package br.com.petrim.lich.enums;

public enum StatusStepExecutionEnum {

    PENDING ("P"),
    STARTED ("I"),
    FINISHED ("C");

    private String status;

    StatusStepExecutionEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
