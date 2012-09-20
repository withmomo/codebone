package org.codebone.generator.connector;

import java.sql.Connection;

public interface DatabaseConnector {
	public Connection getConnection();
}
