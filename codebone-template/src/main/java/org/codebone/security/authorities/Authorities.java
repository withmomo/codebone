package org.codebone.security.authorities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codebone.framework.generic.AbstractModel;


@Entity
@Table
public class Authorities extends AbstractModel{
	
	@Column
	@Id
	private Long idx;
	
	@Column
	private Long groupIdx;
	
	@Column
	private String authority;

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public Long getGroupIdx() {
		return groupIdx;
	}

	public void setGroupIdx(Long groupIdx) {
		this.groupIdx = groupIdx;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Authorities() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Authorities(Date createdDate, Date updatedDate) {
		super(createdDate, updatedDate);
		// TODO Auto-generated constructor stub
	}

	public Authorities(Date createdDate, Date updatedDate, Long idx,
			Long groupIdx, String authority) {
		super(createdDate, updatedDate);
		this.idx = idx;
		this.groupIdx = groupIdx;
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "AuthoritiesModel [idx=" + idx + ", groupIdx=" + groupIdx
				+ ", authority=" + authority + "]";
	}
}
