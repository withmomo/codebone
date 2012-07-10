package org.codebone.security.group;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.codebone.framework.generic.AbstractModel;
import org.codebone.security.authorities.AuthoritiesModel;
import org.codebone.security.manager.ManagerModel;


@Entity
public class GroupModel extends AbstractModel{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private Long idx;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@ManyToMany(targetEntity=AuthoritiesModel.class)
	@JoinColumn(name="idx")
	private List<AuthoritiesModel> authoritiesList;
}
