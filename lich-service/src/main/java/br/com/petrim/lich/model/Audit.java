package br.com.petrim.lich.model;

import br.com.petrim.lich.enums.TypeTransactionEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "audit")
public class Audit implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = TypeTransactionEnum.Mapper.class)
    @Column(name = "type_transaction", length = 1)
    private TypeTransactionEnum typeTransaction;

    @Column(name = "date_event")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEvent;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "json_entity")
    private String jsonEntity;

    @Column(name = "class_entity")
    private String classEntity;

    @Column(name = "ip")
    private String ip;

    @Column(name = "system_transaction")
    private Integer systemTransaction;

    @Override
    public Object getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeTransactionEnum getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(TypeTransactionEnum typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getJsonEntity() {
        return jsonEntity;
    }

    public void setJsonEntity(String jsonEntity) {
        this.jsonEntity = jsonEntity;
    }

    public String getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(String classEntity) {
        this.classEntity = classEntity;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getSystemTransaction() {
        return systemTransaction;
    }

    public void setSystemTransaction(Integer systemTransaction) {
        this.systemTransaction = systemTransaction;
    }
}
