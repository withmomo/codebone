package org.codebone.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codebone.generator.connector.Column;
import org.codebone.generator.connector.DatabaseType;

public class Generator {
	
	private String siteTitle;
	private DatabaseType databaseType;
	private String teamplatePath;
	private String generatePath;
	private String tableName;
	private String packageName;
	private String uri;
	private List<Column> columns;
	
	public void generate() {
		replaceAbsolutePath();
		try {
			new FileScanner(teamplatePath, new FileScanner.FileListner() {
				public void found(File file) {
					String source = FileUtils.read(file);
					String generatedSource = mappingTemplate(source);
					generateTemplateFile(file, generatedSource);
				}
			});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void generateTemplateFile(File file, String generatedSource) {
		String rootDirectoryPath = replaceFolderSperator(file.getAbsolutePath());
		rootDirectoryPath = rootDirectoryPath.replaceAll(teamplatePath,generatePath);
		rootDirectoryPath = rootDirectoryPath.substring(0,rootDirectoryPath.lastIndexOf("/"));
		if( !rootDirectoryPath.endsWith("/") )
			rootDirectoryPath = rootDirectoryPath + "/";
		
		String fileName = file.getName();
		int directorySeperatorIndex = fileName.indexOf("&");
		if( directorySeperatorIndex > -1 ) {
			String directoryPath = fileName.substring(0,directorySeperatorIndex);
			if("{PACKAGE}".equals(directoryPath))
				directoryPath = packageName.replaceAll("\\.","/");
			else if("{MAPPING_URI}".equals(directoryPath)) 
				directoryPath = uri;
			
			rootDirectoryPath += directoryPath;;
			fileName = fileName.substring(directorySeperatorIndex+1);
		}
		createDirectory(rootDirectoryPath);
		
		fileName = fileName.replaceAll("\\{TABLE_NAME\\}",tableName);
		fileName = fileName.replaceAll("\\{TABLE_NAME_CAMELCASE\\}",transformCamelcase(tableName));
		String absolutePath = rootDirectoryPath + "/" + fileName;
		FileUtils.write(absolutePath, generatedSource);
	}
	
	private void replaceAbsolutePath() {
		teamplatePath = replaceFolderSperator(new File(teamplatePath).getAbsolutePath());
		generatePath = replaceFolderSperator(new File(generatePath).getAbsolutePath());
	}
	
	public static String replaceFolderSperator(String path) {
		return path.replaceAll("\\\\", "/");
	}
	
	private void createDirectory(String path) {
		File file = new File(path);
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
		generatedSource = replaceReservedKeyword(generatedSource, Template.SITE_TITLE, siteTitle);
		generatedSource = replaceReservedKeyword(generatedSource, Template.PACKAGE, packageName);
		generatedSource = replaceReservedKeyword(generatedSource, Template.MAPPING_URI, uri);
		generatedSource = replaceReservedKeyword(generatedSource, Template.TABLE_NAME, tableName);
		generatedSource = replaceReservedKeyword(generatedSource, Template.TABLE_NAME_CAMELCASE, camelTableName);
		generatedSource = replaceReservedKeyword(generatedSource, Template.TABLE_NAME_UPPERCASE, tableName.toUpperCase());
		return generatedSource;
	}

	private String generateColumLoopSource(String columnLoopSouce) {
		StringBuilder builder = new StringBuilder();
		for(Column column : columns) {
			String generatedColumnSource = replaceReservedKeyword(columnLoopSouce, Template.COLUMN_TYPE, Column.transformJavaType(column.getTypeName(),databaseType));
			generatedColumnSource = replaceReservedKeyword(generatedColumnSource, Template.COLUMN_NAME, column.getName());
			String camelcase = transformCamelcase(column.getName());
			generatedColumnSource = replaceReservedKeyword(generatedColumnSource, Template.COLUMN_NAME_CAMELCASE, camelcase);
			generatedColumnSource = replaceReservedKeyword(generatedColumnSource, Template.COLUMN_DESCRIPTION, column.getDescription());
			generatedColumnSource = replaceReservedKeyword(generatedColumnSource, Template.COLUMN_DEFAULT_VALUE, column.getDefaultValue());
			
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

	public DatabaseType getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(DatabaseType databaseType) {
		this.databaseType = databaseType;
	}

	public String getSiteTitle() {
		return siteTitle;
	}

	public void setSiteTitle(String siteTitle) {
		this.siteTitle = siteTitle;
	}
}