package br.com.petrim.lich.batch.vo;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class VoStepExecution {

    private String id;
    private String script;
    private String logpath;
    private String logname;
    private String lichLogFilePath;
    private Long time;
    private String lichStepId;
    private String systemProcessId;

    @JsonAlias("situacao")
    private String statusStepExecution; //situacao
    private List<String> log;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getLogpath() {
        return logpath;
    }

    public void setLogpath(String logpath) {
        this.logpath = logpath;
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getLichLogFilePath() {
        return lichLogFilePath;
    }

    public void setLichLogFilePath(String lichLogFilePath) {
        this.lichLogFilePath = lichLogFilePath;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getLichStepId() {
        return lichStepId;
    }

    public void setLichStepId(String lichStepId) {
        this.lichStepId = lichStepId;
    }

    public String getSystemProcessId() {
        return systemProcessId;
    }

    public void setSystemProcessId(String systemProcessId) {
        this.systemProcessId = systemProcessId;
    }

    public String getStatusStepExecution() {
        return statusStepExecution;
    }

    public void setStatusStepExecution(String statusStepExecution) {
        this.statusStepExecution = statusStepExecution;
    }

    public List<String> getLog() {
        return log;
    }

    public void setLog(List<String> log) {
        this.log = log;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
