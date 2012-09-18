package org.codebone.generator.connector;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TableHelper {

	public static List<Column> getColumns(DatabaseConnector databaseConnector, String tableName) {
		List<Column> list = new ArrayList<Column>();
		Connection connection = databaseConnector.getConnection();
		try {
			DatabaseMetaData databaseMetaData = databaseConnector.getConnection().getMetaData();
			ResultSet datas = null;
			
			// primaryKey
			String primaryKeyColumnName = null;
			datas = databaseMetaData.getPrimaryKeys(null, null, tableName);
		 	if(datas.next()) {
		 		primaryKeyColumnName = datas.getString("COLUMN_NAME");
		 	}
		 	datas.close();
		 	
		 	// columns
			datas = databaseMetaData.getColumns(null, null, tableName, null);
			while (datas.next()) {
				String name = datas.getString("COLUMN_NAME");
				int type = datas.getInt("DATA_TYPE");
				String typeName = datas.getString("TYPE_NAME");
				int size = datas.getInt("COLUMN_SIZE");
				
				String defaultValue = datas.getString("COLUMN_DEF");
				if( defaultValue == null || "timestamp".equals(typeName.toLowerCase()) ) {
					defaultValue = Column.defaultValue(typeName);
				} else if( "string".equals(Column.transformJavaType(typeName).toLowerCase()) ) {
					defaultValue = "\"" + defaultValue + "\"";
				}
				
				String description = datas.getString("REMARKS");
				boolean isPrimaryKey = false;
				if( primaryKeyColumnName != null ) {
					isPrimaryKey = primaryKeyColumnName.equals(name) ? true : false;
				}
				
				list.add( new Column(name, type, typeName, size, defaultValue, description, isPrimaryKey, false) );
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