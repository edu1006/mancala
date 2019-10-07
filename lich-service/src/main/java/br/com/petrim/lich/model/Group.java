package br.com.petrim.lich.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "access_group")
public class Group extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 20, nullable = false)
    private Long id;

    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "status", length = 1, nullable = false)
    private Integer status;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "access_group_functionality",
        joinColumns = { @JoinColumn(name = "id_access_group", referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name = "id_access_functionality", referencedColumnName = "id") })
    private Set<Functionality> functionalities;

    @Override
    public Long getId() {
        return id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Set<Functionality> getFunctionalities() {
		return functionalities;
	}
    
    public void setFunctionalities(Set<Functionality> functionalities) {
		this.functionalities = functionalities;
	}
}
