package br.com.petrim.lich.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractUserHistEntity implements AbstractEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_insert")
    @CreatedDate
    private Date dateInsert;

    @Column(name = "user_insert")
    @CreatedBy
    private Long userInsert;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_update")
    @LastModifiedDate
    private Date dateUpdate;

    @Column(name = "user_update")
    @LastModifiedBy
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
