package br.com.petrim.lich.vo;

import br.com.petrim.lich.enums.StatusEnum;

public class UserStatusVo {

    private Long id;

    private StatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
