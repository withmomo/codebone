package org.codebone.security.authorities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codebone.framework.generic.AbstractModel;


@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"principle", "authority"}))
public class AuthoritiesModel extends AbstractModel{
	
	@Column
	@Id
	private Long idx;
	
	@Column
	private Long principle;
	
	@Column
	private String authority;

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public Long getPrinciple() {
		return principle;
	}

	public void setPrinciple(Long principle) {
		this.principle = principle;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "AuthoritiesModel [idx=" + idx + ", principle=" + principle
				+ ", authority=" + authority + "]";
	}

	public AuthoritiesModel(Long idx, Long principle, String authority) {
		super();
		this.idx = idx;
		this.principle = principle;
		this.authority = authority;
	}

	public AuthoritiesModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
