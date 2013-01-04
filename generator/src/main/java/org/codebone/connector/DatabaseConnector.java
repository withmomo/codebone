package org.codebone.connector;

import java.sql.Connection;

public interface DatabaseConnector {
	public Connection getConnection();
}
