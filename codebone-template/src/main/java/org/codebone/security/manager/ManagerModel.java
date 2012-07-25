package org.codebone.security.manager;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.codebone.framework.generic.AbstractModel;


@Entity
public class ManagerModel extends AbstractModel{
	
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
	private Long groupIdx;
	
	@Column
	private String name = "";

	public ManagerModel(Date createdDate, Date updatedDate, Long idx,
			String id, String password, String email, String phoneNumber,
			Boolean enabled, Long groupIdx, String name) {
		super(createdDate, updatedDate);
		this.idx = idx;
		this.id = id;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.enabled = enabled;
		this.groupIdx = groupIdx;
		this.name = name;
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

	public Long getGroupIdx() {
		return groupIdx;
	}

	public void setGroupIdx(Long groupIdx) {
		this.groupIdx = groupIdx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ManagerModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ManagerModel [idx=" + idx + ", id=" + id + ", password="
				+ password + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", enabled=" + enabled + ", groupIdx=" + groupIdx
				+ ", name=" + name + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + "]";
	}
	
	
}
