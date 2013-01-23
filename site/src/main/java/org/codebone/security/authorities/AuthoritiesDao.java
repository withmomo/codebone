package org.codebone.security.authorities;

import java.io.Serializable;
import java.util.List;

import org.codebone.framework.generic.AbstractDao;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository
public class AuthoritiesDao extends AbstractDao<Authorities>{

	@Override
	protected Class<Authorities> getEntityClass() {
		return Authorities.class;
	}

	public List<Authorities> getAuthorities(Long organizationIdx) {
		logger.info("read authorities by organizationIdx "+ organizationIdx);
		Session session = sessionFactory.openSession();
		List<Authorities> list = null;
		list = (List<Authorities>) session.createCriteria(getEntityClass())
				.add(Restrictions.eq("organizationIdx", organizationIdx))
				.list();
		return list;
	}
	protected Serializable parse(String key) {
		return new Long(key);
	}
}
