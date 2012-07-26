package org.codebone.security.authorities;

import java.util.List;

import org.codebone.framework.generic.AbstractDao;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository
public class AuthoritiesDao extends AbstractDao<Authorities>{

	@Override
	protected Class<Authorities> getEntityClass() {
		return Authorities.class;
	}

	public List<Authorities> getAuthorities(Long groupIdx) {
		logger.info("read authorities by groupIdx "+ groupIdx);
		Session session = sessionFactory.getCurrentSession();
		List<Authorities> list = null;
		list = (List<Authorities>) session.createCriteria(getEntityClass())
				.add(Restrictions.eq("groupIdx", groupIdx))
				.list();
		return list;
	}
}
