package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.model.JobProtocol;
import br.com.petrim.lich.repository.JobProtocolRepository;
import br.com.petrim.lich.service.JobProtocolService;
import br.com.petrim.lich.util.SpringContextUtil;
import br.com.petrim.lich.vo.JobExecResultVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.JobExecutionDao;
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
    private JobExplorer jobExplorer;

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
    public synchronized void updateLastExecutions() {
        List<JobProtocol> protocols = findLastExecutions();
        List<JobExecResultVo> results = new ArrayList<>();

        JobExecution execution;
        JobExecResultVo result;

        for (JobProtocol protocol : protocols) {
            execution = jobExplorer.getJobExecution(protocol.getIdJobExecution());
            result = getResult(protocol, execution);

            results.add(result);
        }

        getLogger().info("Executions size: " + results.size());

        // Notify client's
        simpMessagingTemplate.convertAndSend("/ws_lich_topic/process_executed", getResultsJson(results));
    }

    private List<JobProtocol> findLastExecutions() {
        Long lastHours = NumberUtils.LONG_ONE; //FIXME: Recuperar de propriedade
        LocalDateTime localDateTime = LocalDateTime.now().minusHours(lastHours);
        Date dateEnd = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        return jobProtocolRepository.findEndsProtocols(dateEnd);
    }

    private String getResultsJson(List<JobExecResultVo> results) {
        String resultJson = "Error";

        try {
            ObjectMapper mapper = new ObjectMapper();
            resultJson = mapper.writeValueAsString(results);
        } catch (JsonProcessingException e) {
            getLogger().error(e.getMessage(), e);
        }

        return resultJson;
    }

    private JobExecResultVo getResult(JobProtocol protocol, JobExecution execution) {
        JobExecResultVo result = new JobExecResultVo();

        result.setIdJobExecution(execution.getId());
        result.setJobName(execution.getJobInstance().getJobName());
        result.setStart(execution.getStartTime());

        if (execution.getEndTime() != null) {
            result.setEnd(execution.getEndTime());
            result.setStatus(execution.getStatus().getBatchStatus().name());
            result.setExitCode(execution.getExitStatus().getExitCode());
        } else {
            result.setEnd(protocol.getDateEnd());
            result.setStatus(protocol.getStatus());
        }

        return result;
    }
}
