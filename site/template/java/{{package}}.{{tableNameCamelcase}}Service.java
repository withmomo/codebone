package {{package}};

import java.util.List;

import org.apache.log4j.Logger;
import org.codebone.framework.BaseModel;
import org.codebone.framework.SuccessModel;
import org.codebone.framework.generic.AbstractDao;
import org.codebone.framework.generic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class {{tableNameCamelcase}}Service{
	
	@Autowired
	private {{tableNameCamelcase}}Dao dao;
	
	protected static final Logger logger = Logger.getLogger({{tableNameCamelcase}}Service.class);
	
	public AbstractDao getDao(){
		return dao;
	}
	
	public BaseModel create ({{tableNameCamelcase}} {{tableNameLowercase}}){
		logger.info("create model " + {{tableNameLowercase}});
		getDao().create({{tableNameLowercase}});
		return new SuccessModel({{tableNameLowercase}});
	}
	
	public BaseModel read (String key){
		logger.info("read model by" + key);
		{{tableNameCamelcase}} {{tableNameLowercase}} = ({{tableNameCamelcase}}) getDao().read(key);
		return new SuccessModel({{tableNameLowercase}});
	}
	
	public BaseModel listAll(){
		logger.info("read model list All");
		List<{{tableNameCamelcase}}> list = getDao().listAll();
		return new SuccessModel(list);
	}
	
	public BaseModel list(int page){
		page=page-1;
		logger.info("read model list");
		List<{{tableNameCamelcase}}> list = getDao().list(page, 20+1);
		if(list.size()==21){
			list.remove(20);
			return new SuccessModel(list, true, getDao().count());
		}
		return new SuccessModel(list, false, getDao().count());
	}
	
	public BaseModel search(String property, String keyword, int page){
		page=page-1;
		logger.info("read model list");
		List<{{tableNameCamelcase}}> list = getDao().search(property, keyword, page, 20+1);
		if(list.size()==21){
			list.remove(20);
			return new SuccessModel(list, true, getDao().count());
		}
		return new SuccessModel(list, false, getDao().count());
	}
	
	public BaseModel update ({{tableNameCamelcase}} {{tableNameLowercase}}){
		logger.info("update model " + {{tableNameLowercase}});
		{{tableNameCamelcase}} returnModel = ({{tableNameCamelcase}})  getDao().update({{tableNameLowercase}});
		return new SuccessModel(returnModel);
	}
	
	public BaseModel delete ({{tableNameCamelcase}} {{tableNameLowercase}}){
		logger.info("delete model " + {{tableNameLowercase}});
		getDao().delete({{tableNameLowercase}});
		return new SuccessModel();
	}
	
	public BaseModel delete (String idx){
		logger.info("delete model " + idx);
		getDao().delete(getDao().read(idx));
		return new SuccessModel();
	}
}
