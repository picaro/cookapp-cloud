package com.op.cookcloud.model.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<ShopList> shopLists;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<UserSettings> settingsList;
}