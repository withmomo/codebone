package org.codebone.security.organization;

import java.io.Serializable;

import org.codebone.framework.generic.AbstractDao;
import org.springframework.stereotype.Repository;


@Repository
public class OrganizationDao extends AbstractDao<Organization>{

	@Override
	protected Class<Organization> getEntityClass() {
		return Organization.class;
	}
	protected Serializable parse(String key) {
		return new Long(key);
	}
}
