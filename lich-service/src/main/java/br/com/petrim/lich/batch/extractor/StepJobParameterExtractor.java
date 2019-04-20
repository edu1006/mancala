package br.com.petrim.lich.batch.extractor;

import br.com.petrim.lich.util.Constants;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
import org.springframework.batch.core.step.job.JobParametersExtractor;

public class StepJobParameterExtractor extends DefaultJobParametersExtractor implements JobParametersExtractor {

    @Override
    public JobParameters getJobParameters(Job job, StepExecution stepExecution) {
        JobParameters parameters = super.getJobParameters(job, stepExecution);

        JobParametersBuilder builder = new JobParametersBuilder();
        builder.addJobParameters(parameters);
        builder.addLong(Constants.STEP_EXECUTION, stepExecution.getId());

        if (stepExecution.getExecutionContext().containsKey(Constants.JOB_PARENT)) {
            builder.addLong(Constants.JOB_PARENT, stepExecution.getExecutionContext().getLong(Constants.JOB_PARENT));
        }

        return builder.toJobParameters();
    }
}
