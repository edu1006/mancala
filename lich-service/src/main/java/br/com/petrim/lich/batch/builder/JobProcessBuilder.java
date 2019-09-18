package br.com.petrim.lich.batch.builder;

import br.com.petrim.lich.batch.listener.JobLogListener;
import br.com.petrim.lich.enums.TypeStepProcessEnum;
import br.com.petrim.lich.exception.ProcessException;
import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.model.StepProcess;
import br.com.petrim.lich.util.Constants;
import br.com.petrim.lich.util.SpringContextUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.JobFlowBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

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
        JobFlowBuilder jobFlowBuilder = buildJobFlow(jobBuilder, jobProcess);

        if (jobFlowBuilder != null ) {
            throw new ProcessException("Error to create process");
        }

        return jobFlowBuilder.build().build();
    }

    private JobBuilder getJobBuilder(JobProcess jobProcess, Boolean innerJob) {

        StringBuilder jobName = new StringBuilder();
        jobName.append(jobProcess.getId());
        jobName.append("_");

        if (innerJob) {
            jobName.append(Constants.PREFIX_INNER_JOB);
        }

        jobName.append(jobProcess.getIdProcess());

        JobBuilder jobBuilder = jobBuilders.get(jobName.toString());
        jobBuilder.repository(jobRepository);
        jobBuilder.listener(getJobLogListener());
        return jobBuilder;
    }

    private JobFlowBuilder buildJobFlow(JobBuilder jobBuilder, JobProcess jobProcess) {

        JobFlowBuilder jobFlowBuilder = null;

        jobBuilder.incrementer(new RunIdIncrementer());

        List<StepProcess> stepsProcesses = jobProcess.getStepsProcesses().stream().collect(Collectors.toList());
        stepsProcesses.sort((Comparator.comparing(StepProcess::getOrder)));

        if (!stepsProcesses.isEmpty()) {

            Iterator<StepProcess> iterator = stepsProcesses.iterator();
            jobFlowBuilder = buildSteps(jobBuilder, iterator);
        }

        return jobFlowBuilder;
    }

    private JobFlowBuilder buildSteps(JobBuilder jobBuilder, Iterator<StepProcess> iterator) {
        StepProcess stepProcess;

        JobFlowBuilder jobFlowBuilder = buildFirstStep(jobBuilder, iterator.next()); // Build first step.

        while (iterator.hasNext()) {
            stepProcess = iterator.next();
            buildNextStep(jobFlowBuilder, stepProcess);
        }

        return jobFlowBuilder;
    }

    private JobFlowBuilder buildFirstStep(JobBuilder jobBuilder, StepProcess stepProcess) {
        if (isStepParallel(stepProcess)) {
            return jobBuilder.start(stepProcessBuilder.buildParallelStep(stepProcess)); // parallel step first
        } else {
            return jobBuilder.start(stepProcessBuilder.buildFlowStep(stepProcess));
        }
    }

    private void buildNextStep(JobFlowBuilder jobFlowBuilder, StepProcess stepProcess) {
        if (isStepParallel(stepProcess)) {
            jobFlowBuilder.next(stepProcessBuilder.buildParallelStep(stepProcess));
        } else {
            jobFlowBuilder.next(stepProcessBuilder.buildStep(stepProcess));
        }
    }

    private Boolean isStepParallel(StepProcess stepProcess) {
        return TypeStepProcessEnum.STEP_PARALLEL.equals(stepProcess.getType());
    }

    private JobLogListener getJobLogListener() {
        return SpringContextUtil.getBean(JobLogListener.class);
    }

}
