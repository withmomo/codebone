package org.codebone.security.group;

import org.codebone.framework.generic.AbstractDao;
import org.springframework.stereotype.Repository;


@Repository
public class GrouprDao extends AbstractDao<Group>{

	@Override
	protected Class<Group> getEntityClass() {
		return Group.class;
	}
}
