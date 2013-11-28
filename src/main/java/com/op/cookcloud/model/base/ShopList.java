package com.op.cookcloud.model.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shoplist")
public class ShopList {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circleid", nullable = false)
    @Getter
    @Setter
    private Circle circle;


    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", nullable = false)
    private Person person;

    @Getter
    @Setter
    private String note;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoplist")
    private List<Product> productList;


    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shoplist")
    private List<Shop> shops;


}