package br.com.petrim.lich.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "user")
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "document")
    private String document;

    @Column(name = "registration")
    private String registration;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "cel_phone")
    private Long phoneCel;

    @Column(name = "commercial_phone")
    private Long phoneCommercial;

    @Column(name = "status")
    private Integer status;

    @Column(name = "date_insert")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateInsert;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_access_group",
        joinColumns = { @JoinColumn(name = "id_user", referencedColumnName = "id") },
        inverseJoinColumns = { @JoinColumn(name = "id_access_group", referencedColumnName = "id") })
    private List<Group> groups;

    public User() {

    }

    public User(Long id, String name, String login, Long phone, Long phoneCel, Integer status) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.phone = phone;
        this.phoneCel = phoneCel;
        this.status = status;
    }

    @Override
    public Object getId() {
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getPhoneCel() {
        return phoneCel;
    }

    public void setPhoneCel(Long phoneCel) {
        this.phoneCel = phoneCel;
    }

    public Long getPhoneCommercial() {
        return phoneCommercial;
    }

    public void setPhoneCommercial(Long phoneCommercial) {
        this.phoneCommercial = phoneCommercial;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDateInsert() {
        return dateInsert;
    }

    public void setDateInsert(Date dateInsert) {
        this.dateInsert = dateInsert;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
