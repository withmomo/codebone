package org.codebone.generator;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import org.codebone.generator.connector.Column;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AbstractDatabaseTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractDatabaseTest.class);
	
	protected List<Column> columns;
	
	@Before
	public void tearup() {
		logger.info("tearup");
		try {
			loadData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void teardown() {
		logger.info("teardown");
	}
	
	public void loadData() throws SQLException {
		Gson gson = new Gson();
		StringBuilder buffer = new StringBuilder();
		buffer.append("[");
		buffer.append("    {");
		buffer.append("        \"name\": \"idx\",");
		buffer.append("        \"type\": -5,");
		buffer.append("        \"typeName\": \"BIGINT\",");
		buffer.append("        \"defaultValue\": \"new Long(0)\",");
		buffer.append("        \"size\": 19,");
		buffer.append("        \"description\": \"\",");
		buffer.append("        \"isPrimaryKey\": true,");
		buffer.append("        \"isSearchable\": false");
		buffer.append("    },");
		buffer.append("    {");
		buffer.append("        \"name\": \"authority\",");
		buffer.append("        \"type\": 12,");
		buffer.append("        \"typeName\": \"VARCHAR\",");
		buffer.append("        \"defaultValue\": '\"\"',");
		buffer.append("        \"size\": 255,");
		buffer.append("        \"description\": \"\",");
		buffer.append("        \"isPrimaryKey\": false,");
		buffer.append("        \"isSearchable\": false");
		buffer.append("    },");
		buffer.append("    {");
		buffer.append("        \"name\": \"organizationIdx\",");
		buffer.append("        \"type\": -5,");
		buffer.append("        \"typeName\": \"BIGINT\",");
		buffer.append("        \"defaultValue\": \"new Long(0)\",");
		buffer.append("        \"size\": 19,");
		buffer.append("        \"description\": \"\",");
		buffer.append("        \"isPrimaryKey\": false,");
		buffer.append("        \"isSearchable\": false");
		buffer.append("    }");
		buffer.append("]");
		Type type = new TypeToken<List<Column>>(){}.getType();
		columns = gson.fromJson(buffer.toString(), type);
		
		for( Column column : columns ) {
			logger.info(column.toString());
		}
	}
}
