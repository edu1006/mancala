package br.com.petrim.lich.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;
import java.util.stream.Stream;

public enum AgentTypeEnum {

    LINUX (1),
    UNIX (2),
    WINDOWS (3),
    LOCALHOST(9);

    private Integer id;

    AgentTypeEnum(Integer id) {
        this.id = id;
    }

    @JsonCreator
    public static AgentTypeEnum valueOfId(Integer id) {
        Optional<AgentTypeEnum> optional = Stream.of(values())
                .filter(type -> type.id.compareTo(id) == 0)
                .findFirst();

        return (optional.isPresent()) ? optional.get() : null;
    }

    @JsonValue
    public Integer getId() {
        return id;
    }

    @Converter
    public static class Mapper implements AttributeConverter<AgentTypeEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(AgentTypeEnum agentTypeEnum) {
            return agentTypeEnum.getId();
        }

        @Override
        public AgentTypeEnum convertToEntityAttribute(Integer integer) {
            return AgentTypeEnum.valueOfId(integer);
        }
    }
}
