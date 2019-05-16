package br.com.petrim.lich.vo;

import br.com.petrim.lich.serializer.DateTimeStringSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

public class JobExecResultVo {

    private Long idJobExecution;
    private String jobName;

    @JsonSerialize(using = DateTimeStringSerializer.class)
    private Date start;

    @JsonSerialize(using = DateTimeStringSerializer.class)
    private Date end;
    private String status;
    private String exitCode;

    public Long getIdJobExecution() {
        return idJobExecution;
    }

    public void setIdJobExecution(Long idJobExecution) {
        this.idJobExecution = idJobExecution;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
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

    public String getExitCode() {
        return exitCode;
    }

    public void setExitCode(String exitCode) {
        this.exitCode = exitCode;
    }
}
