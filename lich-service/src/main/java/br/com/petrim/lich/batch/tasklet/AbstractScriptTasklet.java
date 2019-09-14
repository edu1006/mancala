package br.com.petrim.lich.batch.tasklet;

import br.com.petrim.lich.batch.vo.VoStepExecution;
import br.com.petrim.lich.batch.vo.VoStepStatus;
import br.com.petrim.lich.enums.StatusStepExecutionEnum;
import br.com.petrim.lich.exception.ProcessException;
import br.com.petrim.lich.model.Agent;
import br.com.petrim.lich.model.StepProcess;
import br.com.petrim.lich.service.AgentService;
import br.com.petrim.lich.util.Constants;
import br.com.petrim.lich.util.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Script taxi will be enable to execute only remote.
 */
public abstract class AbstractScriptTasklet extends AbstractTasklet {

    public AbstractScriptTasklet(StepProcess stepProcess) {
        super(stepProcess);
    }

    @Override
    protected void execute() {
        logInfo("Starting process");

        Agent agent = getAgent();
        //updateIdAgent() //FIXME: check if its necessary.
        //logInfo("update id agent");

        logInfo("Type connection of agent: " + agent.getTypeConnection());

        // execute remotely
        executeProcessRemote(agent);
    }

    private void executeProcessRemote(Agent agent) {
        logInfo("Execution on agent: " + agent.getAddress() + ":" + agent.getPort());
        executeProcessRemoteRest(agent);
    }

    private void executeProcessRemoteRest(Agent agent) {
        RestTemplate restTemplate = new RestTemplate();
        String urlProcessRest = getUrlProcessRest(agent);

        HttpEntity<VoStepExecution> executeReq = new HttpEntity<>(getVoStepExecution());
            ResponseEntity<String> executeRes = restTemplate.postForEntity(urlProcessRest, executeReq, String.class);

        String idStepExecuted = executeRes.getBody();

        // Read status process remote
        VoStepExecution voStepExecution = readProcessRemote(restTemplate, agent, idStepExecuted);
        // Check if process already finished
        checkProcessIdRunning(restTemplate, agent, voStepExecution);
        // Check remote status
        readStatusRemoteProcess(voStepExecution);

        //processAgentResult() - @Deprecated (method removed, because "readStatusRemoteProcess" will dispatch an exception in case of status error)
    }

    private VoStepExecution readProcessRemote(RestTemplate restTemplate, Agent agent, String idStepExecuted) {
        await(Constants.STEP_SCRIPT_TIME_WAIT);

        logInfo("Read process remote");

        String statusProcessRest = getUrlStatusRest(agent);

        HttpEntity<VoStepStatus> executeReq = new HttpEntity<>(new VoStepStatus(idStepExecuted));
        ResponseEntity<VoStepExecution> statusProcessResp = restTemplate.postForEntity(statusProcessRest, executeReq, VoStepExecution.class);

        VoStepExecution voStepExecution = statusProcessResp.getBody();
        //updateLogger();
        //updateSystemProcessId();

        while (StatusStepExecutionEnum.FINISHED.getStatus().compareTo(voStepExecution.getStatusStepExecution()) != 0) {

            await(Constants.STEP_SCRIPT_TIME_WAIT);

            statusProcessResp = restTemplate.postForEntity(statusProcessRest, executeReq, VoStepExecution.class);
            voStepExecution = statusProcessResp.getBody();

            //updateLogger();

            //updateSystemProcessId .... if not set yet.
        }

        logInfo("Status remote process: " + voStepExecution.getStatus());
        return voStepExecution;
    }

    private void checkProcessIdRunning(RestTemplate restTemplate, Agent agent, VoStepExecution voStepExecution) {
        logInfo("Check if process ID keep on execution: " + voStepExecution.getSystemProcessId());

        String checkProcessIdRest = getUrlCheckProcessRest(agent);

        VoStepExecution voCheckProcessId = new VoStepExecution();
        voCheckProcessId.setId(voStepExecution.getId());
        voCheckProcessId.setSystemProcessId(voStepExecution.getSystemProcessId());

        HttpEntity<VoStepExecution> executeReq = new HttpEntity<>(voCheckProcessId);
        ResponseEntity<Integer> statusProcessId = restTemplate.postForEntity(checkProcessIdRest, executeReq, Integer.class);

        if (statusProcessId.getBody().compareTo(NumberUtils.INTEGER_ZERO) == 0) {
            throw new ProcessException("Process still running on remote agent. Check on agent server!"); // FIXME: add treatment for this situation
        }

        logInfo("Process already finished on agent server.");
    }

