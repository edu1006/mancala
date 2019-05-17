package br.com.petrim.lich.model;

import javax.persistence.Transient;
import java.io.Serializable;

public abstract class AbstractEntity implements Serializable {

    @Transient
    private boolean audit = true;

    public abstract Object getId();

    public void noAudit() {
        this.audit = false;
    }

    public boolean isAudit() {
        return this.audit;
    }

}
