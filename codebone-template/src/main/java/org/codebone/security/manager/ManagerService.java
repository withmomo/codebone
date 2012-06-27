package org.codebone.security.manager;

import org.codebone.framework.generic.AbstractDao;
import org.codebone.framework.generic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class ManagerService extends AbstractService<ManagerModel> {
	
	@Autowired
	private ManagerDao dao;
	
	@Override
	public AbstractDao getDao() {
		return dao;
	}
}
