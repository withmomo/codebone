package org.codebone.generator.connector;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDatabaseConnector implements DatabaseConnector {
	
	protected DatabaseConfiguration databaseConfiguration;
	public AbstractDatabaseConnector(DatabaseConfiguration databaseConfiguration) {
		this.databaseConfiguration = databaseConfiguration;
	}
	
	public Connection getConnection() {
		if( databaseConfiguration == null )
			throw new IllegalArgumentException("database configuration must be set.");
		
		try {
			loadConnectorClass();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String connectionString = getConnectionString();
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connectionString, databaseConfiguration.getId(), databaseConfiguration.getPassword());
	    } catch (Exception e) {
	      e.printStackTrace();
	    }

	    return connection;
	}

	protected abstract String getConnectionString();

	private void loadConnectorClass() throws ClassNotFoundException {
		DatabaseType databaseType = databaseConfiguration.getDatabaseType();
		if( DatabaseType.isMySQL(databaseType) ) {
			Class.forName("com.mysql.jdbc.Driver");
		}else if( DatabaseType.isMSSQL(databaseType) ) {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} else {
			throw new ClassNotFoundException("not support database type.");
		}
	}
}