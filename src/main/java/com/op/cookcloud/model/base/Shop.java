package com.op.cookcloud.model.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "shop")
public @Data class Shop extends EntityWithId  {

    private String name;

    private String coordinates;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person", nullable = false)
    private Person person;


    @Override
    public String toString(){
        return name;
    }
}
