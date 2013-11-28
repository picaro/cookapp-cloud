package com.op.cookcloud.model.base;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "circle")
public class Circle{

    @Id
    @GeneratedValue
    private Long id;

    private String name;
	
    private String note;

	public void setNote(String note)
	{
		this.note = note;
	}

	public String getNote()
	{
		return note;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
