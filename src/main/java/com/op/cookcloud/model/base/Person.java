package com.op.cookcloud.model.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "person")
public @Data class Person extends EntityWithId {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phone;

    private String gender;

    private Date date_registration;

    private Date dob;

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
    @ManyToMany(fetch = FetchType.LAZY,  cascade = {CascadeType.REFRESH})
    @JoinTable(name = "user2circle",
            joinColumns = {@JoinColumn(name = "userid")},
            inverseJoinColumns = {@JoinColumn(name = "circleid")})
    private Set<Circle> circles = new HashSet<Circle>();

    @Override
    public String toString(){
                 return firstName;
    }
}