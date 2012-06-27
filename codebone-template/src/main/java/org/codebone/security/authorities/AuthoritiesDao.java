package org.codebone.security.authorities;

import org.codebone.framework.generic.AbstractDao;
import org.springframework.stereotype.Repository;


@Repository
public class AuthoritiesDao extends AbstractDao<AuthoritiesModel>{

	@Override
	protected Class<AuthoritiesModel> getEntityClass() {
		return AuthoritiesModel.class;
	}
}
