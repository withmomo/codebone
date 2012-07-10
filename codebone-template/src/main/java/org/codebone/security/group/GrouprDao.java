package org.codebone.security.group;

import org.codebone.framework.generic.AbstractDao;
import org.springframework.stereotype.Repository;


@Repository
public class GrouprDao extends AbstractDao<GroupModel>{

	@Override
	protected Class<GroupModel> getEntityClass() {
		return GroupModel.class;
	}
}
