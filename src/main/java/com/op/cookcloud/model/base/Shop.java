package com.op.cookcloud.model.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shop")
public @Data class Shop extends EntityWithId  {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoplistid", nullable = false)
    private ShopList shoplist;
}
