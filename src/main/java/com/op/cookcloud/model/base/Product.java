package com.op.cookcloud.model.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "product")
public @Data class Product extends EntityWithId  {

    private String note;

    private String name;

    private Integer shoplistid;

    @Transient
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoplistid", nullable = false)
    private ShopList shoplist;

    @Override
    public String toString(){
        return note;
    }
}