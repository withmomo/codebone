package org.codebone.security.authorities;

import org.codebone.framework.generic.AbstractDao;
import org.codebone.framework.generic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class AuthoritiesService extends AbstractService<AuthoritiesModel> {
	
	@Autowired
	private AuthoritiesDao dao;

	@Override
	public AbstractDao getDao() {
		return dao;
	}
}
