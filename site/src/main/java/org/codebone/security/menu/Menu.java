package org.codebone.security.menu;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Menu{
	
	public Menu() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long idx;
	
	@Column
	private String name = "";
	
	@Column
	private String url = "";
	
	@Column
	private Integer priOrder = 0;
	
	@Column
	private Integer subOrder = 0;
	
	/**
	 * 일단은 2단메뉴까지만 생각ㅇㅋ
	 */
	
	@Column(length=1)
	private String isSeparate = "N";
	
	@Column(length=1)
	private String isExternal = "N";
	
	@Column
	private Long managerIdx = new Long(1);

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPriOrder() {
		return priOrder;
	}

	public void setPriOrder(Integer priOrder) {
		this.priOrder = priOrder;
	}

	public Integer getSubOrder() {
		return subOrder;
	}

	public void setSubOrder(Integer subOrder) {
		this.subOrder = subOrder;
	}

	public String getIsSeparate() {
		return isSeparate;
	}

	public void setIsSeparate(String isSeparate) {
		this.isSeparate = isSeparate;
	}

	public String getIsExternal() {
		return isExternal;
	}

	public void setIsExternal(String isExternal) {
		this.isExternal = isExternal;
	}

	public Long getManagerIdx() {
		return managerIdx;
	}

	public void setManagerIdx(Long managerIdx) {
		this.managerIdx = managerIdx;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Menu [idx=" + idx + ", name=" + name + ", url=" + url
				+ ", priOrder=" + priOrder + ", subOrder=" + subOrder
				+ ", isSeparate=" + isSeparate + ", isExternal=" + isExternal
				+ ", managerIdx=" + managerIdx + ", createDate=" + createDate
				+ "]";
	}

	public Menu(Long idx, String name, String url, Integer priOrder,
			Integer subOrder, String isSeparate, String isExternal,
			Long managerIdx, Date createDate) {
		super();
		this.idx = idx;
		this.name = name;
		this.url = url;
		this.priOrder = priOrder;
		this.subOrder = subOrder;
		this.isSeparate = isSeparate;
		this.isExternal = isExternal;
		this.managerIdx = managerIdx;
		this.createDate = createDate;
	}
}
