package com.op.cookcloud.model.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoplistid", nullable = false)
    @Getter
    @Setter
    private ShopList shoplist;


    @Getter
    @Setter
    private String note;

    @Getter
    @Setter
    private String name;

}