package br.com.petrim.lich.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;
import java.util.stream.Stream;

public enum TypeExecutionEnum {

    AUTO ("A"),
    MANUAL ("M");

    private String id;

    TypeExecutionEnum(String id) {
        this.id = id;
    }

    @JsonCreator
    public static TypeExecutionEnum valueOfId(String id) {
        Optional<TypeExecutionEnum> optional = Stream.of(values())
                .filter(type -> type.getId().equals(id))
                .findFirst();

        return (optional.isPresent()) ? optional.get() : null;
    }

    @JsonValue
    public String getId() {
        return id;
    }

    @Converter
    public static class Mapper implements AttributeConverter<TypeExecutionEnum, String> {

        @Override
        public String convertToDatabaseColumn(TypeExecutionEnum typeExecutionEnum) {
            return typeExecutionEnum.getId();
        }

        @Override
        public TypeExecutionEnum convertToEntityAttribute(String s) {
            return TypeExecutionEnum.valueOfId(s);
        }
    }
}
