package br.com.petrim.lich.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;
import java.util.stream.Stream;

public enum WeekDayEnum {

    SUNDAY (1, "type.weekday.sunday"),

    MONDAY (2, "type.weekday.monday"),

    TUESDAY (3, "type.weekday.tuesday"),

    WEDNESDAY (4, "type.weekday.wednesday"),

    THURSDAY (5, "type.weekday.thursday"),

    FRIDAY (6, "type.weekday.friday"),

    SATURDAY (7, "type.weekday.saturday");

    private Integer id;

    private String label;

    WeekDayEnum(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    @JsonCreator
    public static WeekDayEnum valueOfId(Integer id) {
        Optional<WeekDayEnum> optional = Stream.of(values())
                .filter(day -> day.id.equals(id))
                .findFirst();

        return (optional.isPresent()) ? optional.get() : null;
    }

    @JsonValue
    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    @Converter
    public static class Mapper implements AttributeConverter<WeekDayEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(WeekDayEnum weekDayEnum) {
            return weekDayEnum.getId();
        }

        @Override
        public WeekDayEnum convertToEntityAttribute(Integer integer) {
            return WeekDayEnum.valueOfId(integer);
        }
    }
}
