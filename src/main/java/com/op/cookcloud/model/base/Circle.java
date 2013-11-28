package com.op.cookcloud.model.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "circle")
public class Circle {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String note;

    @Getter
    @Setter
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circle")
    private List<ShopList> shopLists;


}
