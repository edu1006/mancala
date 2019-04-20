package br.com.petrim.lich.batch.builder;

import br.com.petrim.lich.batch.listener.JobLogListener;
import br.com.petrim.lich.batch.tasklet.ScriptTasklet;
import br.com.petrim.lich.exception.ProcessException;
import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.model.StepProcess;
import br.com.petrim.lich.util.Constants;
import br.com.petrim.lich.util.SpringContextUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

@Component
public class JobProcessBuilder {

    @Autowired
    private StepProcessBuilder stepProcessBuilder;

    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private JobRepository jobRepository;

    public synchronized Job build(JobProcess jobProcess, Boolean innerJob) {
        JobBuilder jobBuilder = getJobBuilder(jobProcess, innerJob);
        SimpleJobBuilder simpleJobBuilder = buildJobFlow(jobBuilder, jobProcess);

        if (simpleJobBuilder == null) {
            throw new ProcessException("Error to create process");
        }

        return simpleJobBuilder.build();
    }

    private JobBuilder getJobBuilder(JobProcess jobProcess, Boolean innerJob) {

        StringBuilder jobName = new StringBuilder();

        if (innerJob) {
            jobName.append(Constants.PREFIX_INNER_JOB);
        }

        jobName.append(jobProcess.getIdProcess());

        JobBuilder jobBuilder = jobBuilders.get(jobName.toString());
        jobBuilder.repository(jobRepository);
        jobBuilder.listener(getJobLogListener());
        return jobBuilder;
    }

    private SimpleJobBuilder buildJobFlow(JobBuilder jobBuilder, JobProcess jobProcess) {
        SimpleJobBuilder simpleJobBuilder = null;

        jobBuilder.incrementer(new RunIdIncrementer());

        Set<StepProcess> stepsProcesses = jobProcess.getStepsProcesses();

        if (stepsProcesses != null && !stepsProcesses.isEmpty()) {

            int index = 0;
            Iterator<StepProcess> iterator = stepsProcesses.iterator();

            while (iterator.hasNext()) {

                if (index == 0) { // It's the first step of job

                    simpleJobBuilder = jobBuilder.start(stepProcessBuilder.buildStep(iterator.next()));

                } else {

                    simpleJobBuilder.next(stepProcessBuilder.buildStep(iterator.next()));

                }

                index++;
            }

        }

        return simpleJobBuilder;
    }

    private JobLogListener getJobLogListener() {
        return SpringContextUtil.getBean(JobLogListener.class);
    }

}
