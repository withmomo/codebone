package org.codebone.connector;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {

	public static List<String> getTables(DatabaseConnector databaseConnector) {
		List<String> list = new ArrayList<String>();
		Connection connection = databaseConnector.getConnection();
		try {
			DatabaseMetaData databaseMetaData = databaseConnector.getConnection().getMetaData();
			ResultSet datas = databaseMetaData.getTables("", "", "", null);
			while (datas.next()) {
				list.add(datas.getString("TABLE_NAME"));
			}
			datas.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { connection.close(); } catch (SQLException e) {}
		}
		
		return list;
	}
}
