package org.codebone.console;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.codebone.connector.DatabaseConfiguration;
import org.codebone.connector.DatabaseConnector;
import org.codebone.connector.DatabaseHelper;
import org.codebone.connector.DatabaseType;
import org.codebone.connector.MySQLDatabaseConnector;

import schemacrawler.schema.Column;
import schemacrawler.schema.Database;
import schemacrawler.schema.Index;
import schemacrawler.schema.IndexColumn;
import schemacrawler.schema.PrimaryKey;
import schemacrawler.schema.Schema;
import schemacrawler.schema.Table;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevel;
import schemacrawler.utility.SchemaCrawlerUtility;

public class Codebone extends BaseCommand {
	
	private String host;
	private String database;
	private String user;
	private String password;
	private String table;
	private String port;
	private String databaseType;
	
	@Override
	public void run(CommandLine line) throws Exception {
	    setValues(line);
	    DatabaseType dbType = null;
	    databaseType = databaseType.toUpperCase();
	    System.out.println("database type : " + databaseType);
	    System.out.println("host : " + host);
	    System.out.println("user : " + user);
	    System.out.println("password : " + password);
	    System.out.println("port : " + port);
	    System.out.println("database : " + database);
	    System.out.println("table : " + table);
	    if(databaseType.equals(DatabaseType.MYSQL.toString())){
	    	dbType = DatabaseType.MYSQL;
	    }else if(databaseType.equals(DatabaseType.MSSQL.toString())){
	    	dbType = DatabaseType.MSSQL;
	    }
	    DatabaseConfiguration dbConf = new DatabaseConfiguration(dbType, host, Integer.parseInt(port), database, user, password);
	    Connection connection = null;
	    DatabaseConnector connector = new MySQLDatabaseConnector(dbConf);
		connection = connector.getConnection();
	    List<String> tableList = DatabaseHelper.getTables(connector);
	    if(!tableList.contains(table)){
	    	System.out.println("Table not found!");
	    	return;
	    }
	    final SchemaCrawlerOptions options = new SchemaCrawlerOptions();
		options.setSchemaInfoLevel(SchemaInfoLevel.standard());

		
		Scanner scan = new Scanner(System.in);
		final Database databaseStruct = SchemaCrawlerUtility.getDatabase(connection, options);
		Schema schema = databaseStruct.getSchema(database);
		final Table tableStruct = databaseStruct.getTable(schema, table);
			  List<Column> oneToManyColumnList = new ArrayList();
			  for(Column column : tableStruct.getColumns()){
				  if(column.isPartOfForeignKey()){
					  Column referencedColumn = column.getReferencedColumn();
					  if(isUnique(column)){
						  System.out.println("OneToOne Detected!");
						  System.out.println(referencedColumn.getParent().getName()+ " 1 -> 1 " + column.getParent().getName());
						  System.out.println("Codebone will copy this relationship into JPA Model File. copy it? (Y/N)");
						  String answer = scan.next();
						  if(answer.toLowerCase().equals("y")){
							  System.out.println("Pressed Y");
						  }else if(answer.toLowerCase().equals("n")){
							  System.out.println("Pressed N");
						  }else{
							  System.out.println("Error");
						  }
					  }else{
						  System.out.println("OneToMany Detected!");
						  System.out.println(referencedColumn.getParent().getName()+ " 1 -> N " + column.getParent().getName());
						  oneToManyColumnList.add(column);
					  }
				  }
				  if(oneToManyColumnList.size()==2 && tableStruct.getColumns().size()==2){
					  System.out.println("ManyToMany Detected!");
					  System.out.println(oneToManyColumnList.get(0).getReferencedColumn().getParent().getName() + " N <-> N " 
					  + oneToManyColumnList.get(1).getReferencedColumn().getParent().getName());
				  }
			  }
	    
	    
	    
	    
	}
	
	private static boolean isUnique(Column column){
		for (Index index : column.getParent().getIndices()) {
            if (index.isUnique()) {
                List<IndexColumn> indexColumns = index.getColumns();
                if (indexColumns.size() == 1 && 
                		indexColumns.get(0).getFullName().equals(column.getFullName())) {
                	return true;
                }
            }
        }
		PrimaryKey pk = column.getParent().getPrimaryKey();
		if(pk != null){
			List<IndexColumn> PKList = pk.getColumns();
			if(PKList.size() == 1 &&
					PKList.get(0).getFullName().equals(column.getFullName())){
				return true;
			}
		}
		return false;
	}

	private void setValues(CommandLine line) {
		host = line.getOptionValue("h");
	    database = line.getOptionValue("dbname");
	    user = line.getOptionValue("u");
	    password = line.getOptionValue("p");
	    table = line.getOptionValue("t");
	    port = line.getOptionValue("port");
	    databaseType = line.getOptionValue("type");
	}
	
	@Override
	@SuppressWarnings("static-access")
	public Options options() {
		Option hostOption = OptionBuilder.withArgName("host").hasArg()
				.isRequired(true).withDescription("Database host")
				.create("h");

		Option userOption = OptionBuilder.withArgName("user").hasArg()
				.isRequired(true).withDescription("Database username")
				.create("u");

		Option passwordOption = OptionBuilder.withArgName("password").hasArg()
				.isRequired(true).withDescription("Database password")
				.create("p");

		Option databaseOption = OptionBuilder.withArgName("database").hasArg()
				.isRequired(true).withDescription("Database name")
				.create("dbname");
		
		Option databasePort = OptionBuilder.withArgName("port").hasArg()
				.isRequired(false).withDescription("Database port")
				.create("port");
		
		Option databaseType = OptionBuilder.withArgName("type").hasArg()
				.isRequired(true).withDescription("Database type")
				.create("type");

		Option tableOption = OptionBuilder.withArgName("table").hasArg()
				.withDescription("Database table").create("t");

		Options options = new Options();
		options.addOption(hostOption);
		options.addOption(userOption);
		options.addOption(passwordOption);
		options.addOption(databaseOption);
		options.addOption(tableOption);
		options.addOption(databasePort);
		options.addOption(databaseType);

		return options;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
}
