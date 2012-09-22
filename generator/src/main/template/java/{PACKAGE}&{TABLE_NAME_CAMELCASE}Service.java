package {PACKAGE};

import java.util.List;

import org.apache.log4j.Logger;
import org.codebone.framework.BaseModel;
import org.codebone.framework.SuccessModel;
import org.codebone.framework.generic.AbstractDao;
import org.codebone.framework.generic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class {TABLE_NAME_CAMELCASE}Service{
	
	@Autowired
	private {TABLE_NAME_CAMELCASE}Dao dao;
	
	protected static final Logger logger = Logger.getLogger("service");
	
	public AbstractDao getDao(){
		return dao;
	}
	
	public BaseModel create ({TABLE_NAME_CAMELCASE} {TABLE_NAME}){
		logger.info("create model " + {TABLE_NAME});
		getDao().create({TABLE_NAME});
		return new SuccessModel();
	}
	
	public BaseModel read (String key){
		logger.info("read model by" + key);
		{TABLE_NAME_CAMELCASE} {TABLE_NAME} = ({TABLE_NAME_CAMELCASE}) getDao().read(key);
		return new SuccessModel({TABLE_NAME});
	}
	
	public BaseModel listAll(){
		logger.info("read model list All");
		List<{TABLE_NAME_CAMELCASE}> list = getDao().listAll();
		return new SuccessModel(list);
	}
	
	public BaseModel list(int page){
		page=page-1;
		logger.info("read model list");
		List<{TABLE_NAME_CAMELCASE}> list = getDao().list(page, 20+1);
		if(list.size()==21){
			list.remove(20);
			return new SuccessModel(list, true, getDao().count());
		}
		return new SuccessModel(list, false, getDao().count());
	}
	
	public BaseModel search(String property, String keyword, int page){
		page=page-1;
		logger.info("read model list");
		List<{TABLE_NAME_CAMELCASE}> list = getDao().search(property, keyword, page, 20+1);
		if(list.size()==21){
			list.remove(20);
			return new SuccessModel(list, true, getDao().count());
		}
		return new SuccessModel(list, false, getDao().count());
	}
	
	public BaseModel update ({TABLE_NAME_CAMELCASE} {TABLE_NAME}){
		logger.info("update model " + {TABLE_NAME});
		{TABLE_NAME_CAMELCASE} returnModel = ({TABLE_NAME_CAMELCASE})  getDao().update({TABLE_NAME});
		return new SuccessModel(returnModel);
	}
	
	public BaseModel delete ({TABLE_NAME_CAMELCASE} {TABLE_NAME}){
		logger.info("delete model " + {TABLE_NAME});
		getDao().delete({TABLE_NAME});
		return new SuccessModel();
	}
}
