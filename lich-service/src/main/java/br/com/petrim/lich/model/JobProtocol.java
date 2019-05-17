package br.com.petrim.lich.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "job_protocol")
public class JobProtocol extends AbstractEntity {

    @Id
    @Column(name = "id", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_job_process", length = 20, nullable = false)
    private Long idJobProcess;

    @Column(name = "ds_job_process", length = 20, nullable = false)
    private String dsJobProcess;

    @Column(name = "id_job_execution", length = 20)
    private Long idJobExecution; // Id Spring Job Execution

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_start")
    private Date dateStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "status", length = 20)
    private String status; //Spring Status

    @Column(name = "parentProtocol", length = 20)
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
