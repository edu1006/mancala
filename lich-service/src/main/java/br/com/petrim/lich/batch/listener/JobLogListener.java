package br.com.petrim.lich.batch.listener;

import br.com.petrim.lich.exception.ProcessException;
import br.com.petrim.lich.model.JobProtocol;
import br.com.petrim.lich.service.JobProtocolService;
import br.com.petrim.lich.util.Constants;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@Scope("prototype")
public class JobLogListener implements JobExecutionListener {

    @Autowired
    private JobProtocolService jobProtocolService;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        if (!jobExecution.getJobInstance().getJobName().startsWith(Constants.PREFIX_INNER_JOB)) {
            createJobProtocol(jobExecution);
        } else {
            createInnerJobProtocol(jobExecution);
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (!jobExecution.getJobInstance().getJobName().startsWith(Constants.PREFIX_INNER_JOB)) {
            updateJobProtocol(jobExecution);
        } else {
            updateInnerJobProtocol(jobExecution);
        }
    }

    private void createJobProtocol(JobExecution jobExecution) {
        Long idJobProtocol = jobExecution.getJobParameters().getLong(Constants.JOB_PROTOCOL);

        JobProtocol jobProtocol = findJobProtocol(idJobProtocol);
        jobProtocol.setDateStart(jobExecution.getStartTime());
        jobProtocol.setIdJobExecution(jobExecution.getId());

        jobProtocolService.save(jobProtocol);
        jobProtocolService.updateLastExecutions();
    }

    private void updateJobProtocol(JobExecution jobExecution) {
        Long idJobProtocol = jobExecution.getJobParameters().getLong(Constants.JOB_PROTOCOL);
        updateJobProtocol(idJobProtocol, jobExecution);
    }

    private void createInnerJobProtocol(JobExecution jobExecution) {
        JobProtocol jobProtocol = new JobProtocol();

        jobProtocol.setDateStart(jobExecution.getStartTime());
        jobProtocol.setIdJobExecution(jobExecution.getId());
        jobProtocol.setDsJobProcess(jobExecution.getJobInstance().getJobName());
        jobProtocol.setIdJobProcess(jobExecution.getJobParameters().getLong(Constants.JOB_PROCESS));
        jobProtocol.setParentProtocol(jobExecution.getJobParameters().getLong(Constants.JOB_PROTOCOL));

        jobProtocol = jobProtocolService.save(jobProtocol);
        jobProtocolService.updateLastExecutions();

        jobExecution.getExecutionContext().putLong(Constants.JOB_PROTOCOL, jobProtocol.getId());
    }

    private void updateInnerJobProtocol(JobExecution jobExecution) {
        Long idJobProtocol = jobExecution.getExecutionContext().getLong(Constants.JOB_PROTOCOL);
        updateJobProtocol(idJobProtocol, jobExecution);
    }

    private void updateJobProtocol(Long idJobProtocol, JobExecution jobExecution) {
        JobProtocol jobProtocol = findJobProtocol(idJobProtocol);
        jobProtocol.setDateEnd(new Date());
        jobProtocol.setStatus(jobExecution.getStatus().name());

        // check if occurs error with completed job (in case of continuable step's)
        if ((jobExecution.getFailureExceptions() != null && !jobExecution.getFailureExceptions().isEmpty())
                && BatchStatus.COMPLETED.equals(jobExecution.getStatus())) {

            jobProtocol.setStatus(Constants.WARNING_STATUS); // in this case, the protocol will receive warning status.
        }

        // FIXME: save error messages.

        jobProtocolService.save(jobProtocol);
        jobProtocolService.updateLastExecutions();
    }

    private JobProtocol findJobProtocol(Long idJobProtocol) {
        if (idJobProtocol == null) {
            throw new ProcessException("Error to update job protocol");
        }

        Optional<JobProtocol> optional = jobProtocolService.findById(idJobProtocol);

        if (!optional.isPresent()) {
            throw new ProcessException("Error to find job protocol: " + idJobProtocol);
        }

        return optional.get();
    }
}
