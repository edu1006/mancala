package br.com.petrim.lich.batch.builder;

import br.com.petrim.lich.batch.extractor.StepJobParameterExtractor;
import br.com.petrim.lich.batch.listener.StepJobListener;
import br.com.petrim.lich.batch.tasklet.ScriptTasklet;
import br.com.petrim.lich.model.JobProcess;
import br.com.petrim.lich.model.StepProcess;
import br.com.petrim.lich.service.JobProcessService;
import br.com.petrim.lich.util.SpringContextUtil;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
                .tasklet(new ScriptTasklet(stepProcess))
                .build();
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
                .build();
    }

    private StepJobListener getStepJobListener() {
        return SpringContextUtil.getBean(StepJobListener.class);
    }

}
