package br.com.petrim.lich.vo;

public class EnumValueVo<I> {

    private I id;
    private String description;

    public EnumValueVo() {}

    public EnumValueVo(I id, String description) {
        this.id = id;
        this.description = description;
    }

    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
