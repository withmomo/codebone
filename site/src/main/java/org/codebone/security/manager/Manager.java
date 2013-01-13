package org.codebone.security.manager;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Manager{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Long idx;
	
	@Column
	private String id = "";
	
	@Column
	private String password = "";
	
	@Column
	private String email = "";
	
	@Column
	private String phoneNumber = "";
	
	@Column
	private Boolean enabled = true;
	
	@Column
	private Long organizationIdx;
	
	@Column
	private String name = "";
	
	@Column
	private String picture = null;
	
	@Column
	private Date createDate = new Date();
	
	public Manager() {
		super();
	}
	
	public Manager(Long idx, String id, String password, String email,
			String phoneNumber, Boolean enabled, Long organizationIdx,
			String name, Date createDate) {
		super();
		this.idx = idx;
		this.id = id;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.enabled = enabled;
		this.organizationIdx = organizationIdx;
		this.name = name;
		this.createDate = createDate;
	}
	
	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Long getOrganizationIdx() {
		return organizationIdx;
	}

	public void setOrganizationIdx(Long organizationIdx) {
		this.organizationIdx = organizationIdx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Manager [idx=" + idx + ", id=" + id + ", password=" + password
				+ ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", enabled=" + enabled + ", organizationIdx="
				+ organizationIdx + ", name=" + name + ", createDate="
				+ createDate + "]";
	}
}
