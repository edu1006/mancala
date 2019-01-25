package br.com.petrim.lich.enums;

public enum StatusEnum {

    ENABLED (1),
    DISABLED (0);

    private Integer id;

    StatusEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
