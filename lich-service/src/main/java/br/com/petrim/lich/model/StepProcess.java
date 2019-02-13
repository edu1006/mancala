package br.com.petrim.lich.model;

import br.com.petrim.lich.enums.StatusEnum;
import br.com.petrim.lich.enums.TypeStepProcessEnum;
import br.com.petrim.lich.enums.YesNoEnum;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "step_process")
public class StepProcess extends AbstractUserHistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "id_step")
    private String idStep;

    @Column(name = "order_step")
    private Integer order;

    @Column(name = "type_step")
    @Convert(converter = TypeStepProcessEnum.Mapper.class)
    private TypeStepProcessEnum type;

    @Column(name = "script_batch")
    private String scriptBatch;

    @Column(name = "command_batch")
    private String commandBatch;

    @Column(name = "script_bash")
    private String scriptBash;

    @Column(name = "command_bash")
    private String commandBash;

    @Column(name = "jar_path")
    private String jarPath;

    @Column(name = "rest_url")
    private String restUrl;

    @Column(name = "rest_post_parameter")
    private String restPostParameter;

    @Column(name = "rest_type")
    private String restType;

    @Column(name = "rest_link_attribute")
    private String restLinkAttribute;

    @Column(name = "rest_status_attribute")
    private String restStatusAttribute;

    @Column(name ="rest_log_attribute")
    private String restLogAttribute;

    @Column(name = "rest_status_value_ok")
    private String restStatusValueOk;

    @Column(name = "rest_status_value_error")
    private String restStatusValueError;

    @Column(name = "log_path")
    private String logPath;

    @Column(name = "log_name")
    private String logName;

    @Column(name = "continue_if_error")
    @Convert(converter = YesNoEnum.Mapper.class)
    private YesNoEnum continueIfError;

    @Column(name = "ask_to_continue")
    @Convert(converter = YesNoEnum.Mapper.class)
    private YesNoEnum askToContinue;

    @Column(name = "ask_to_continue_message")
    private String askToContinueMessage;

    @Column(name = "time_sleep")
    private String timeSleep;

    @Column(name = "path_origin")
    private String pathOrigin;

    @Column(name = "path_destiny")
    private String pathDestiny;

    @Column(name = "file_pattern")
    private String filePattern;

    @Column(name = "id_job_process")
    private Long idJobProcess;

    @Column(name = "id_agent")
    private Long idAgent;

    @Column(name = "id_inner_job_process")
    private Long idInnerJobProcess;

    @Convert(converter = StatusEnum.Mapper.class)
    @Column(name = "status")
    private StatusEnum status;

    @Column(name = "id_step_parallel")
    private Long idStepParallel;

    @OneToMany(mappedBy = "idStepParallel", fetch = FetchType.LAZY)
    private Set<StepProcess> stepsParallels;

    @Override
    public Long getId() {
        return this.id;
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

    public String getIdStep() {
        return idStep;
    }

    public void setIdStep(String idStep) {
        this.idStep = idStep;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public TypeStepProcessEnum getType() {
        return type;
    }

    public void setType(TypeStepProcessEnum type) {
        this.type = type;
    }

    public String getScriptBatch() {
        return scriptBatch;
    }

    public void setScriptBatch(String scriptBatch) {
        this.scriptBatch = scriptBatch;
    }

    public String getCommandBatch() {
        return commandBatch;
    }

    public void setCommandBatch(String commandBatch) {
        this.commandBatch = commandBatch;
    }

    public String getScriptBash() {
        return scriptBash;
    }

    public void setScriptBash(String scriptBash) {
        this.scriptBash = scriptBash;
    }

    public String getCommandBash() {
        return commandBash;
    }

    public void setCommandBash(String commandBash) {
        this.commandBash = commandBash;
    }

    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath;
    }

    public String getRestUrl() {
        return restUrl;
    }

    public void setRestUrl(String restUrl) {
        this.restUrl = restUrl;
    }

    public String getRestPostParameter() {
        return restPostParameter;
    }

    public void setRestPostParameter(String restPostParameter) {
        this.restPostParameter = restPostParameter;
    }

    public String getRestType() {
        return restType;
    }

    public void setRestType(String restType) {
        this.restType = restType;
    }

    public String getRestLinkAttribute() {
        return restLinkAttribute;
    }

    public void setRestLinkAttribute(String restLinkAttribute) {
        this.restLinkAttribute = restLinkAttribute;
    }

    public String getRestStatusAttribute() {
        return restStatusAttribute;
    }

    public void setRestStatusAttribute(String restStatusAttribute) {
        this.restStatusAttribute = restStatusAttribute;
    }

    public String getRestLogAttribute() {
        return restLogAttribute;
    }

    public void setRestLogAttribute(String restLogAttribute) {
        this.restLogAttribute = restLogAttribute;
    }

    public String getRestStatusValueOk() {
        return restStatusValueOk;
    }

    public void setRestStatusValueOk(String restStatusValueOk) {
        this.restStatusValueOk = restStatusValueOk;
    }

    public String getRestStatusValueError() {
        return restStatusValueError;
    }

    public void setRestStatusValueError(String restStatusValueError) {
        this.restStatusValueError = restStatusValueError;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public YesNoEnum getContinueIfError() {
        return continueIfError;
    }

    public void setContinueIfError(YesNoEnum continueIfError) {
        this.continueIfError = continueIfError;
    }

    public YesNoEnum getAskToContinue() {
        return askToContinue;
    }

    public void setAskToContinue(YesNoEnum askToContinue) {
        this.askToContinue = askToContinue;
    }

    public String getAskToContinueMessage() {
        return askToContinueMessage;
    }

    public void setAskToContinueMessage(String askToContinueMessage) {
        this.askToContinueMessage = askToContinueMessage;
    }

    public String getTimeSleep() {
        return timeSleep;
    }

    public void setTimeSleep(String timeSleep) {
        this.timeSleep = timeSleep;
    }

    public String getPathOrigin() {
        return pathOrigin;
    }

    public void setPathOrigin(String pathOrigin) {
        this.pathOrigin = pathOrigin;
    }

    public String getPathDestiny() {
        return pathDestiny;
    }

    public void setPathDestiny(String pathDestiny) {
        this.pathDestiny = pathDestiny;
    }

    public String getFilePattern() {
        return filePattern;
    }

    public void setFilePattern(String filePattern) {
        this.filePattern = filePattern;
    }

    public Long getIdJobProcess() {
        return idJobProcess;
    }

    public void setIdJobProcess(Long idJobProcess) {
        this.idJobProcess = idJobProcess;
    }

    public Long getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(Long idAgent) {
        this.idAgent = idAgent;
    }

    public Long getIdInnerJobProcess() {
        return idInnerJobProcess;
    }

    public void setIdInnerJobProcess(Long idInnerJobProcess) {
        this.idInnerJobProcess = idInnerJobProcess;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Long getIdStepParallel() {
        return idStepParallel;
    }

    public void setIdStepParallel(Long idStepParallel) {
        this.idStepParallel = idStepParallel;
    }

    public Set<StepProcess> getStepsParallels() {
        return stepsParallels;
    }

    public void setStepsParallels(Set<StepProcess> stepsParallels) {
        this.stepsParallels = stepsParallels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StepProcess that = (StepProcess) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(idStep, that.idStep);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, idStep);
    }
}
