package br.com.petrim.lich.batch.tasklet;

import br.com.petrim.lich.enums.YesNoEnum;
import br.com.petrim.lich.model.Parameter;
import br.com.petrim.lich.model.StepProcess;
import br.com.petrim.lich.service.ParameterService;
import br.com.petrim.lich.util.Constants;
import br.com.petrim.lich.util.SpringContextUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractTasklet implements Tasklet {

    private ChunkContext chunkContext;

    private StepProcess stepProcess;

    /**
     * Used to log by list.
     */
    private Integer nextLineLog;

    public AbstractTasklet(StepProcess stepProcess) {
        this.stepProcess = stepProcess;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        // Executions (step and job)
        StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();
        JobExecution jobExecution = stepExecution.getJobExecution();

        // Set id step process on execution context.
        stepExecution.getExecutionContext().putLong(Constants.STEP_PROCESS, stepProcess.getId());

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

    protected String replaceParameter(String script) {
        ParameterService parameterService = SpringContextUtil.getBean(ParameterService.class);
        Map<String, Parameter> mapParameters = parameterService.findEnabledValued();

        String[] words = script.split("#");
        if (words.length > 0) {
            for (String word: words) {
                if (!word.contains(" ")) {
                    Parameter parameter = mapParameters.get(word);

                    if (parameter != null) {
                        script = script.replace("#" + word + "#", parameter.getValue());
                    }
                }
            }
        }

        return script;
    }

    protected void updateLog(String log) {
        String logPath = getStepExecutionContext().getString(Constants.STEP_LOG_PATH);
        Path pathLogPath = Paths.get(logPath);

        String line = System.lineSeparator() + log;
        try {
            if (Files.exists(pathLogPath)) {
                Files.write(pathLogPath, line.getBytes(), StandardOpenOption.APPEND);
            } else {
                Files.write(pathLogPath, line.getBytes(), StandardOpenOption.CREATE_NEW);
            }
        } catch (IOException e) {
            logWarn("Error to save on log file: " + logPath, e);
        }
    }

    protected void updateLog(List<String> log) {
        if (log != null && !log.isEmpty()) {
            Integer lineToStart = (this.nextLineLog != null) ? this.nextLineLog : NumberUtils.INTEGER_ZERO;
            updateLogLines(log, lineToStart);
        }
    }

    private void updateLogLines(List<String> log, Integer lineToStart) {
        for (int i = lineToStart; i < log.size(); i++) {
            updateLog(log.get(i));
        }

        this.nextLineLog = log.size();
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
        this.getLogger().info("{} - {}", getStepProcess().getIdStep(), message);
    }

    /**
     * Log warn for steps.
     *
     * @param message
     * @param t
     */
    protected void logWarn(String message, Throwable t) {
        StringBuilder warnMessage = new StringBuilder(getStepProcess().getIdStep());
        warnMessage.append(" - ");
        warnMessage.append(message);

        this.getLogger().warn(warnMessage.toString(), t);
    }

}
