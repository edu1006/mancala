package br.com.petrim.lich.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;
import java.util.stream.Stream;

public enum PeriodicityEnum {

    DE_MINUTOS_EM_MINUTOS (12, "type.periodicity.minute"),

    DE_HORAS_EM_HORAS (13, "type.periodicity.hour"),

    DIARIAMENTE (1, "type.periodicity.daily"),

    SEMANALMENTE (2, "type.periodicity.weekly"),

    MENSALMENTE (3, "type.periodicity.monthly"),

    BIMESTRALMENTE (4, "type.periodicity.bimonthly"),

    TRIMESTRALMENTE (5, "type.periodicity.trimonthly"),

    SEMESTRALMENTE (6, "type.periodicity.semiannually"),

    ANUALMENTE (7, "type.periodicity.annually"),

    DURANTE_FDS (8, "type.periodicity.weekend"),

    TODA_SEMANA_EXCETO_FDS (9, "type.periodicity.weekwithouweekeng"),

    APENAS_DIAS_UTEIS (14, "type.periodicity.usefulday"),

    PRIMEIRO_DIA_ANO (10, "type.periodicity.firstyearday"),

    ULTIMO_DIA_ANO (11, "type.periodicity.lastyearday");

    private Integer id;
    private String label;

    PeriodicityEnum(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    @JsonCreator
    public static PeriodicityEnum valueOfId(Integer id) {
        Optional<PeriodicityEnum> optional = Stream.of(values())
                .filter(p -> p.getId().equals(id))
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
    public static class Mapper implements AttributeConverter<PeriodicityEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(PeriodicityEnum periodicityEnum) {
            return periodicityEnum.getId();
        }

        @Override
        public PeriodicityEnum convertToEntityAttribute(Integer integer) {
            return PeriodicityEnum.valueOfId(integer);
        }
    }
}
