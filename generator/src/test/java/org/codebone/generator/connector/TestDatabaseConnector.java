package org.codebone.generator.connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.codebone.generator.Define;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

// TODO require Database mockup.
public class TestDatabaseConnector {
	
	private DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(DatabaseType.MYSQL, Define.host, Define.port, Define.database, Define.id, Define.password);
	
	@Test
	public void connect() {
		Connection connection = null;
		try {
			DatabaseConnector databaseConnector = new MySQLDatabaseConnector(databaseConfiguration);
			connection = databaseConnector.getConnection();
		} catch(Exception e) {
		}
		boolean isConnected = connection != null ? true : false;
		assertTrue("Connection connected", isConnected);
	}
	
	@Test
	public void getTables() throws SQLException {
		Connection connection = null;
		DatabaseConnector databaseConnector = new MySQLDatabaseConnector(databaseConfiguration);
		connection = databaseConnector.getConnection();

		// tables
		List<String> tables = DatabaseHelper.getTables(databaseConnector);
		for( String table : tables ) {
			System.out.println(table);
		}
		
		connection.close();
	}
	
	@Test
	public void getColumns() throws SQLException {
		Connection connection = null;
		DatabaseConnector databaseConnector = new MySQLDatabaseConnector(databaseConfiguration);
		connection = databaseConnector.getConnection();

		// columns
		List<Column> columns = TableHelper.getColumns(databaseConnector,"user");
		for( Column column : columns ) {
			System.out.println(column.getName() + " type:[" + column.getType() + "]["+column.isPrimaryKey()+"][" + column.getDescription() + "]");
		}
		
		connection.close();
	}
}
