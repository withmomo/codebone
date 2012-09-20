package org.codebone.generator.connector;


public class MySQLDatabaseConnector extends AbstractDatabaseConnector {
	public MySQLDatabaseConnector(DatabaseConfiguration databaseConfiguration) {
		super(databaseConfiguration);
	}

	@Override
	protected String getConnectionString() {
		return "jdbc:mysql://" + databaseConfiguration.getHost() + ":" + databaseConfiguration.getPort() + "/"  + databaseConfiguration.getDatabase();
	}
}
