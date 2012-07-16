package org.codebone.security.group;

import org.codebone.framework.BaseModel;
import org.codebone.framework.generic.AbstractDao;
import org.codebone.framework.generic.AbstractService;
import org.codebone.security.authorities.AuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class GroupService extends AbstractService<GroupModel> {
	
	@Autowired
	private GrouprDao dao;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Override
	public AbstractDao getDao() {
		return dao;
	}
	
	public BaseModel getAuthorities(Long groupIdx){
		return authoritiesService.getAuthorities(groupIdx);
	}
}
