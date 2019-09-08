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
    @Column(name = "id", length = 20, nullable = false)
    private Long id;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "address", length = 20, nullable = false)
    private String address;

    @Column(name = "port", length = 10, nullable = false)
    private Long port;

    @Convert(converter = StatusEnum.Mapper.class)
    @Column(name = "status", length = 1, nullable = false)
    private StatusEnum status;

    @Convert(converter = AgentTypeEnum.Mapper.class)
    @Column(name = "type", length = 1, nullable = false)
    private AgentTypeEnum type;

    @Convert(converter = AgentTypeConnEnum.Mapper.class)
    @Column(name = "type_connection", length = 1, nullable = false)
    private AgentTypeConnEnum typeConnection;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_insert", nullable = false)
    private Date dateInsert;

    public Agent() {
        // Default constructor
    }

    public Agent(Long id, String address, Long port, AgentTypeConnEnum typeConnection) {
        this.id = id;
        this.address = address;
        this.port = port;
        this.typeConnection = typeConnection;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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
