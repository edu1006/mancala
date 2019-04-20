package br.com.petrim.lich.service.impl;

import br.com.petrim.lich.model.JobProtocol;
import br.com.petrim.lich.repository.JobProtocolRepository;
import br.com.petrim.lich.service.JobProtocolService;
import br.com.petrim.lich.util.SpringContextUtil;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.dao.JobExecutionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("jobProtocolService")
public class JobProtocolServiceImpl extends AbstractService implements JobProtocolService {

    @Autowired
    private JobProtocolRepository jobProtocolRepository;

    @Autowired
    private JobExplorer jobExplorer;

    @Override
    public JobProtocol save(JobProtocol jobProtocol) {
        return jobProtocolRepository.save(jobProtocol);
    }

    @Override
    public Optional<JobProtocol> findById(Long id) {
        return jobProtocolRepository.findById(id);
    }

    @Override
    public void updateLastExecutions() {
        List<JobProtocol> protocols = jobProtocolRepository.findAll();
        List<JobExecution> executions = new ArrayList<>();

        protocols.forEach(protocol -> {
            JobExecution execution = jobExplorer.getJobExecution(protocol.getIdJobExecution());
            executions.add(execution);
        });

        getLogger().info("Executions size: " + executions.size());
    }
}
