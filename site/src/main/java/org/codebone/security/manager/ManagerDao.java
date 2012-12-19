package org.codebone.security.manager;

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
		Session session = sessionFactory.getCurrentSession();
		Manager manager = (Manager) session.createCriteria(Manager.class)
				.add(Restrictions.eq("id", id))
				.uniqueResult();
		return manager;
	}
}
