package br.com.petrim.lich.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "step_protocol")
public class StepProtocol extends AbstractEntity {

    @Id
    @Column(name = "id", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_step_process", length = 20)
    private Long idStepProcess;

    @Column(name = "ds_step_process", length = 50, nullable = false)
    private String dsStepProcess;

    @Column(name = "id_step_execution")
    private Long idStepExecution;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_start")
    private Date dateStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "status", length = 20)
    private String status; // Spring status

    @Column(name = "log_path", length = 200)
    private String logPath;

    @Column(name = "error_message", length = 2000)
    private String errorMessage;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdStepProcess() {
        return idStepProcess;
    }

    public void setIdStepProcess(Long idStepProcess) {
        this.idStepProcess = idStepProcess;
    }

    public String getDsStepProcess() {
        return dsStepProcess;
    }

    public void setDsStepProcess(String dsStepProcess) {
        this.dsStepProcess = dsStepProcess;
    }

    public Long getIdStepExecution() {
        return idStepExecution;
    }

    public void setIdStepExecution(Long idStepExecution) {
        this.idStepExecution = idStepExecution;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
