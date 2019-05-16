package br.com.petrim.lich.batch.tasklet;

import br.com.petrim.lich.model.StepProcess;
import org.springframework.batch.core.StepContribution;
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
        Thread.sleep(10000L);
        System.out.println(stepProcess.getIdStep());
        return RepeatStatus.FINISHED;
    }

}
