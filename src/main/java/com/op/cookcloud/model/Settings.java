package com.op.cookcloud.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: alex3
 * Date: 08.11.13
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
@Document(collection = "settings")
public class Settings implements Serializable {

    private Boolean isUADbSaved;

    public Boolean getUADbSaved() {
        return isUADbSaved;
    }

    public void setUADbSaved(Boolean UADbSaved) {
        isUADbSaved = UADbSaved;
    }
}
