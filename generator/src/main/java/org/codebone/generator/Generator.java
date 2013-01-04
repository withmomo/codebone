package org.codebone.generator;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.WordUtils;
import org.codebone.connector.Column;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public class Generator {
	
	private String siteTitle;
	private String teamplatePath;
	private String generatePath;
	private String tableName;
	private String packageName;
	private String uri;
	private List<Column> columns;
	
	public boolean generate() {
		replaceAbsolutePath();
		try {
			new FileScanner(teamplatePath, new FileScanner.FileListner() {
				public void found(File file) {
					String source = FileUtils.read(file);
					try {
						String generatedSource = mappingTemplate(source);
						generateTemplateFile(file, generatedSource);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
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
		
		fileName = replaceReservedKeyword(fileName, Template.TABLE_NAME, tableName);
		fileName = replaceReservedKeyword(fileName, Template.TABLE_NAME_CAMELCASE, transformCamelcase(tableName));
		
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
	
	private String mappingTemplate(String source) throws Exception {
		if( source == null || tableName == null || packageName == null || uri == null | columns == null )
			return null;
		
		HashMap<String, Object> datas = getMappingDatas();
		
		MustacheFactory mf = new DefaultMustacheFactory();
		StringReader reader = new StringReader(source);
		StringWriter writer = new StringWriter();
		Mustache mustache = mf.compile(reader, "define");
		mustache.execute(writer, datas).flush();
		return writer.toString();
	}

	private HashMap<String, Object> getMappingDatas() throws IllegalAccessException, InvocationTargetException {
		HashMap<String, Object> datas = new HashMap<String, Object>();
	    datas.put("siteTitle", siteTitle);
	    datas.put("package", packageName);
	    datas.put("mappingUri", uri);
	    setValue(datas, "tableName", tableName);
	    
	    @SuppressWarnings("rawtypes")
		List<HashMap> objectColumns = new ArrayList<HashMap>();
	    for(Column column : columns ) {
	    	HashMap<String, Object> objectColumn = new HashMap<String, Object>();
	    	
	    	PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(column.getClass());
	        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {

	            Method method = propertyDescriptor.getReadMethod();
	            if (propertyDescriptor.getReadMethod() == null) {
	                continue;
	            }

	            if (method.getGenericParameterTypes().length > 0) {
	                continue;
	            }

	            String name = propertyDescriptor.getName();
	            Object value = method.invoke(column);

	            if (value == null) {
	                continue;
	            }
	            
	            setValue(objectColumn, name, value);
	            
	        }
	    	objectColumns.add(objectColumn);
	    }
	    datas.put("columns", objectColumns);
	    return datas;
	}
	
	private void setValue(HashMap<String, Object> obj, String name, Object value) {
		obj.put(name, value);
		
		String uppercase = name + "Uppercase";
		obj.put(uppercase, value.toString().toUpperCase());
	    
	    String lowercase = name + "Lowercase";
	    obj.put(lowercase, value.toString().toLowerCase());
	    
	    String camelcase = name + "Camelcase";
	    String camelcaseValue = transformCamelcase(value.toString());
	    obj.put(camelcase, camelcaseValue);
	}
	
	private String transformCamelcase(String source) {
		if( source == null )
			return null;
		return WordUtils.capitalizeFully(source, new char[]{'_'}).replaceAll("_", "");
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
	
	public String getSiteTitle() {
		return siteTitle;
	}

	public void setSiteTitle(String siteTitle) {
		this.siteTitle = siteTitle;
	}
}