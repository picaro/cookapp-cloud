package com.op.cookcloud.model.base;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shoplist")
public class ShopList {

    @Id
    @GeneratedValue
    private Long id;

    //circle
    //user


    private String note;


}