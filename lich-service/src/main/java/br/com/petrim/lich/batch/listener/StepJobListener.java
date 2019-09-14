package br.com.petrim.lich.batch.listener;

import br.com.petrim.lich.util.Constants;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StepJobListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        Long idJobProcess = stepExecution.getJobParameters().getLong(Constants.JOB_PROCESS);
        stepExecution.getExecutionContext().putLong(Constants.JOB_PARENT, idJobProcess);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return stepExecution.getExitStatus();
    }

}
