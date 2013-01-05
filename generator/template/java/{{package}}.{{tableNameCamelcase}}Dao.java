package {{package}};

import org.codebone.framework.generic.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository
public class {{tableNameCamelcase}}Dao extends AbstractDao<{TABLE_NAME_CAMELCASE}>{
	@Override
	protected Class<{{tableNameCamelcase}}> getEntityClass() {
		return {{tableNameCamelcase}}.class;
	}
}
