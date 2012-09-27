package org.codebone.security.menu;

import java.util.List;

import org.codebone.framework.generic.AbstractDao;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;


@Repository
public class MenuDao extends AbstractDao<Menu>{

	@Override
	protected Class<Menu> getEntityClass() {
		return Menu.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> listAll(){
		logger.info("read model list");
		Session session = sessionFactory.getCurrentSession();
		List<Menu> list = null;
		list = (List<Menu>) session.createCriteria(getEntityClass())
				.addOrder(Order.asc("priOrder"))
				.addOrder(Order.asc("subOrder"))
				.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> list(){
		logger.info("read model list");
		Session session = sessionFactory.getCurrentSession();
		List<Menu> list = null;
		list = (List<Menu>) session.createCriteria(getEntityClass())
				.addOrder(Order.asc("priOrder"))
				.addOrder(Order.asc("subOrder"))
				.setMaxResults(20)
				.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> list(int page, int row){
		logger.info("read model list - page : "+page+", row : "+row);
		Session session = sessionFactory.getCurrentSession();
		List<Menu> list = null;
		list = (List<Menu>) session.createCriteria(getEntityClass())
				.addOrder(Order.asc("priOrder"))
				.addOrder(Order.asc("subOrder"))
				.setMaxResults(row).setFirstResult(page)
				.list();
		return list;
	}

	public int deleteFamily(Integer priOrder, Integer subOrder) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder query = new StringBuilder();
		query.append("delete from Menu where priOrder = "+priOrder);
		if(subOrder!=0){
			query.append(" and subOrder = "+subOrder);
		}
		return session.createQuery(query.toString()).executeUpdate();
	}
	
	public void changeOrder(Integer priOrder, Integer subOrder, boolean isUp){
		if(priOrder==0){
			return;
		}
		String op, orderName;
		Integer order;
		if(isUp){
			op = "-";
		}else{
			op = "+";
		}
		if(subOrder==0){
			orderName = "priOrder";
			order = priOrder;
		}else{
			orderName = "subOrder";
			order = subOrder;
		}
		
		
		Session session = sessionFactory.getCurrentSession();
		StringBuilder query = new StringBuilder();
		query.append("update Menu");
		query.append(" set " + orderName + " = -1");
		query.append(" where " + orderName+ " = " + order + op + "1");
		if(subOrder!=0){
			query.append(" and priOrder = "+priOrder);
		}
		logger.info(query.toString());
		//옮길 대상 위치에 있는 메뉴들을 -1로 옮김. 옮길 위치의 메뉴 order가 비게 됨.
		session.createQuery(query.toString()).executeUpdate();
		
		query = new StringBuilder();
		query.append("update Menu");
		query.append(" set " + orderName + " = " +orderName +op + "1");
		query.append(" where " + orderName+ " = "+ order);
		if(subOrder!=0){
			query.append(" and priOrder = "+priOrder);
		}
		logger.info(query.toString());
		//메뉴 실제 옮김
		session.createQuery(query.toString()).executeUpdate();
		
		if(isUp){
			op = "+";
		}else{
			op = "-";
		}
		query = new StringBuilder();
		query.append("update Menu");
		query.append(" set " + orderName + " = " +order);
		query.append(" where " + orderName+ " = -1");
		if(subOrder!=0){
			query.append(" and priOrder = "+priOrder);
		}
		logger.info(query.toString());
		session.createQuery(query.toString()).executeUpdate();
	}
	
	/**
	 * 서브메뉴를 메인메뉴로 격상시켜주는 메소드
	 * @param priOrder
	 * @param idx
	 */
	public void LevelUp(Integer priOrder, Long idx){
		if(priOrder==0){
			return;
		}
		Session session = sessionFactory.getCurrentSession();
		StringBuilder query = new StringBuilder();
		query.append("update Menu");
		query.append(" set priOrder = priOrder + 1");
		query.append(" where priOrder > "+priOrder);
		logger.info(query.toString());
		//서브메뉴가 메인메뉴로 들어갈 공간을 만들기 위해
		//그보다 아래에 있는 메뉴들의 order값을 전부 1 더함.
		session.createQuery(query.toString()).executeUpdate();
		
		query = new StringBuilder();
		query.append("update Menu");
		query.append(" set subOrder = 0,");
		query.append(" priOrder = priOrder + 1");
		query.append(" where idx = " + idx);
		logger.info(query.toString());
		// 해당 메뉴를 메인메뉴로 격상시킴
		session.createQuery(query.toString()).executeUpdate();
	}
	
	/**
	 * 메인메뉴를 자신 바로 윗 메뉴의 서브메뉴로 격하시키는 메소드
	 * @param priOrder
	 * @param idx
	 */
	public void LevelDown(Integer priOrder, Long idx){
		Session session = sessionFactory.getCurrentSession();
		StringBuilder query = new StringBuilder();
		query.append("update Menu");
		query.append(" set subOrder = subOrder + 1");
		query.append(" where priOrder = "+(priOrder-1));
		query.append(" and subOrder != 0");
		logger.info(query.toString());
		//바로 위 메인메뉴에 딸린 모든 서브메뉴들의 순서를 하나씩 내림
		session.createQuery(query.toString()).executeUpdate();
		
		query = new StringBuilder();
		query.append("update Menu");
		query.append(" set subOrder = 1,");
		query.append(" priOrder = priOrder - 1");
		query.append(" where idx = " + idx);
		logger.info(query.toString());
		// 해당 메뉴를 서브메뉴로 격하시킴
		session.createQuery(query.toString()).executeUpdate();
	}
}
