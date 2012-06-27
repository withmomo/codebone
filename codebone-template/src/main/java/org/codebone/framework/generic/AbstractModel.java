package org.codebone.framework.generic;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class AbstractModel implements Serializable{
	
	@Column
	protected Date createdDate = new Date();
	
	@Column
	protected Date updatedDate = new Date();

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public AbstractModel(Date createdDate, Date updatedDate) {
		super();
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public AbstractModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "AbstractModel [createdDate=" + createdDate + ", updatedDate="
				+ updatedDate + "]";
	}
}