    private void readStatusRemoteProcess(VoStepExecution voStepExecution) {
        if (voStepExecution.getStatus() != null && voStepExecution.getStatus().intValue() > 0) {

            Integer status = voStepExecution.getStatus();
            StringBuilder logger = null;

            if (voStepExecution.getLog() != null && !voStepExecution.getLog().isEmpty()) {
                logger = new StringBuilder();

                for (String line : voStepExecution.getLog()) {
                    logger.append(line);
                    logger.append("\n");
                }
            }

            generateMessageToStatus(status, logger);
        }
    }

    private void generateMessageToStatus(int status, StringBuilder logger) {
        StringBuilder message = new StringBuilder();
        if (logger != null) {
            message.append(logger.toString());
        }
        message.append(getMessageToStatus(status).toString());
        // updateLogger()
        throw new ProcessException(message.toString());
    }

    private StringBuilder getMessageToStatus(int status) {
        StringBuilder sb = new StringBuilder();
        sb.append("Status : " + status);
        sb.append(" - ");

        if(status == 1) {
            sb.append("Catchall for general errors");
        } else if(status == 2) {
            sb.append("Misuse of shell builtins (according to Bash documentation)");
        } else if(status == 99) {
            sb.append("Error found in log according to configured text.");
        } else if(status == 126) {
            sb.append("Command invoked cannot execute");
        } else if(status == 127) {
//            sb.append("Command not found : " + ); //FIXME: Check how get command to execute.
        } else if(status == 128) {
            sb.append("Invalid argument to exit");
        } else if(status == 130) {
            sb.append("Script terminated by Control-C");
        } else if(status > 255) {
            sb.append("Exit status out of range");
        } else {
            sb.append("Script returns status " + status);
        }

//        executeErrorAction(status, sb.toString()); // FIXME: Execute some actions in case of error occurs.

        return sb;
    }

    private String getUrlProcessRest(Agent agent) {
        return buildUrlRest(agent, "/v1/step/");
    }

    private String getUrlStatusRest(Agent agent) {
        return buildUrlRest(agent, "/v1/step/status");
    }

    private String getUrlCheckProcessRest(Agent agent) {
        return buildUrlRest(agent, "/v1/step/processid");
    }

    private String buildUrlRest(Agent agent, String resource) {
        StringBuilder url = new StringBuilder("http://");
        url.append(agent.getAddress());
        url.append(":");
        url.append(agent.getPort());
        url.append(resource);

        return url.toString();
    }

    private VoStepExecution getVoStepExecution() {
        VoStepExecution voStepExecution = new VoStepExecution();

        voStepExecution.setId(getIdToJsonAgent());
        voStepExecution.setScript(getScriptProcess());
        voStepExecution.setTime(new Date().getTime());

        /** if step have a specific log path */
        if (StringUtils.isNotBlank(getStepProcess().getLogPath())) {
            voStepExecution.setLogpath(getStepProcess().getLogPath());
        }

        /** if step have a specific log file */
        if (StringUtils.isNotBlank(getStepProcess().getLogName())) {
            voStepExecution.setLogname(getStepProcess().getLogName());
        }

        return voStepExecution;
    }

    //FIXME: build id
    private String getIdToJsonAgent() {
        StringBuilder id = new StringBuilder();

        id.append(getStepProcess().getIdJobProcess());
        id.append("_");
        id.append(getStepProcess().getIdStep());
        id.append("_");

        return id.toString();
    }

    private Agent getAgent() {
        AgentService agentService = SpringContextUtil.getBean(AgentService.class);
        return agentService.findToRunStep(getStepProcess().getIdAgent());
    }

    /**
     * Method to implement scripts to be executed.
     *
     * @return
     */
    protected abstract String getScriptProcess();
}
