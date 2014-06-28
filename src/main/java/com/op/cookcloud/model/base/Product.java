package com.op.cookcloud.model.base;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
public class Product extends EntityWithId  {

    private static final long serialVersionUID = 2031603586369405652L;

    private String note;

    private String name;

    private Integer shoplistid;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean crossed;

    //Transient
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoplistid", updatable = false, insertable = false, nullable = false)
    private ShopList shoplist;


}