package com.op.cookcloud.model.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usersettings")
public @Data class UserSettings  extends EntityWithId {

    private static final long serialVersionUID = -2094802639410766006L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = true)
    private Person person;


}