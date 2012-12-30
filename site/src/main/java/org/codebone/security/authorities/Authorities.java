package org.codebone.security.authorities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Authorities{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Long idx;
	
	@Column
	private Long organizationIdx;
	
	@Column
	private String authority = "";
	
	@Column
	private Date createDate = new Date();

	@Override
	public String toString() {
		return "Authorities [idx=" + idx + ", organizationIdx="
				+ organizationIdx + ", authority=" + authority
				+ ", createDate=" + createDate + "]";
	}

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public Long getOrganizationIdx() {
		return organizationIdx;
	}

	public void setOrganizationIdx(Long organizationIdx) {
		this.organizationIdx = organizationIdx;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Authorities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Authorities(Long idx, Long organizationIdx, String authority,
			Date createDate) {
		super();
		this.idx = idx;
		this.organizationIdx = organizationIdx;
		this.authority = authority;
		this.createDate = createDate;
	}
	
}
