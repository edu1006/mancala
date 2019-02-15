package br.com.petrim.lich.vo;

import br.com.petrim.lich.enums.StatusEnum;

public class JobProcessStatusVo {

    private Long idJob;
    private StatusEnum status;

    public Long getIdJob() {
        return idJob;
    }

    public void setIdJob(Long idJob) {
        this.idJob = idJob;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
