package org.codebone.security.menu;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.codebone.framework.generic.AbstractModel;


@Entity
public class MenuModel extends AbstractModel{
	
	@Column
	@Id
	private Long idx;
	
	@Column
	private String name = "";
	
	@Column
	private String url = "";
	
	@Column
	private Integer pri_order = 0;
	
	@Column
	private Integer sub_order = 0;
	
	/**
	 * 일단은 2단메뉴까지만 생각ㅇㅋ
	 */
	
	@Column(length=1)
	private String isSeperate = "N";
	
	@Column(length=1)
	private String isExternal = "N";
	
	@Column
	private Long managerIdx = new Long(0);

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

	public Integer getPri_order() {
		return pri_order;
	}

	public void setPri_order(Integer pri_order) {
		this.pri_order = pri_order;
	}

	public Integer getSub_order() {
		return sub_order;
	}

	public void setSub_order(Integer sub_order) {
		this.sub_order = sub_order;
	}

	public String getIsSeperate() {
		return isSeperate;
	}

	public void setIsSeperate(String isSeperate) {
		this.isSeperate = isSeperate;
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

	public MenuModel(Date createdDate, Date updatedDate, Long idx, String name,
			String url, Integer pri_order, Integer sub_order,
			String isSeperate, String isExternal, Long managerIdx) {
		super(createdDate, updatedDate);
		this.idx = idx;
		this.name = name;
		this.url = url;
		this.pri_order = pri_order;
		this.sub_order = sub_order;
		this.isSeperate = isSeperate;
		this.isExternal = isExternal;
		this.managerIdx = managerIdx;
	}

	public MenuModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuModel(Date createdDate, Date updatedDate) {
		super(createdDate, updatedDate);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MenuModel [idx=" + idx + ", name=" + name + ", url=" + url
				+ ", pri_order=" + pri_order + ", sub_order=" + sub_order
				+ ", isSeperate=" + isSeperate + ", isExternal=" + isExternal
				+ ", managerIdx=" + managerIdx + ", createdDate=" + createdDate
				+ ", updatedDate=" + updatedDate + "]";
	}
	
}
