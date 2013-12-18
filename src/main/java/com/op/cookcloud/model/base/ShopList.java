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

    private Integer circleid;

    @Transient
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.REFRESH})
    @JoinTable(name = "slist2circle",
            joinColumns = {@JoinColumn(name = "shoplistid")},
            inverseJoinColumns = {@JoinColumn(name = "circleid")})
    private Set<Circle> circles = new HashSet<Circle>();

    @Transient
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = true)
    private Person person;

    //Transient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoplist")
    private List<Product> productList;

    @Transient
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.REFRESH})
    @JoinTable(name = "shoplist2shop",
            joinColumns = {@JoinColumn(name = "shoplistid")},
            inverseJoinColumns = {@JoinColumn(name = "shopid")})
    private Set<Shop> shops = new HashSet<Shop>();


    @Override
    public String toString(){
        return note;
    }

}