package br.com.petrim.lich.batch.tasklet.impl;

import br.com.petrim.lich.batch.tasklet.AbstractScriptTasklet;
import br.com.petrim.lich.model.StepProcess;

public class CommandBashTasklet extends AbstractScriptTasklet {

    public CommandBashTasklet(StepProcess stepProcess) {
        super(stepProcess);
    }

    @Override
    protected String getScriptProcess() {
        return replaceParameter(getStepProcess().getScriptBash());
    }
}
