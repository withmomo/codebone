package org.codebone.domain.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name="customer")
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer{
	
	public Customer() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long SID = new Long(0);
	
	@Column
	private String Last_Name = "";
	
	@Column
	private String First_Name = "";
	
	
	public Long getSid(){
		return SID;
	}

	public void setSid(Long SID){
		this.SID = SID;
	}
	public String getLastName(){
		return Last_Name;
	}

	public void setLastName(String Last_Name){
		this.Last_Name = Last_Name;
	}
	public String getFirstName(){
		return First_Name;
	}

	public void setFirstName(String First_Name){
		this.First_Name = First_Name;
	}
	
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
