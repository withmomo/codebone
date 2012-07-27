package org.codebone.security.organization;

import org.codebone.framework.generic.AbstractDao;
import org.springframework.stereotype.Repository;


@Repository
public class OrganizationDao extends AbstractDao<Organization>{

	@Override
	protected Class<Organization> getEntityClass() {
		return Organization.class;
	}
}
