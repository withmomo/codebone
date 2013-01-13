package org.codebone.framework.generic;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public abstract class AbstractDao<M> {
	
	protected static final Logger logger = Logger.getLogger("dao");
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public void create(M m){
		logger.info("create model " + m);
		Session session = sessionFactory.getCurrentSession();
		session.save(m);
		session.flush();
	}
	
	public M read(String key){
		logger.info("read model by" + key);
		Session session = sessionFactory.getCurrentSession();
		M m = (M) session.get(getEntityClass(), Long.parseLong(key));
		return m;
	}
	
	public List<M> listAll(){
		logger.info("read model list");
		Session session = sessionFactory.getCurrentSession();
		List<M> list = null;
		list = (List<M>) session.createCriteria(getEntityClass())
				.list();
		return list;
	}
	
	public List<M> list(int page, int row){
		logger.info("read model list - page : "+page+", row : "+row);
		Session session = sessionFactory.getCurrentSession();
		List<M> list = null;
		list = (List<M>) session.createCriteria(getEntityClass())
				.setMaxResults(row).setFirstResult(page)
				.list();
		return list;
	}
	
	public List<M> search(String property, String keyword, int page, int row){
		logger.info("read model list");
		Session session = sessionFactory.getCurrentSession();
		List<M> list = null;
		list = (List<M>) session.createCriteria(getEntityClass())
				.add(Restrictions.ilike(property, keyword,MatchMode.ANYWHERE))
				.setMaxResults(row).setFirstResult(page)
				.list();
		return list;
	}
	
	public int count(){
		logger.info("read model's count");
		Session session = sessionFactory.getCurrentSession();
		List list = null;
		list = (List) session.createCriteria(getEntityClass())
				.setProjection(Projections.rowCount())
				.list();
		return (Integer) list.get(0);
	}
	
	public M update(M m){
		logger.info("update model " + m);
		Session session = sessionFactory.getCurrentSession();
		session.update(m);
		session.flush();
		return m;
	}
	
	public M merge(M m){
		logger.info("merge model " + m);
		Session session = sessionFactory.getCurrentSession();
		session.merge(m);
		session.flush();
		return m;
	}
	
	public void delete(M m){
		logger.info("delete model " + m);
		Session session = sessionFactory.getCurrentSession();
		session.delete(m);
		session.flush();
	}
	
	public void evict(M m){
		logger.info("evict model " + m);
		Session session = sessionFactory.getCurrentSession();
		session.evict(m);
		session.flush();
	}
	
	protected abstract Class<M> getEntityClass();
}
