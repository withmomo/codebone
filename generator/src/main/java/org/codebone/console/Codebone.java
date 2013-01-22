package org.codebone.console;

import java.net.URLDecoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.lang.WordUtils;
import org.codebone.connector.Column;
import org.codebone.connector.DatabaseConfiguration;
import org.codebone.connector.DatabaseConnector;
import org.codebone.connector.DatabaseHelper;
import org.codebone.connector.DatabaseType;
import org.codebone.connector.MySQLDatabaseConnector;
import org.codebone.connector.SchemaCrawlerHelper;
import org.codebone.console.ui.ConsolePrinter;
import org.codebone.generator.Generator;

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
	
	private List<Column> columnList = null;
			
	@Override
	public void run(CommandLine line) throws Exception {
		setValues(line);
		DatabaseType dbType = null;
		databaseType = databaseType.toUpperCase();
		if (databaseType.equals(DatabaseType.MYSQL.toString())) {
			dbType = DatabaseType.MYSQL;
		} else if (databaseType.equals(DatabaseType.MSSQL.toString())) {
			dbType = DatabaseType.MSSQL;
		}
		DatabaseConfiguration dbConf = new DatabaseConfiguration(dbType, host,
				Integer.parseInt(port), database, user, password);
		Connection connection = null;
		DatabaseConnector connector = new MySQLDatabaseConnector(dbConf);
		connection = connector.getConnection();
		List<String> tableList = DatabaseHelper.getTables(connector);
		if (!tableList.contains(table)) {
			System.out.println("Table not found!");
			return;
		}
		
		final SchemaCrawlerOptions options = new SchemaCrawlerOptions();
		options.setSchemaInfoLevel(SchemaInfoLevel.standard());
		final Database databaseStruct = SchemaCrawlerUtility.getDatabase(
				connection, options);
		Schema schema = databaseStruct.getSchema(database);
		final Table tableStruct = databaseStruct.getTable(schema, table);
		
		
		List<Relationship> applyRelList = FindTableRelationship(connection, tableStruct);
		List<Table> targetTableList = new ArrayList<Table>();
		targetTableList.add(tableStruct);
		for(Relationship rel : applyRelList){
			if(!targetTableList.contains(rel.getColumn().getParent())){
				targetTableList.add(rel.getColumn().getParent());
			}
			if(!targetTableList.contains(rel.getReferencedColumn().getParent())){
				targetTableList.add(rel.getReferencedColumn().getParent());
			}
		}
		
		String pathStr = URLDecoder.decode(ClassLoader.getSystemClassLoader().getResource(".").getPath(), "UTF-8").replaceFirst("/", "");
		List<Generator> generators = new ArrayList<Generator>();
		
		String templatePath = ConsolePrinter.getTemplatePath();
		Map<Table, String> packageMap = new HashMap<Table, String>();
		for(Table table : targetTableList){
			packageMap.put(table, ConsolePrinter.queryPackage(table.getName()));
		}
		for(Table table : targetTableList){
			
			Generator generator = new Generator();
			generator.setPackageName(packageMap.get(table));
			generator.setTeamplatePath(pathStr + templatePath);
			generator.setGeneratePath(pathStr + "/src/main");
			columnSetting(table, applyRelList, packageMap);
			generator.setColumns(columnList);
			generator.setTableName(table.getName());
			/*generator.setPackageName(ConsolePrinter.queryPackage());
			generator.setUri(ConsolePrinter.queryUri());
			generator.setSiteTitle(ConsolePrinter.querySiteTitle());*/
			generator.setUri(table.getName());
			generator.setSiteTitle(table.getName());
			generators.add(generator);
		}
		for(Generator generator : generators){
			ConsolePrinter.printResult(generator.generate(), generator.getTableName());
		}
	}

	private void columnSetting(Table table, List<Relationship> applyRelList, Map<Table, String> packageMap) {
		columnList = new ArrayList<Column>();
		for(schemacrawler.schema.Column column : table.getColumns()){
			String typeName = column.getColumnDataType().getName().toLowerCase();
			String remarks = (column.getRemarks().equals("")) ? column.getName() : column.getRemarks();
			Column codeboneColumn = new Column(
					column.getName(), 
					column.getColumnDataType().getType(), 
					typeName,
					column.getSize(), 
					Column.defaultValue(typeName), 
					remarks,
					column.isPartOfPrimaryKey(), true);
			
			
				for(Relationship rel : applyRelList){
					/*if(rel.getColumn().equals(column)){
						codeboneColumn.setRelation(rel);
					}else */
					if(rel.getReferencedColumn().equals(column)){
						codeboneColumn.setRelation(rel);
					}
				}
				if(codeboneColumn.getRelation()!= null){
					columnList.add(codeboneColumn);
					Relationship rel = codeboneColumn.getRelation();
					schemacrawler.schema.Column refColumn = rel.getColumn();
					codeboneColumn = new Column();
					codeboneColumn.setForeignKey(true);
					codeboneColumn.setPrimaryKey(false);
					codeboneColumn.setSearchable(false);
					codeboneColumn.setType(0);
					codeboneColumn.setTypeName("");
					String anotherPackage = packageMap.get(column.getParent());
					String camelTableName = WordUtils.capitalizeFully(refColumn.getParent().getName(), new char[]{'_'}).replaceAll("_", "");
					codeboneColumn.setAnotherPackage(anotherPackage + "." + camelTableName);
					codeboneColumn.setName(refColumn.getParent().getName());
					if(rel.getType().equals(RelationshipType.OneToOne)){
						codeboneColumn.setRelationAnnotation("@OneToOne");
						codeboneColumn.setOptionAnnotation("@JoinColumn(name=\"" + refColumn.getName() + "\")");
						codeboneColumn.setJavaType(camelTableName);
						codeboneColumn.setDefaultValue("new " + camelTableName + "()");
					}else if(rel.getType().equals(RelationshipType.OneToMany)){
						codeboneColumn.setRelationAnnotation("@OneToMany");
						codeboneColumn.setOptionAnnotation("@JoinColumn(name=\"" + refColumn.getName() + "\")");
						codeboneColumn.setJavaType("List<" + camelTableName + ">");
						codeboneColumn.setDefaultValue("null");
					}
				}
				
			columnList.add(codeboneColumn);
		}
	}

	private List<Relationship> FindTableRelationship(Connection connection,
			Table tableStruct){
		List<Relationship> relList = new ArrayList<Relationship>();

		relList.addAll(SchemaCrawlerHelper.findRelationship(tableStruct,
				tableStruct));
		for (Table table : tableStruct
				.getRelatedTables(TableRelationshipType.child)) {
			relList.addAll(SchemaCrawlerHelper.findRelationship(table,
					tableStruct));
		}
		List<Relationship> applyRelList = new ArrayList<Relationship>();
		for (Relationship rel : relList) {
			boolean response = ConsolePrinter.queryRelationship(rel);
			if(response){
				applyRelList.add(rel);
			}
		}
		return applyRelList;
	}

	private void setValues(CommandLine line) {
		host = line.getOptionValue("host");
		database = line.getOptionValue("database");
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
