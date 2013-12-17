package com.op.cookcloud.model.base;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shoplist")
public
@Data
class ShopList extends EntityWithId {

    private String note;

    private Date date_created;

    private Date date_kill;

    private String coordinates;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.REFRESH})
    @JoinTable(name = "slist2circle",
            joinColumns = {@JoinColumn(name = "shoplistid")},
            inverseJoinColumns = {@JoinColumn(name = "circleid")})
    private Set<Circle> circles = new HashSet<Circle>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    private Person person;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoplist")
    private List<Product> productList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoplist")
    private List<Shop> shops;

    @Override
    public String toString(){
        return note;
    }

}