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
public class {TABLE_NAME_CAMELCASE}Service extends AbstractService<{TABLE_NAME_CAMELCASE}>{
	
	@Autowired
	private {TABLE_NAME_CAMELCASE}Dao dao;
	
	public AbstractDao getDao() {
		return dao;
	}
}
