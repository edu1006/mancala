package br.com.petrim.lich.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;
import java.util.stream.Stream;

public enum YesNoEnum {

    YES(1),
    NO(0);

    private Integer id;

    YesNoEnum(Integer id) {
        this.id = id;
    }

    @JsonCreator
    public static YesNoEnum valueOfId(Integer id) {
        Optional<YesNoEnum> optional = Stream.of(values())
                .filter(e -> e.getId().equals(id))
                .findFirst();

        return (optional.isPresent()) ? optional.get() : null;
    }

    @JsonValue
    public Integer getId() {
        return id;
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
