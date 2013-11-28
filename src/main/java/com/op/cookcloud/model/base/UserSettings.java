package com.op.cookcloud.model.base;

import javax.persistence.*;

@Entity
@Table(name = "usersettings")
public class UserSettings {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(mappedBy = "person.id")
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}