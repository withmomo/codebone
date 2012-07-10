package org.codebone.security.manager;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.codebone.framework.generic.AbstractModel;
import org.codebone.security.group.GroupModel;


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
	
	@ManyToMany(targetEntity = GroupModel.class)
	@JoinColumn(name="idx")
	private List<GroupModel> groupList;
	
	@Column
	private String name = "";	
}
