package org.codebone.security.manager;

import org.codebone.framework.BaseModel;
import org.codebone.framework.SuccessModel;
import org.codebone.framework.generic.AbstractDao;
import org.codebone.framework.generic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class ManagerService extends AbstractService<Manager>{
	
	@Autowired
	private ManagerDao dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public AbstractDao getDao() {
		return dao;
	}
	
	public BaseModel create (Manager model){
		logger.info("create model " + model);
		String encodedPassword = passwordEncoder.encodePassword(model.getPassword(), null);
		model.setPassword(encodedPassword);
		getDao().create(model);
		return new SuccessModel();
	}
	
	public BaseModel update (Manager model){
		logger.info("update model " + model);
		if(!(model.getPassword().equals(""))){
			String encodedPassword = passwordEncoder.encodePassword(model.getPassword(), null);
			model.setPassword(encodedPassword);
		}
		Manager returnModel = (Manager) getDao().update(model);
		return new SuccessModel(returnModel);
	}
	public boolean isNew(){
		int count = dao.count();
		if(count==0){
			return true;
		}else{
			return false;
		}
	}

	public BaseModel readById(String id) {
		logger.info("read model by " + id);
		Manager m = dao.readById(id);
		return new SuccessModel(m);
	}
}
