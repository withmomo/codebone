package org.codebone.connector;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class DatabaseConfiguration {

	private DatabaseType databaseType;
	private String host;
	private int port;
	private String database;
	private String id;
	private String password;

	public DatabaseConfiguration(DatabaseType databaseType, String host, int port, String database, String id, String password) {
		this.databaseType = databaseType; 
		this.host = host;
		this.port = port;
		this.database = database;
		this.id = id;
		this.password = password;
	}

	public DatabaseConfiguration() {
	}

	public DatabaseType getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(DatabaseType databaseType) {
		this.databaseType = databaseType;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
