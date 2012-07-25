package org.codebone.security.manager;

import org.codebone.framework.generic.AbstractDao;
import org.springframework.stereotype.Repository;


@Repository
public class ManagerDao extends AbstractDao<ManagerModel>{

	@Override
	protected Class<ManagerModel> getEntityClass() {
		return ManagerModel.class;
	}
}
