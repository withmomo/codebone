package org.codebone.security.group;

import org.codebone.framework.generic.AbstractDao;
import org.codebone.framework.generic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class GroupService extends AbstractService<GroupModel> {
	
	@Autowired
	private GrouprDao dao;
	
	@Override
	public AbstractDao getDao() {
		return dao;
	}
}
