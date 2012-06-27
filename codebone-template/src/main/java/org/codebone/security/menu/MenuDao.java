package org.codebone.security.menu;

import java.util.List;

import org.codebone.framework.generic.AbstractDao;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;


@Repository
public class MenuDao extends AbstractDao<MenuModel>{

	@Override
	protected Class<MenuModel> getEntityClass() {
		return MenuModel.class;
	}
	
	public List<MenuModel> listForMenu(){
		logger.info("read model list");
		Session session = sessionFactory.getCurrentSession();
		List<MenuModel> list = null;
		list = (List<MenuModel>) session.createCriteria(getEntityClass())
				.addOrder(Order.asc("pri_order"))
				.addOrder(Order.asc(" sub_order"))
				.list();
		return list;
	}
}
