package br.com.petrim.lich.model;

import br.com.petrim.lich.enums.StatusEnum;
import br.com.petrim.lich.enums.YesNoEnum;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "parameter")
public class Parameter extends AbstractUserHistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "is_password")
    @Convert(converter = YesNoEnum.Mapper.class)
    private YesNoEnum password;

    @Column(name = "status")
    @Convert(converter = StatusEnum.Mapper.class)
    private StatusEnum status;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public YesNoEnum getPassword() {
        return password;
    }

    public void setPassword(YesNoEnum password) {
        this.password = password;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }
}
