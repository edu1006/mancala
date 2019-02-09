package br.com.petrim.lich.model;

import br.com.petrim.lich.enums.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "job_process")
public class JobProcess extends AbstractUserHistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "id_process")
    private String idProcess;

    @Column(name = "description")
    private String description;

    @Convert(converter = StatusEnum.Mapper.class)
    @Column(name = "status", length = 1)
    private StatusEnum status;

    @Convert(converter = TypeExecutionEnum.Mapper.class)
    @Column(name = "type_execution", length = 1)
    private TypeExecutionEnum typeExecution;

    @Convert(converter = PeriodicityEnum.Mapper.class)
    @Column(name = "periodicity", length = 1)
    private PeriodicityEnum periodicity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "periodicity_start_date")
    private Date periodicityStartDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "periodicity_end_date")
    private Date periodicityEndDate;

    @Convert(converter = WeekDayEnum.Mapper.class)
    @Column(name = "week_day")
    private WeekDayEnum weekDay;

    @Column(name = "month_day")
    private Integer monthDay;

    @Column(name = "x_value")
    private Integer xValue;

    @Convert(converter = YesNoEnum.Mapper.class)
    @Column(name = "inner_job", length = 1)
    private YesNoEnum innerJob;

    @Convert(converter = YesNoEnum.Mapper.class)
    @Column(name = "inner_parallel", length = 1)
    private YesNoEnum innerParallel;

    @Column(name = "tags")
    private String tags;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idJobProcess")
    private Set<StepProcess> stepsProcesses;

    @Override
    public Long getId() {
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(String idProcess) {
        this.idProcess = idProcess;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public TypeExecutionEnum getTypeExecution() {
        return typeExecution;
    }

    public void setTypeExecution(TypeExecutionEnum typeExecution) {
        this.typeExecution = typeExecution;
    }

    public PeriodicityEnum getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(PeriodicityEnum periodicity) {
        this.periodicity = periodicity;
    }

    public Date getPeriodicityStartDate() {
        return periodicityStartDate;
    }

    public void setPeriodicityStartDate(Date periodicityStartDate) {
        this.periodicityStartDate = periodicityStartDate;
    }

    public Date getPeriodicityEndDate() {
        return periodicityEndDate;
    }

    public void setPeriodicityEndDate(Date periodicityEndDate) {
        this.periodicityEndDate = periodicityEndDate;
    }

    public WeekDayEnum getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDayEnum weekDay) {
        this.weekDay = weekDay;
    }

    public Integer getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(Integer monthDay) {
        this.monthDay = monthDay;
    }

    public Integer getxValue() {
        return xValue;
    }

    public void setxValue(Integer xValue) {
        this.xValue = xValue;
    }

    public YesNoEnum getInnerJob() {
        return innerJob;
    }

    public void setInnerJob(YesNoEnum innerJob) {
        this.innerJob = innerJob;
    }

    public YesNoEnum getInnerParallel() {
        return innerParallel;
    }

    public void setInnerParallel(YesNoEnum innerParallel) {
        this.innerParallel = innerParallel;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Set<StepProcess> getStepsProcesses() {
        return stepsProcesses;
    }

    public void setStepsProcesses(Set<StepProcess> stepsProcesses) {
        this.stepsProcesses = stepsProcesses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobProcess that = (JobProcess) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idProcess, that.idProcess);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idProcess);
    }
}
