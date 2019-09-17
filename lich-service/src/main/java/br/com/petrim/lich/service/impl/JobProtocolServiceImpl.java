package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.model.JobProtocol;
import br.com.petrim.lich.repository.JobProtocolRepository;
import br.com.petrim.lich.service.JobProtocolService;
import br.com.petrim.lich.vo.JobExecResultVo;
import br.com.petrim.lich.vo.JobExecsResultVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("jobProtocolService")
public class JobProtocolServiceImpl extends AbstractService implements JobProtocolService {

    @Autowired
    private JobProtocolRepository jobProtocolRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public JobProtocol save(JobProtocol jobProtocol) {
        return jobProtocolRepository.save(jobProtocol);
    }

    @Override
    public Optional<JobProtocol> findById(Long id) {
        return jobProtocolRepository.findById(id);
    }

    @Override
    public JobExecsResultVo findLastExecutions() {
        List<JobProtocol> protocols = findLastExecuteds();
        List<JobProtocol> runningProtocols = findRunningExecutions();

        List<JobExecResultVo> results = new ArrayList<>();
        List<JobExecResultVo> runningResults = new ArrayList<>();

        setResults(protocols, results);
        setResults(runningProtocols, runningResults);

        getLogger().info("Executions size: " + results.size());
        getLogger().info("Running size: " + runningResults.size());

        return new JobExecsResultVo(runningResults, results);
    }

    @Override
    public synchronized void updateLastExecutions() {
        // Notify client's
        JobExecsResultVo result = findLastExecutions();
        simpMessagingTemplate.convertAndSend("/ws_lich_topic/process_executed", getResultsJson(result));
    }

    private void setResults(List<JobProtocol> protocols, List<JobExecResultVo> results) {
        JobExecResultVo result;

        for (JobProtocol protocol : protocols) {
            result = getResult(protocol);

            results.add(result);
        }
    }

    private List<JobProtocol> findLastExecuteds() {
        Long lastHours = NumberUtils.LONG_ONE; //FIXME: Recuperar de propriedade
        LocalDateTime localDateTime = LocalDateTime.now().minusHours(lastHours);
        Date dateEnd = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        return jobProtocolRepository.findEndsProtocols(dateEnd);
    }

    private List<JobProtocol> findRunningExecutions() {
        return jobProtocolRepository.findNotEndsProtocols();
    }

    private String getResultsJson(JobExecsResultVo result) {
        String resultJson = "Error";

        try {
            ObjectMapper mapper = new ObjectMapper();
            resultJson = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            getLogger().error(e.getMessage(), e);
        }

        return resultJson;
    }

    private JobExecResultVo getResult(JobProtocol protocol) {
        JobExecResultVo result = new JobExecResultVo();

        result.setIdJobExecution(protocol.getId());
        result.setJobName(protocol.getDsJobProcess());
        result.setStart(protocol.getDateStart());

        result.setEnd(protocol.getDateEnd());
        result.setStatus(protocol.getStatus());

        return result;
    }
}
