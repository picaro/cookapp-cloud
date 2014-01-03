package com.op.cookcloud.model.base;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "person")
public
@Data
class Person extends EntityWithId {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String phone;
    @NotNull
    private String gender;
    private Date dob;

    private Date date_registration;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean enabled;

    @Transient
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<ShopList> shopLists;

    @Transient
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<UserSettings> settingsList;

    @Transient
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
    @JoinTable(name = "user2circle",
            joinColumns = {@JoinColumn(name = "userid")},
            inverseJoinColumns = {@JoinColumn(name = "circleid")})
    private Set<Circle> circles = new HashSet<Circle>();

    @Override
    public String toString() {
        return firstName;
    }
}