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
@Document(collection = "comments")
public class Comment implements Serializable {



    private String comment;

    private Integer stars;

    private String email;

    private String datetime;

    private String lang;

    public Comment() {
    }

    public Comment(String comment, String email) {
        this.comment = comment;
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
