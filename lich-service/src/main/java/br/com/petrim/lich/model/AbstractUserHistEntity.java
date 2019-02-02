package br.com.petrim.lich.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractUserHistEntity implements AbstractEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_insert")
    private Date dateInsert;

    @Column(name = "user_insert")
    private Long userInsert;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_update")
    private Date dateUpdate;

    @Column(name = "user_update")
    private Long userUpdate;

    public Date getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(Date dateInsert) {
        this.dateInsert = dateInsert;
    }

    public Long getUserInsert() {
        return userInsert;
    }

    public void setUserInsert(Long userInsert) {
        this.userInsert = userInsert;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Long getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Long userUpdate) {
        this.userUpdate = userUpdate;
    }
}
