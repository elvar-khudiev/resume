/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findBySurname", query = "SELECT u FROM User u WHERE u.surname = :surname"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByPhone", query = "SELECT u FROM User u WHERE u.phone = :phone"),
    @NamedQuery(name = "User.findByAddress", query = "SELECT u FROM User u WHERE u.address = :address"),
    @NamedQuery(name = "User.findByBirthdate", query = "SELECT u FROM User u WHERE u.birthdate = :birthdate")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "email", unique = true)
    private String email;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "phone")
    private String phone;
    @Lob
    @Column(name = "profile_description")
    private String profileDescription;
    @Column(name = "address")
    private String address;
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<UserSkill> userSkillList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<EmploymentHistory> employmentHistoryList;
    @JoinColumn(name = "authority_id", referencedColumnName = "id")
    @ManyToOne
    private Authority authorityId;
    @JoinColumn(name = "nationality_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Country nationalityId;
    @JoinColumn(name = "birthplace_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Country birthplaceId;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String surname, String email, String password, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public User(Integer id, String name, String surname, String email, String password, String phone, String profileDescription, String address, Date birthdate, List<UserSkill> userSkillList, List<EmploymentHistory> employmentHistoryList, Authority authorityId, Country nationalityId, Country birthplaceId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.profileDescription = profileDescription;
        this.address = address;
        this.birthdate = birthdate;
        this.userSkillList = userSkillList;
        this.employmentHistoryList = employmentHistoryList;
        this.authorityId = authorityId;
        this.nationalityId = nationalityId;
        this.birthplaceId = birthplaceId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @XmlTransient
    public List<UserSkill> getUserSkillList() {
        return userSkillList;
    }

    public void setUserSkillList(List<UserSkill> userSkillList) {
        this.userSkillList = userSkillList;
    }

    @XmlTransient
    public List<EmploymentHistory> getEmploymentHistoryList() {
        return employmentHistoryList;
    }

    public void setEmploymentHistoryList(List<EmploymentHistory> employmentHistoryList) {
        this.employmentHistoryList = employmentHistoryList;
    }

    public Authority getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Authority authorityId) {
        this.authorityId = authorityId;
    }

    public Country getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Country nationalityId) {
        this.nationalityId = nationalityId;
    }

    public Country getBirthplaceId() {
        return birthplaceId;
    }

    public void setBirthplaceId(Country birthplaceId) {
        this.birthplaceId = birthplaceId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name=" + name
                + ", surname=" + surname
                + ", email=" + email
                + ", password=" + password
                + ", phone=" + phone
                + ", profileDescription=" + profileDescription
                + ", address=" + address
                + ", birthdate=" + birthdate
                + ", authorityId=" + authorityId
                + ", nationalityId=" + nationalityId
                + ", birthplaceId=" + birthplaceId + '}';
    }
}
