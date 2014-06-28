package com.op.cookcloud.model.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shop")
public @Data class Shop extends EntityWithId  {

    private static final long serialVersionUID = -5405790156658560523L;

    private String name;

    private String coordinates;

    private Integer userid;

    @JsonIgnore
    @Transient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person", nullable = true)
    private Person person;

    @JsonIgnore
    @Transient
    @ManyToMany(mappedBy="shops")
    private Set<ShopList> shopLists = new HashSet<ShopList>();

    @Override
    public String toString(){
        return name;
    }
}
