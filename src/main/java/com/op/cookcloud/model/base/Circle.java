package com.op.cookcloud.model.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "circle")
public @Data class Circle extends EntityWithId  {

    private String name;

    private String note;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circle")
    private List<ShopList> shopLists;


}
