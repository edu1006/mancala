package br.com.petrim.lich.vo;

import java.util.List;

public class JobExecsResultVo {

    private List<JobExecResultVo> runnings;
    private List<JobExecResultVo> executeds;

    public JobExecsResultVo(List<JobExecResultVo> runnings, List<JobExecResultVo> executeds) {
        this.runnings = runnings;
        this.executeds = executeds;
    }

    public List<JobExecResultVo> getRunnings() {
        return runnings;
    }

    public void setRunnings(List<JobExecResultVo> runnings) {
        this.runnings = runnings;
    }

    public List<JobExecResultVo> getExecuteds() {
        return executeds;
    }

    public void setExecuteds(List<JobExecResultVo> executeds) {
        this.executeds = executeds;
    }
}
