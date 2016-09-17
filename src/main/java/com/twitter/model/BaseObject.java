package com.twitter.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Base class for all the models. 
 * It contains the common fields that is shared between other models.
 */
public abstract class BaseObject implements Cloneable, Serializable {

	private static final long serialVersionUID = 145433353L;
	
	/**
	 * id: Unique id for the object.
	 */
	private Integer id;
	
	/**
	 * UserId: UserId that is stored in the Corporate DB.
	 */
	private Integer userId;
	
	/**
	 *  This captures the time this object is created in the DB
	 */
	private Date createdDate;
	

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}


}
