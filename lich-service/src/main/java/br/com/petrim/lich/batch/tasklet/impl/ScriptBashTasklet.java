package br.com.petrim.lich.batch.tasklet.impl;

import br.com.petrim.lich.batch.tasklet.AbstractScriptTasklet;
import br.com.petrim.lich.model.StepProcess;

public class ScriptBashTasklet extends AbstractScriptTasklet {

    public ScriptBashTasklet(StepProcess stepProcess) {
        super(stepProcess);
    }

    @Override
    protected String getScriptProcess() {
        return "sh " + replaceParameter(getStepProcess().getScriptBash());
    }

}
