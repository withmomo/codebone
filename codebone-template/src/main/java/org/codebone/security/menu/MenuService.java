package org.codebone.security.menu;

import java.util.List;

import org.codebone.framework.BaseModel;
import org.codebone.framework.FailModel;
import org.codebone.framework.SuccessModel;
import org.codebone.framework.generic.AbstractDao;
import org.codebone.framework.generic.AbstractService;
import org.codebone.security.manager.ManagerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;


@Component
public class MenuService extends AbstractService<MenuModel>{
	
	@Autowired
	private MenuDao dao;
	
	@Override
	public AbstractDao getDao() {
		return dao;
	}
	
	public BaseModel changeOrder(Integer priOrder, Integer subOrder, boolean isUp){
		dao.changeOrder(priOrder, subOrder, isUp);
		return new SuccessModel();
	}
	
	public BaseModel changeLevel(Integer priOrder, Integer subOrder, Long idx){
		if(priOrder==0){
			return new FailModel();
		}else{
			if(subOrder==0){
				dao.LevelDown(priOrder, idx);
			}else{
				dao.LevelUp(priOrder, idx);
			}
		}
		return new SuccessModel();
	}

	public BaseModel deleteFamily(String idx){
		MenuModel menuModel = dao.read(idx);
		int result = dao.deleteFamily(menuModel.getPriOrder(), menuModel.getSubOrder());
		if(result!=0){
			return new SuccessModel();
		}else{
			return new FailModel();
		}
	}
}
