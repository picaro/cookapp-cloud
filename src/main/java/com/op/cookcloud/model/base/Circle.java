package com.op.cookcloud.model.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.enunciate.json.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "circle")
public @Data class Circle extends EntityWithId  {

    private String name;

    private String note;

    @JsonIgnore
    @ManyToMany(mappedBy="circles")
    private Set<ShopList> shopLists = new HashSet<ShopList>();

    @JsonIgnore
    @ManyToMany(mappedBy="circles")
    private Set<Person> persons = new HashSet<Person>();

}
