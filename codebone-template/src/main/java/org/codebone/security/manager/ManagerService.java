package org.codebone.security.manager;

import org.codebone.framework.BaseModel;
import org.codebone.framework.SuccessModel;
import org.codebone.framework.generic.AbstractDao;
import org.codebone.framework.generic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@PreAuthorize("hasAnyRole('ROLE_ADMIN, ROLE_MANAGER_ADMIN')")
public class ManagerService extends AbstractService<ManagerModel> {
	
	@Autowired
	private ManagerDao dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public AbstractDao getDao() {
		return dao;
	}
	
	@PreAuthorize("hasRole('ROLE_MANAGER_CREATE')")
	public BaseModel create (ManagerModel model){
		logger.info("create model " + model);
		String encodedPassword = passwordEncoder.encodePassword(model.getPassword(), null);
		model.setPassword(encodedPassword);
		getDao().create(model);
		return new SuccessModel();
	}
	
	@PreAuthorize("hasRole('ROLE_MANAGER_UPDATE')")
	public BaseModel update (ManagerModel model){
		logger.info("update model " + model);
		String encodedPassword = passwordEncoder.encodePassword(model.getPassword(), null);
		model.setPassword(encodedPassword);
		ManagerModel returnModel = (ManagerModel) getDao().update(model);
		return new SuccessModel(returnModel);
	}
	
	@PreAuthorize("hasRole('ROLE_MANAGER_DELETE')")
	public BaseModel delete(ManagerModel model){
		return super.delete(model);
	}
}
