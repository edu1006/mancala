package br.com.petrim.lich.batch.executor;

import br.com.petrim.lich.batch.builder.JobProcessBuilder;
import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.model.User;
import br.com.petrim.lich.service.JobProcessService;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobExecutor {

    @Autowired
    private JobProcessService jobProcessService;

    @Autowired
    private JobProcessBuilder jobProcessBuilder;

    @Autowired
    private JobRunner jobRunner;

    public void executeJob(Long idJobProcess, User user) {
        JobProcess jobProcess = jobProcessService.getJobProcessById(idJobProcess);
        Job job = jobProcessBuilder.build(jobProcess, Boolean.FALSE);

        jobRunner.runner(job, user, jobProcess);
    }

}
