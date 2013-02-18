package org.codebone.console;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.WordUtils;
import org.codebone.connector.Column;
import org.codebone.connector.DatabaseConfiguration;
import org.codebone.connector.DatabaseConnector;
import org.codebone.connector.DatabaseHelper;
import org.codebone.connector.MySQLDatabaseConnector;
import org.codebone.connector.SchemaCrawlerHelper;
import org.codebone.console.ui.ConsolePrinter;
import org.codebone.generator.Define;
import org.codebone.generator.Generator;

import schemacrawler.schema.Database;
import schemacrawler.schema.Schema;
import schemacrawler.schema.Table;
import schemacrawler.schema.TableRelationshipType;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevel;
import schemacrawler.utility.SchemaCrawlerUtility;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GeneratorTools extends BaseCommand {

	private String table;
	private String path;
	
	private List<Column> columnList = null;
			
	@Override
	public void run(CommandLine line) throws Exception {
		setValues(line);
		DatabaseConfiguration databaseConfiguration = getDatabaseConfiguration();
		Connection connection = null;
		DatabaseConnector connector = new MySQLDatabaseConnector(databaseConfiguration);
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
		Schema schema = databaseStruct.getSchema(databaseConfiguration.getDatabase());
		final Table tableStruct = databaseStruct.getTable(schema, table);
		
		
		List<Relationship> applyRelList = findTableRelationship(connection, tableStruct);
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
		
		List<Generator> generators = new ArrayList<Generator>();
		
		String templatePath = path + "/template";
		File templateDirectory = new File(templatePath);
		if( !templateDirectory.exists() ) {
			System.out.println("Not found template folder. " + templateDirectory);
			System.exit(0);
		//	templatePath = ConsolePrinter.getTemplatePath();
		}
		Map<Table, String> packageMap = new HashMap<Table, String>();
		Map<Table, String> uriMap = new HashMap<Table, String>();
		Map<Table, String> titleMap = new HashMap<Table, String>();
		for(Table table : targetTableList){
			packageMap.put(table, ConsolePrinter.queryPackage(table.getName()));
			uriMap.put(table, ConsolePrinter.queryUri(table.getName()));
			titleMap.put(table, ConsolePrinter.querySiteTitle(table.getName()));
			System.out.println();
		}
		for(Table table : targetTableList){
			Generator generator = new Generator();
			generator.setPackageName(packageMap.get(table));
			generator.setUri(uriMap.get(table));
			generator.setSiteTitle(titleMap.get(table));
			generator.setTeamplatePath(templatePath);
			generator.setGeneratePath(path + "/src/main");
			columnSetting(table, applyRelList, packageMap);
			generator.setColumns(columnList);
			generator.setTableName(table.getName());
			generators.add(generator);
		}
		for(Generator generator : generators){
			ConsolePrinter.printResult(generator.generate(), generator.getTableName());
		}
	}

	private DatabaseConfiguration getDatabaseConfiguration() {
		String json = null;
		try {
			String configPath = path + "/" + Define.definefile;
			json = FileUtils.readFileToString(new File(configPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		Type type = new TypeToken<DatabaseConfiguration>(){}.getType();
		return gson.fromJson(json, type);
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
					String anotherPackage = packageMap.get(refColumn.getParent());
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

	private List<Relationship> findTableRelationship(Connection connection,
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
		table = line.getOptionValue("table");
		path = line.getOptionValue("path");
	}

	@Override
	@SuppressWarnings("static-access")
	public Options options() {
		Option pathOption = OptionBuilder.withArgName("path").hasArg()
				.isRequired(true)
				.withDescription("Database configuration file path")
				.create("path");

		Option tableOption = OptionBuilder.withArgName("table").hasArg()
				.withDescription("Database table").create("table");

		Options options = new Options();
		options.addOption(pathOption);
		options.addOption(tableOption);
		return options;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
}
