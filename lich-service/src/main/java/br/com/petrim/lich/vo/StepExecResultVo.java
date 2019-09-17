package br.com.petrim.lich.vo;

import br.com.petrim.lich.serializer.DateTimeStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class StepExecResultVo {

    private Long idStepExecution;
    private String stepName;
    @JsonSerialize(using = DateTimeStringSerializer.class)
    private Date start;
    @JsonSerialize(using = DateTimeStringSerializer.class)
    private Date end;
    private String status;
    private String logPath;

    public Long getIdStepExecution() {
        return idStepExecution;
    }

    public void setIdStepExecution(Long idStepExecution) {
        this.idStepExecution = idStepExecution;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
}
