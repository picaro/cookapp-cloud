package com.op.cookcloud.model.base;

import com.op.cookcloud.validation.CheckUserUniqueEmail;
import com.op.cookcloud.validation.groups.CreateChecks;
import com.op.cookcloud.validation.groups.UpdateChecks;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "person")
@Data
@GroupSequence({ CreateChecks.class, UpdateChecks.class, Person.class })
public class Person extends EntityWithId {


    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    @CheckUserUniqueEmail(email = "email", errorKey = "email", skipCurrent = true)
    private String email;
    @NotEmpty
    private String password;
    private String phone;
    private String gender;
    private Date dob;

    private Date date_registration;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean enabled;

    @Transient
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<ShopList> shopLists;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "person" , targetEntity = Role.class)
    private List<Role> roles;

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