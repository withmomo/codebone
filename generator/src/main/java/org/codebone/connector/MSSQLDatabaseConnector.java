package org.codebone.connector;


public class MSSQLDatabaseConnector extends AbstractDatabaseConnector {
	public MSSQLDatabaseConnector(DatabaseConfiguration databaseConfiguration) {
		super(databaseConfiguration);
	}

	@Override
	protected String getConnectionString() {
		return "jdbc:jtds:sqlserver://" + databaseConfiguration.getHost() + ":" + databaseConfiguration.getPort() + "/"  + databaseConfiguration.getDatabase();
	}
}
