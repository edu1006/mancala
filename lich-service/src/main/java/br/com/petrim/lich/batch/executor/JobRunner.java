package br.com.petrim.lich.batch.executor;

import br.com.petrim.lich.exception.ProcessException;
import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.model.JobProtocol;
import br.com.petrim.lich.model.User;
import br.com.petrim.lich.service.JobProtocolService;
import br.com.petrim.lich.util.Constants;
import br.com.petrim.lich.util.DateUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobRunner {

    @Autowired
    @Qualifier("batchJobLauncher")
    private JobLauncher jobLauncher;

    @Autowired
    private JobProtocolService jobProtocolService;

    public void runner(Job job, User user, JobProcess jobProcess) {

        try {
            jobLauncher.run(job, getJobParameters(user, jobProcess));
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobParametersInvalidException | JobInstanceAlreadyCompleteException e) {
            throw new ProcessException("Error to initialize process", e);
        }

    }

    private JobParameters getJobParameters(User user, JobProcess jobProcess) {
        JobParametersBuilder builder = new JobParametersBuilder();

        builder.addLong(Constants.TIME, (new Date()).getTime());
        builder.addDate(Constants.PROCESS_DATE_TODAY, DateUtil.getProcessDateToday());
        builder.addLong(Constants.USER, user.getId());
        builder.addLong(Constants.JOB_PROCESS, jobProcess.getId());
        builder.addLong(Constants.JOB_PROTOCOL, createProtocol(jobProcess));

        return builder.toJobParameters();
    }

    private Long createProtocol(JobProcess jobProcess) {
        JobProtocol jobProtocol = new JobProtocol();

        jobProtocol.setDsJobProcess(jobProcess.getIdProcess());
        jobProtocol.setIdJobProcess(jobProcess.getId());

        jobProtocol = jobProtocolService.save(jobProtocol);

        return jobProtocol.getId();
    }

}
