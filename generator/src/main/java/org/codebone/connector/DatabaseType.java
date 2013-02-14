package org.codebone.connector;

public enum DatabaseType {
	MYSQL, ORACLE, POSTGRESQL, MSSQL, SQLITE;
	
	public static boolean isMySQL(DatabaseType databaseType) {
		return databaseType == MYSQL ? true : false;
	}
	
	public static boolean isMSSQL(DatabaseType databaseType) {
		return databaseType == MSSQL ? true : false;
	}
}