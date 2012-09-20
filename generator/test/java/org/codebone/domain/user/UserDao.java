package org.codebone.domain.user;

import org.codebone.framework.generic.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AbstractDao<User>{
	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}
}
