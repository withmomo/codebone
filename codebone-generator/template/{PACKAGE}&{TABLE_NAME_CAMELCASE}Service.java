package <PACKAGE>;

import java.util.List;

import org.apache.log4j.Logger;
import org.codebone.framework.BaseModel;
import org.codebone.framework.SuccessModel;
import org.codebone.framework.generic.AbstractDao;
import org.codebone.framework.generic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class <TABLE_NAME_CAMELCASE>Service extends AbstractService<<TABLE_NAME_CAMELCASE>>{
	
	@Autowired
	private <TABLE_NAME_CAMELCASE>Dao dao;
	
	public AbstractDao getDao() {
		return dao;
	}
	
	public BaseModel create (<TABLE_NAME_CAMELCASE> model){
		return new SuccessModel();
	}
	
	public BaseModel update (<TABLE_NAME_CAMELCASE> model){
		<TABLE_NAME_CAMELCASE> returnModel = (<TABLE_NAME_CAMELCASE>) getDao().update(model);
		return new SuccessModel(returnModel);
	}
}
