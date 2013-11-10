package com.op.cookcloud.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Document(collection = "products")
public class Product implements Serializable
{

    private int id;
    
    private String name;
    
    private String ean;

    private String upc;

    private String description;
    
    private String country;

    private String size;

    private int countryId;

    private int cookSeconds;

	private String imgURL;

    private String countryCode;

    private String addDate;

    private String lang;

    private List<Comment> commentList;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<Comment> getCommentList() {
        if (commentList == null) commentList = new ArrayList();

        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getCookSeconds() {
		return cookSeconds;
	}

	public void setCookSeconds(int cookSeconds) {
		this.cookSeconds = cookSeconds;
	}

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    /**
     * @return the ean
     */
    public String getEan() {
        return ean;
    }

    /**
     * @param ean the ean to set
     */
    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the countryId
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the countryId to set
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the imgURL
     */
    public String getImgURL() {
        return imgURL;
    }

    /**
     * @param imgURL the imgURL to set
     */
    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
}