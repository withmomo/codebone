package org.codebone.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codebone.generator.connector.Column;

public class Generator {
	private String teamplatePath;
	private String generatePath;
	private String tableName;
	private String packageName;
	private String uri;
	private List<Column> columns;
	
	public void generate() {
		makeGeneratedDirectory();
		try {
			new FileScanner(teamplatePath, new FileScanner.FileListner() {
				public void found(File file) {
					if( file.getPath().endsWith(".template") ) {
						String source = FileUtils.read(file);
						String generatedSource = mappingTemplate(source);
						FileUtils.write(getGeneratedFile(file), generatedSource);
					}
				}
			});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private File getGeneratedFile(File file) {
		String fileName = file.getName();
		String camelTableName = transformCamelcase(tableName);
		fileName = fileName.replaceAll("\\{TABLE_NAME\\}",camelTableName);
		fileName = fileName.replaceAll(".template",".java");
		return new File(generatePath + fileName);
	}
	
	private void makeGeneratedDirectory() {
		File file = new File(generatePath);
		if( !file.exists() ) {
			file.mkdirs();
		}
	}
	
	private String mappingTemplate(String source) {
		if( source == null || tableName == null || packageName == null || uri == null | columns == null )
			return null;
		
		// loop
		Pattern pattern = Pattern.compile(Template.COLUMN_LOOP_REGEX, Pattern.DOTALL|Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(source);
		StringBuilder builder = new StringBuilder(source);
		int modifiedPoint = 0;
		while(matcher.find()) {
			String columnLoopSouce = matcher.group();
			columnLoopSouce = columnLoopSouce.substring(13, columnLoopSouce.length()-14);
			String generatedSource = generateColumLoopSource(columnLoopSouce);
			int start = matcher.start() + modifiedPoint;
			int end = matcher.end() + modifiedPoint;
			builder.replace(start, end, generatedSource);
			modifiedPoint = generatedSource.length() - matcher.group().length();
		}
		
		String camelTableName = transformCamelcase(tableName);
		String generatedSource = builder.toString();
		generatedSource = replaceReservedKeyword(generatedSource, Template.PACKAGE, packageName);
		generatedSource = replaceReservedKeyword(generatedSource, Template.MAPPING_URI, uri);
		generatedSource = replaceReservedKeyword(generatedSource, Template.TABLE_NAME, tableName);
		generatedSource = replaceReservedKeyword(generatedSource, Template.TABLE_NAME_CAMELCASE, camelTableName);
		return generatedSource;
	}

	private String generateColumLoopSource(String columnLoopSouce) {
		StringBuilder builder = new StringBuilder();
		for(Column column : columns) {
			String generatedColumnSource = replaceReservedKeyword(columnLoopSouce, Template.COLUMN_TYPE, Column.transformJavaType(column.getTypeName()));
			generatedColumnSource = replaceReservedKeyword(generatedColumnSource, Template.COLUMN_NAME, column.getName());
			String camelcase = transformCamelcase(column.getName());
			generatedColumnSource = replaceReservedKeyword(generatedColumnSource, Template.COLUMN_NAME_CAMELCASE, camelcase);
			generatedColumnSource = replaceReservedKeyword(generatedColumnSource, Template.COLUMN_DESCRIPTION, column.getDescription());
			
			if( column.isPrimaryKey() )
				generatedColumnSource = replaceReservedKeyword(generatedColumnSource, Template.COLUMN_ID, Template.COLUMN_ID_GENERATE);
			else
				generatedColumnSource = replaceReservedKeyword(generatedColumnSource, Template.COLUMN_ID, "");
			
			builder.append(generatedColumnSource);
		}
		return builder.toString();
	}
	
	private String transformCamelcase(String source) {
		if( source == null )
			return null;
		return source.substring(0,1).toUpperCase() + source.substring(1);
	}
	
	private String replaceReservedKeyword(String source, String keyword, String data) {
		return source.replaceAll("(?i)" + keyword, data);
	}

	public String getTeamplatePath() {
		return teamplatePath;
	}

	public void setTeamplatePath(String teamplatePath) {
		this.teamplatePath = teamplatePath;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getGeneratePath() {
		return generatePath;
	}

	public void setGeneratePath(String generatePath) {
		if( !generatePath.endsWith("/") )
			generatePath = generatePath + "/";
		
		this.generatePath = generatePath;
	}
}
