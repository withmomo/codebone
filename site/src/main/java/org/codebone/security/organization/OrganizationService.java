package org.codebone.security.organization;

import org.codebone.framework.BaseModel;
import org.codebone.framework.generic.AbstractDao;
import org.codebone.framework.generic.AbstractService;
import org.codebone.security.authorities.AuthoritiesService;
import org.codebone.security.manager.Manager;
import org.codebone.security.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class OrganizationService extends AbstractService<Organization> {
	
	@Autowired
	private OrganizationDao dao;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Autowired
	private ManagerService managerService;
	
	@Override
	public AbstractDao getDao() {
		return dao;
	}
	
	public BaseModel getAuthorities(Long organizationIdx){
		return authoritiesService.getAuthorities(organizationIdx);
	}
	public boolean isNew(){
		int count = dao.count();
		if(count==0){
			return true;
		}else{
			return false;
		}
	}

	public void addUser(String organizationIdx, String id) {
		Manager manager = (Manager) managerService.readById(id).getData();
		Organization org = (Organization) read(organizationIdx).getData();
		org.addUser(manager);
		dao.update(org);
	}
}
