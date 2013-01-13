package org.codebone.console;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.codebone.connector.DatabaseConfiguration;
import org.codebone.connector.DatabaseConnector;
import org.codebone.connector.DatabaseHelper;
import org.codebone.connector.DatabaseType;
import org.codebone.connector.MySQLDatabaseConnector;
import org.codebone.connector.SchemaCrawlerHelper;
import org.codebone.console.ui.ConsolePrinter;

import schemacrawler.schema.Column;
import schemacrawler.schema.Database;
import schemacrawler.schema.Schema;
import schemacrawler.schema.Table;
import schemacrawler.schema.TableRelationshipType;
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
		
		final Database databaseStruct = SchemaCrawlerUtility.getDatabase(connection, options);
		Schema schema = databaseStruct.getSchema(database);
		final Table tableStruct = databaseStruct.getTable(schema, table);
		
		List<Relationship> relList = new ArrayList<Relationship>();
		
		relList.addAll(SchemaCrawlerHelper.findRelationship(tableStruct, tableStruct));
		for(Table table : tableStruct.getRelatedTables(TableRelationshipType.child)){
			System.out.println(table.getName());
			relList.addAll(SchemaCrawlerHelper.findRelationship(table, tableStruct));
		}
		for(Relationship rel : relList){
			ConsolePrinter.queryRelationship(rel);
		}
	    
	    
	}

	private void setValues(CommandLine line) {
		host = line.getOptionValue("host");
	    database = line.getOptionValue("dbname");
	    user = line.getOptionValue("user");
	    password = line.getOptionValue("password");
	    table = line.getOptionValue("table");
	    port = line.getOptionValue("port");
	    databaseType = line.getOptionValue("type");
	}
	
	@Override
	@SuppressWarnings("static-access")
	public Options options() {
		Option hostOption = OptionBuilder.withArgName("host").hasArg()
				.isRequired(true).withDescription("Datababase host")
				.create("host");

		Option userOption = OptionBuilder.withArgName("user").hasArg()
				.isRequired(true).withDescription("Database username")
				.create("user");

		Option passwordOption = OptionBuilder.withArgName("password").hasArg()
				.isRequired(true).withDescription("Database password")
				.create("password");

		Option databaseOption = OptionBuilder.withArgName("database").hasArg()
				.isRequired(true).withDescription("Database name")
				.create("database");

		Option tableOption = OptionBuilder.withArgName("table").hasArg()
				.withDescription("Database table").create("table");
		
		Option databasePort = OptionBuilder.withArgName("port").hasArg()
				.isRequired(false).withDescription("Database port")
				.create("port");
		
		Option databaseType = OptionBuilder.withArgName("type").hasArg()
				.isRequired(true).withDescription("Database type")
				.create("type");

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
