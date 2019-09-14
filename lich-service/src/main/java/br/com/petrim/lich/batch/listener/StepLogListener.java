package br.com.petrim.lich.batch.listener;

import br.com.petrim.lich.model.StepProtocol;
import br.com.petrim.lich.service.StepProtocolService;
import br.com.petrim.lich.util.Constants;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Scope("prototype")
public class StepLogListener implements StepExecutionListener {

    @Autowired
    private StepProtocolService stepProtocolService;

    @Override
    public void beforeStep(StepExecution stepExecution) {

        // save step protocol
        StepProtocol stepProtocol = saveNewStepProtocol(stepExecution);
        stepExecution.getExecutionContext().putLong(Constants.STEP_PROTOCOL, stepProtocol.getId());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        Long idStepProtocol = stepExecution.getExecutionContext().getLong(Constants.STEP_PROTOCOL);
        Long idStepProcess = stepExecution.getExecutionContext().getLong(Constants.STEP_PROCESS);

        String status = stepExecution.getExitStatus().getExitCode();
        String errorMessage = getErrorMessage(stepExecution);

        // update step protocol
        stepProtocolService.update(idStepProtocol, idStepProcess, status, new Date(), errorMessage);

        return stepExecution.getExitStatus();
    }

    private String getErrorMessage(StepExecution stepExecution) {
        if (!stepExecution.getJobExecution().getAllFailureExceptions().isEmpty()) {
            return stepExecution.getJobExecution().getAllFailureExceptions()
                    .get(NumberUtils.INTEGER_ZERO).getMessage();
        }

        return null;
    }

    private StepProtocol saveNewStepProtocol(StepExecution stepExecution) {
        StepProtocol stepProtocol = new StepProtocol();

        stepProtocol.setIdStepExecution(stepExecution.getId());
        stepProtocol.setDsStepProcess(stepExecution.getStepName());
        stepProtocol.setDateStart(new Date());

        return stepProtocolService.save(stepProtocol);
    }

    private String getLogPath(StepExecution stepExecution) {
        return ""; // FIXME: define log path on disk.
    }
}
