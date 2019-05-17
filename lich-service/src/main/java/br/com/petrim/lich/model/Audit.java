package br.com.petrim.lich.model;

import br.com.petrim.lich.enums.TypeTransactionEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "audit")
public class Audit extends AbstractEntity {

    @Id
    @Column(name = "id", length = 20, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = TypeTransactionEnum.Mapper.class)
    @Column(name = "type_transaction", length = 1, nullable = false)
    private TypeTransactionEnum typeTransaction;

    @Column(name = "date_event", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEvent;

    @Column(name = "id_user", length = 20)
    private Long idUser;

    @Column(name = "json_entity", length = 3000)
    private String jsonEntity;

    @Column(name = "class_entity", length = 200)
    private String classEntity;

    @Column(name = "ip", length = 20)
    private String ip;

    @Column(name = "system_transaction", length = 1)
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
