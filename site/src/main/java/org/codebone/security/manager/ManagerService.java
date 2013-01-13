package org.codebone.security.manager;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
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
		setGravatar(model);
		getDao().create(model);
		return new SuccessModel();
	}
	
	public BaseModel update (Manager model){
		logger.info("update model " + model);
		if(!(model.getPassword().equals(""))){
			String encodedPassword = passwordEncoder.encodePassword(model.getPassword(), null);
			model.setPassword(encodedPassword);
		}
		setGravatar(model);
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
	
	public void setGravatar(Manager model) {
		if( StringUtils.isNotBlank(model.getEmail())) {
			String email = model.getEmail().trim().toLowerCase();
			String picture = "http://www.gravatar.com/avatar/"+ DigestUtils.md5Hex(email);
			model.setPicture(picture);
		}
    }
}
