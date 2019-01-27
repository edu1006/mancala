package br.com.petrim.lich.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;
import java.util.stream.Stream;

public enum StatusEnum {

    ENABLED (1),
    DISABLED (0);

    private Integer id;

    StatusEnum(Integer id) {
        this.id = id;
    }

    @JsonCreator
    public static StatusEnum valueOfId(Integer id) {
        Optional<StatusEnum> optional = Stream.of(values())
                .filter(s -> s.id.compareTo(id) == 0)
                .findFirst();

        return (optional.isPresent()) ? optional.get() : null;
    }

    @JsonValue
    public Integer getId() {
        return id;
    }

    @Converter
    public static class Mapper implements AttributeConverter<StatusEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(StatusEnum statusEnum) {
            return statusEnum.getId();
        }

        @Override
        public StatusEnum convertToEntityAttribute(Integer integer) {
            return StatusEnum.valueOfId(integer);
        }
    }
}
