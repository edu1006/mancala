package br.com.petrim.lich.model;

import br.com.petrim.lich.listener.AuditEntity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "job_protocol")
public class JobProtocol extends AuditEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_job_process")
    private Long idJobProcess;

    @Column(name = "ds_job_process")
    private String dsJobProcess;

    @Column(name = "id_job_execution")
    private Long idJobExecution; // Id Spring Job Execution

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_start")
    private Date dateStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "status")
    private String status; //Spring Status

    @Column(name = "parentProtocol")
    private Long parentProtocol;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdJobProcess() {
        return idJobProcess;
    }

    public void setIdJobProcess(Long idJobProcess) {
        this.idJobProcess = idJobProcess;
    }

    public String getDsJobProcess() {
        return dsJobProcess;
    }

    public void setDsJobProcess(String dsJobProcess) {
        this.dsJobProcess = dsJobProcess;
    }

    public Long getIdJobExecution() {
        return idJobExecution;
    }

    public void setIdJobExecution(Long idJobExecution) {
        this.idJobExecution = idJobExecution;
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

    public Long getParentProtocol() {
        return parentProtocol;
    }

    public void setParentProtocol(Long parentProtocol) {
        this.parentProtocol = parentProtocol;
    }
}
