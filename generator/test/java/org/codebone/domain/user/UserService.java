package org.codebone.domain.user;

import java.util.List;

import org.apache.log4j.Logger;
import org.codebone.framework.BaseModel;
import org.codebone.framework.SuccessModel;
import org.codebone.framework.generic.AbstractDao;
import org.codebone.framework.generic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService{
	
	@Autowired
	private UserDao dao;
	
	protected static final Logger logger = Logger.getLogger("service");
	
	public AbstractDao getDao(){
		return dao;
	}
	
	public BaseModel create (User user){
		logger.info("create model " + user);
		getDao().create(user);
		return new SuccessModel();
	}
	
	public BaseModel read (String key){
		logger.info("read model by" + key);
		User user =  getDao().read(key);
		return new SuccessModel(user);
	}
	
	public BaseModel listAll(){
		logger.info("read model list All");
		List<User> list = getDao().listAll();
		return new SuccessModel(list);
	}
	
	public BaseModel list(int page){
		page=page-1;
		logger.info("read model list");
		List<User> list = getDao().list(page, 20+1);
		if(list.size()==21){
			list.remove(20);
			return new SuccessModel(list, true, getDao().count());
		}
		return new SuccessModel(list, false, getDao().count());
	}
	
	public BaseModel search(String property, String keyword, int page){
		page=page-1;
		logger.info("read model list");
		List<User> list = getDao().search(property, keyword, page, 20+1);
		if(list.size()==21){
			list.remove(20);
			return new SuccessModel(list, true, getDao().count());
		}
		return new SuccessModel(list, false, getDao().count());
	}
	
	public BaseModel update (User user){
		logger.info("update model " + user);
		User returnModel =  getDao().update(user);
		return new SuccessModel(returnModel);
	}
	
	public BaseModel delete (User user){
		logger.info("delete model " + user);
		getDao().delete(user);
		return new SuccessModel();
	}
}
