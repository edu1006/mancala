package br.com.petrim.lich.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Optional;
import java.util.stream.Stream;

public enum TypeTransactionEnum {

    INSERT (1, "Insert"),

    UPDATE (2, "Update"),

    DELETE (3, "Delete");

    private Integer id;
    private String description;

    /**
     * Instancia um novo tipo transacao enum.
     *
     */
    TypeTransactionEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static TypeTransactionEnum valueOfId(Integer id) {
        Optional<TypeTransactionEnum> optional = Stream.of(values())
                .filter(type -> type.getId().equals(id))
                .findFirst();

        return (optional.isPresent()) ? optional.get() : null;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Converter
    public static class Mapper implements AttributeConverter<TypeTransactionEnum, Integer> {

        @Override
        public Integer convertToDatabaseColumn(TypeTransactionEnum typeTransactionEnum) {
            return typeTransactionEnum.getId();
        }

        @Override
        public TypeTransactionEnum convertToEntityAttribute(Integer integer) {
            return TypeTransactionEnum.valueOfId(integer);
        }
    }
}
