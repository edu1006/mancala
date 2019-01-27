package br.com.petrim.lich.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;
import java.util.stream.Stream;

public enum AgentTypeConnEnum {

    SOCKET (1),
    REST (2);

    private Integer id;

    AgentTypeConnEnum(Integer id) {
        this.id = id;
    }

    @JsonCreator
    public static AgentTypeConnEnum valueOfId(Integer id) {
        Optional<AgentTypeConnEnum> optional = Stream.of(values())
                .filter(type -> type.id.compareTo(id) == 0)
                .findFirst();

        return (optional.isPresent()) ? optional.get() : null;
    }

    @JsonValue
    public Integer getId() {
        return id;
    }

    @Converter
    public static class Mapper implements AttributeConverter<AgentTypeConnEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(AgentTypeConnEnum agentTypeConnEnum) {
            return agentTypeConnEnum.getId();
        }

        @Override
        public AgentTypeConnEnum convertToEntityAttribute(Integer integer) {
            return AgentTypeConnEnum.valueOfId(integer);
        }
    }
}
