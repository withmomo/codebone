package org.codebone.security.authorities;

import java.util.List;

import org.codebone.framework.generic.AbstractDao;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository
public class AuthoritiesDao extends AbstractDao<AuthoritiesModel>{

	@Override
	protected Class<AuthoritiesModel> getEntityClass() {
		return AuthoritiesModel.class;
	}

	public List<AuthoritiesModel> getAuthorities(Long groupIdx) {
		logger.info("read authorities by groupIdx "+ groupIdx);
		Session session = sessionFactory.getCurrentSession();
		List<AuthoritiesModel> list = null;
		list = (List<AuthoritiesModel>) session.createCriteria(getEntityClass())
				.add(Restrictions.eq("groupIdx", groupIdx))
				.list();
		return list;
	}
}
