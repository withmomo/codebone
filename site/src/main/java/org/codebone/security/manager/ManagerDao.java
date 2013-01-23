package org.codebone.security.manager;

import java.io.Serializable;

import org.codebone.framework.generic.AbstractDao;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


@Repository
public class ManagerDao extends AbstractDao<Manager>{

	@Override
	protected Class<Manager> getEntityClass() {
		return Manager.class;
	}
	
	public Manager readById(String id) {
		logger.info("read model by" + id);
		Session session = sessionFactory.openSession();
		Manager manager = (Manager) session.createCriteria(Manager.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		return manager;
	}
	protected Serializable parse(String key) {
		return new Long(key);
	}
}
