package org.codebone.security.menu;

import java.util.List;

import org.codebone.framework.BaseModel;
import org.codebone.framework.SuccessModel;
import org.codebone.framework.generic.AbstractDao;
import org.codebone.framework.generic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class MenuService extends AbstractService<MenuModel> {
	
	@Autowired
	private MenuDao dao;
	
	@Override
	public AbstractDao getDao() {
		return dao;
	}
	
	public BaseModel listForMenu(){
		logger.info("read model list All");
		List<MenuModel> list = getDao().listAll();
		return new SuccessModel(list);
	}
}
