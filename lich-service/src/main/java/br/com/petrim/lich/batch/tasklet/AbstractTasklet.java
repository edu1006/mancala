package br.com.petrim.lich.batch.tasklet;

import br.com.petrim.lich.enums.YesNoEnum;
import br.com.petrim.lich.model.StepProcess;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class AbstractTasklet implements Tasklet {

    private StepProcess stepProcess;

    public AbstractTasklet(StepProcess stepProcess) {
        this.stepProcess = stepProcess;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        // Executions (step and job)
        StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
        JobExecution jobExecution = stepExecution.getJobExecution();

        // Execution of the step
        try {

            Thread.sleep(10000L);
            System.out.println(stepProcess.getIdStep().length());

        } catch (Exception e) {

            // Treatment of step with continue with error
            if (isSkippableStep()) {
                jobExecution.addFailureException(e);
            } else {
                throw e;
            }

        }

        // Return
        return RepeatStatus.FINISHED;
    }

    /**
     * Check if the step was configure with 'continue if error'
     *
     * @return
     */
    private Boolean isSkippableStep() {
        return (this.stepProcess.getContinueIfError() != null
                && YesNoEnum.YES.equals(this.stepProcess.getContinueIfError()));
    }

}
