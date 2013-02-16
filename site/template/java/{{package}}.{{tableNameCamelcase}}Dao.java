package {{package}};

import org.codebone.framework.generic.AbstractDao;
import org.springframework.stereotype.Repository;
import java.io.Serializable;

@Repository
public class {{tableNameCamelcase}}Dao extends AbstractDao<{{tableNameCamelcase}}>{
	@Override
	protected Class<{{tableNameCamelcase}}> getEntityClass() {
		return {{tableNameCamelcase}}.class;
	}
	protected Serializable parse(String key) {
		return new {{primaryKeyType}}(key);
	}
}
