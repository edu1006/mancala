package br.com.petrim.lich.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;
import java.util.stream.Stream;

public enum YesNoEnum {

    YES(1, Boolean.TRUE),
    NO(0, Boolean.FALSE);

    private Integer id;
    private Boolean jsonValue;

    YesNoEnum(Integer id, Boolean jsonValue) {
        this.id = id;
        this.jsonValue = jsonValue;
    }

    public static YesNoEnum valueOfId(Integer id) {
        Optional<YesNoEnum> optional = Stream.of(values())
                .filter(e -> e.getId().equals(id))
                .findFirst();

        return (optional.isPresent()) ? optional.get() : null;
    }

    @JsonCreator
    public static YesNoEnum valueOfJsonValue(Boolean jsonValue) {
        Optional<YesNoEnum> optional = Stream.of(values())
                .filter(e -> e.getJsonValue().equals(jsonValue))
                .findFirst();

        return (optional.isPresent()) ? optional.get() : null;
    }

    public Integer getId() {
        return id;
    }

    @JsonValue
    public Boolean getJsonValue() {
        return jsonValue;
    }

    @Converter
    public static class Mapper implements AttributeConverter<YesNoEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(YesNoEnum yesNoEnum) {
            return yesNoEnum.getId();
        }

        @Override
        public YesNoEnum convertToEntityAttribute(Integer integer) {
            return YesNoEnum.valueOfId(integer);
        }
    }
}
