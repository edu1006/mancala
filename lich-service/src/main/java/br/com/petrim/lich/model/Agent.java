package br.com.petrim.lich.model;

import br.com.petrim.lich.enums.AgentTypeConnEnum;
import br.com.petrim.lich.enums.AgentTypeEnum;
import br.com.petrim.lich.enums.StatusEnum;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "agent")
public class Agent extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "port")
    private Long port;

    @Convert(converter = StatusEnum.Mapper.class)
    @Column(name = "status", length = 1)
    private StatusEnum status;

    @Convert(converter = AgentTypeEnum.Mapper.class)
    @Column(name = "type", length = 1)
    private AgentTypeEnum type;

    @Convert(converter = AgentTypeConnEnum.Mapper.class)
    @Column(name = "type_connection", length = 1)
    private AgentTypeConnEnum typeConnection;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_insert")
    private Date dateInsert;

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getPort() {
        return port;
    }

    public void setPort(Long port) {
        this.port = port;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public AgentTypeEnum getType() {
        return type;
    }

    public void setType(AgentTypeEnum type) {
        this.type = type;
    }

    public AgentTypeConnEnum getTypeConnection() {
        return typeConnection;
    }

    public void setTypeConnection(AgentTypeConnEnum typeConnection) {
        this.typeConnection = typeConnection;
    }

    public Date getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(Date dateInsert) {
        this.dateInsert = dateInsert;
    }
}
