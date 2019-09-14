package br.com.petrim.lich.batch.builder;

import br.com.petrim.lich.batch.extractor.StepJobParameterExtractor;
import br.com.petrim.lich.batch.listener.StepJobListener;
import br.com.petrim.lich.batch.listener.StepLogListener;
import br.com.petrim.lich.batch.tasklet.AbstractTasklet;
import br.com.petrim.lich.enums.TypeStepProcessEnum;
import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.model.StepProcess;
import br.com.petrim.lich.service.JobProcessService;
import br.com.petrim.lich.util.ReflectionUtil;
import br.com.petrim.lich.util.SpringContextUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StepProcessBuilder {

    @Autowired
    private StepBuilderFactory stepBuilders;

    public Step buildStep(StepProcess stepProcess) {
        if (stepProcess.getIdInnerJobProcess() != null) {

            return buildJobStep(stepProcess);
        }

        return buildSimpleStep(stepProcess);
    }

    private Step buildSimpleStep(StepProcess stepProcess) {
        return stepBuilders.get(stepProcess.getIdStep())
                .tasklet(getTasklet(stepProcess))
                .listener(getStepLogListener())
                .build();
    }

    private AbstractTasklet getTasklet(StepProcess stepProcess) {
        return (AbstractTasklet) ReflectionUtil
                .newInstanceOfTasklet(
                        getCompleteClassName(
                                stepProcess.getType().getClassName()), stepProcess);
    }

    private String getCompleteClassName(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append(AbstractTasklet.class.getPackage().getName());
        sb.append(".impl.");
        sb.append(className);
        return sb.toString();
    }

    private Step buildJobStep(StepProcess stepProcess) {

        JobProcessService jobProcessService = SpringContextUtil.getBean(JobProcessService.class);
        JobProcess jobProcess = jobProcessService.getJobProcessById(stepProcess.getIdInnerJobProcess());

        JobProcessBuilder builder = SpringContextUtil.getBean(JobProcessBuilder.class);
        Job job = builder.build(jobProcess, Boolean.TRUE);

        return stepBuilders.get(stepProcess.getIdStep())
                .job(job)
                .parametersExtractor(new StepJobParameterExtractor())
                .listener(getStepJobListener())
                //.listener(getStepLogListener())
                .build();
    }

    private StepJobListener getStepJobListener() {
        return SpringContextUtil.getBean(StepJobListener.class);
    }

    private StepLogListener getStepLogListener() {
        return SpringContextUtil.getBean(StepLogListener.class);
    }

    public Flow buildParallelStep(StepProcess stepProcess) {
        return new FlowBuilder<SimpleFlow>(stepProcess.getIdStep())
                .split(stepParallelTaskExecutor(stepProcess))
                .add(buildFlowsToParallelStep(stepProcess))
                .build();
    }

    private TaskExecutor stepParallelTaskExecutor(StepProcess stepProcess) {
        return new SimpleAsyncTaskExecutor("Lich_SP_" + stepProcess.getIdStep() + "_");
    }

    private Flow[] buildFlowsToParallelStep(StepProcess stepProcess) {
        List<Flow> flows = new ArrayList<>();

        for (StepProcess parallelStep : stepProcess.getStepsParallels()) {

            if (TypeStepProcessEnum.STEP_PARALLEL.equals(parallelStep.getType())) {
                flows.add(buildParallelStep(parallelStep));
            } else {
                flows.add(buildFlowStep(parallelStep));
            }
        }

        return flows.toArray(new Flow[flows.size()]);
    }

    public Flow buildFlowStep(StepProcess stepProcess) {
        return new FlowBuilder<Flow>("flow_" + stepProcess.getIdStep())
                .start(buildStep(stepProcess)).build();
    }

}
