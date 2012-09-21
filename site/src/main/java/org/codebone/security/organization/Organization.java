package org.codebone.security.organization;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.codebone.framework.generic.AbstractModel;
import org.codebone.security.authorities.Authorities;
import org.codebone.security.manager.Manager;


@Entity
public class Organization{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Long idx;
	
	@Column
	private String name = "";
	
	@Column
	private String description = "";
	
	@OneToMany
	@JoinColumn(name="organizationIdx")
	private List<Authorities> authoritiesList;
	
	@OneToMany
	@JoinColumn(name="organizationIdx")
	private List<Manager> managerList;

	@Column
	private Date createDate = new Date();

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Authorities> getAuthoritiesList() {
		return authoritiesList;
	}

	public void setAuthoritiesList(List<Authorities> authoritiesList) {
		this.authoritiesList = authoritiesList;
	}

	public List<Manager> getManagerList() {
		return managerList;
	}

	public void setManagerList(List<Manager> managerList) {
		this.managerList = managerList;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Organization(Long idx, String name, String description,
			List<Authorities> authoritiesList, List<Manager> managerList,
			Date createDate) {
		super();
		this.idx = idx;
		this.name = name;
		this.description = description;
		this.authoritiesList = authoritiesList;
		this.managerList = managerList;
		this.createDate = createDate;
	}

	public Organization() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Organization [idx=" + idx + ", name=" + name + ", description="
				+ description + ", authoritiesList=" + authoritiesList
				+ ", managerList=" + managerList + ", createDate=" + createDate
				+ "]";
	}
	
	
}
