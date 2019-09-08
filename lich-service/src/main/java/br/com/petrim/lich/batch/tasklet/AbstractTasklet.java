package br.com.petrim.lich.batch.tasklet;

import br.com.petrim.lich.enums.YesNoEnum;
import br.com.petrim.lich.model.StepProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public abstract class AbstractTasklet implements Tasklet {

    private ChunkContext chunkContext;

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

            this.chunkContext = chunkContext;

            // execute main step
            execute();

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
     * Execute method.
     *
     */
    protected abstract void execute();

    /**
     * Check if the step was configure with 'continue if error'
     *
     * @return
     */
    private Boolean isSkippableStep() {
        return (this.stepProcess.getContinueIfError() != null
                && YesNoEnum.YES.equals(this.stepProcess.getContinueIfError()));
    }

    /**
     * Method to wait some time (parameter)
     */
    protected void await(Long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            logWarn("Problem to wait", e);

            // Restore interrupted state...
            Thread.currentThread().interrupt();
        }
    }

    // Get's and Set's

    /**
     * Get step process.
     *
     * @return
     */
    protected StepProcess getStepProcess() {
        return this.stepProcess;
    }

    /**
     * Get id step of spring.
     *
     * @return
     */
    protected Long getIdStepSpring() {
        return this.chunkContext.getStepContext().getStepExecution().getId();
    }

    /**
     * Get step execution context.
     *
     * @return
     */
    protected ExecutionContext getStepExecutionContext() {
        return this.chunkContext.getStepContext().getStepExecution().getExecutionContext();
    }

    /**
     * Get job execution context.
     *
     * @return
     */
    protected ExecutionContext getJobExecutionContext() {
        return this.chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
    }

    /**
     * Get logger.
     *
     * @return
     */
    protected Logger getLogger() {
        return LoggerFactory.getLogger(getClass());
    }

    /**
     * Log info for steps.
     *
     * @param message
     */
    protected void logInfo(String message) {
        this.getLogger().info(getStepProcess().getIdStep() + " - " + message);
    }

    /**
     * Log warn for steps.
     *
     * @param message
     * @param t
     */
    protected void logWarn(String message, Throwable t) {
        this.getLogger().warn(getStepProcess().getIdStep() + " - " + message, t);
    }

}
