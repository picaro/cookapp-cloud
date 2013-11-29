package com.op.cookcloud.model.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shoplist")
public @Data class ShopList extends EntityWithId  {

    private String note;

    private Date date_created;

    private Date date_kill;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circleid", nullable = false)
    private Circle circle;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    private Person person;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoplist")
    private List<Product> productList;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoplist")
    private List<Shop> shops;


}