package br.com.petrim.lich.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;
import java.util.stream.Stream;

public enum TypeStepProcessEnum {

    /** script bat. */
    SCRIPT_BAT (1, "ScriptBatTasklet", "Script Batch",
            new AgentTypeEnum[]{
                    AgentTypeEnum.LOCALHOST, AgentTypeEnum.WINDOWS
            }),

    /** script bash. */
    SCRIPT_BASH (2, "ScriptBashTasklet", "Script Bash",
            new AgentTypeEnum[]{
                    AgentTypeEnum.LOCALHOST, AgentTypeEnum.LINUX, AgentTypeEnum.UNIX
            }),

    /** script java. */
    SCRIPT_JAVA (4, "ScriptJarTasklet", "Execute Jar",
            new AgentTypeEnum[]{
                    AgentTypeEnum.LOCALHOST, AgentTypeEnum.WINDOWS, AgentTypeEnum.LINUX, AgentTypeEnum.UNIX
            }),

    /** exec job process */
    EXEC_JOB_PROCESS (5, null, "Execute Job",
            new AgentTypeEnum[]{
                    AgentTypeEnum.LOCALHOST, AgentTypeEnum.WINDOWS, AgentTypeEnum.LINUX, AgentTypeEnum.UNIX
            }),

    /** command bash */
    COMMAND_BASH (6, "CommandBashTasklet", "Bash Line Command",
            new AgentTypeEnum[]{
                    AgentTypeEnum.LOCALHOST, AgentTypeEnum.LINUX, AgentTypeEnum.UNIX
            }),

    /** step parallel */
    STEP_PARALLEL (7, "", "Step Parallel",
            new AgentTypeEnum[]{
                    AgentTypeEnum.LOCALHOST, AgentTypeEnum.LINUX, AgentTypeEnum.UNIX, AgentTypeEnum.WINDOWS
            }),

    /** command bash */
    COMMAND_BAT (8, "CommandBatTasklet", "Batch (DOS) Line Command",
            new AgentTypeEnum[]{
                    AgentTypeEnum.LOCALHOST, AgentTypeEnum.WINDOWS
            }),

    /** exec rest ws */
    EXEC_REST_WS (9, "RestWSTasklet", "Rest WS",
            new AgentTypeEnum[]{
                    AgentTypeEnum.LOCALHOST
            }),

    /** exec sleep */
    EXEC_SLEEP (10, "SleepTasklet", "Sleep Task",
            new AgentTypeEnum[]{
                    AgentTypeEnum.LOCALHOST
            }),

    /** exec move files linux */
    EXEC_MOVE_FILES_LINUX (11, "MoveFileLinuxTasklet", "Move Files (Linux)",
            new AgentTypeEnum[]{
                    AgentTypeEnum.LOCALHOST, AgentTypeEnum.LINUX, AgentTypeEnum.UNIX
            }),

    /** exec copy files linux */
    COPY_MOVE_FILES_LINUX (13, "CopyFileLinuxTasklet", "Copy Files (Linux)",
            new AgentTypeEnum[]{
                    AgentTypeEnum.LOCALHOST, AgentTypeEnum.LINUX, AgentTypeEnum.UNIX
            });

    /** id. */
    private Integer id;

    /** class name. */
    private String className;

    /** description. */
    private String description;

    /** types agent enum. */
    private AgentTypeEnum[] typesAgentEnum;

    /**
     * Instancia um novo type step process enum.
     *
     * @param id code
     * @param className class name
     * @param description description
     * @param typesAgentEnum type agent enum
     */
    TypeStepProcessEnum(Integer id, String className, String description, AgentTypeEnum[] typesAgentEnum) {
        this.id = id;
        this.className = className;
        this.description = description;
        this.typesAgentEnum = typesAgentEnum;
    }

    @JsonCreator
    public static TypeStepProcessEnum valueOfId(Integer id) {
        Optional<TypeStepProcessEnum> optional = Stream.of(values())
                .filter(type -> type.id.equals(id))
                .findFirst();

        return (optional.isPresent()) ? optional.get() : null;
    }

    @JsonValue
    public Integer getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public String getDescription() {
        return description;
    }

    public AgentTypeEnum[] getTypesAgentEnum() {
        return typesAgentEnum;
    }

    @Converter
    public static class Mapper implements AttributeConverter<TypeStepProcessEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(TypeStepProcessEnum typeStepProcessEnum) {
            return typeStepProcessEnum.id;
        }

        @Override
        public TypeStepProcessEnum convertToEntityAttribute(Integer id) {
            return TypeStepProcessEnum.valueOfId(id);
        }
    }
}
