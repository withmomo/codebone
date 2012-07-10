package org.codebone.security.authorities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codebone.framework.generic.AbstractModel;
import org.codebone.security.group.GroupModel;


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
}
