package org.codebone.generator.ui;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codebone.connector.Column;
import org.codebone.connector.DatabaseConfiguration;
import org.codebone.connector.DatabaseConnector;
import org.codebone.connector.DatabaseHelper;
import org.codebone.connector.DatabaseType;
import org.codebone.connector.MySQLDatabaseConnector;
import org.codebone.connector.TableHelper;
import org.codebone.generator.Generator;
import org.codebone.generator.ui.BrowserBridge.ScriptCallListner;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressEvent;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Laucher {
	
	private static Browser browser;
	private static BrowserBridge browserBridge;
	private static String currentDirectory = new File(".").getAbsolutePath();
	private static String directory = currentDirectory.substring(0,currentDirectory.length()-2)+ "/wizard/";
	private static String url = "index.html";
	private static String templatePath;
	private static String generatePath;
	private static String tableName;
	private static String packageName;
	private static String uri;
	private static String siteTitle;

	private static DatabaseConfiguration databaseConfiguration;
	private static String host;
	private static int port;
	private static String database;
	private static String id;
	private static String password;
	private static List<Column> columns;
	
	public static void main(String [] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setSize(1000, 600);
		shell.setText("codebone-generator-wizard");
		shell.setLayout(new FillLayout());
		try {
			browser = new Browser(shell, SWT.NONE);
		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser : " + e.getMessage());
			display.dispose();
			return;
		}
		browserBridge = new BrowserBridge(browser, new ScriptCallListner() {
			public void call(Object[] arguments) {
				String data = (String)arguments[0];
				if( data == null )
					return;
				
				HashMap<String,String> map = mapFromJson(data);
				scriptCalledProcess(map);
			}
		});
	    browser.addProgressListener(new ProgressListener() {
			public void completed(ProgressEvent progressEvent) {
				pageloadCompletedProcess();
			}
			public void changed(ProgressEvent arg0) {
			}
		});
	    System.out.println(directory+url);
	    browser.setUrl(directory+url);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		browserBridge.dispose ();
		display.dispose();
	}
	
	private static void pageloadCompletedProcess() {
		String url = browser.getUrl();
		if( url.toLowerCase().endsWith("index.html") ) {
		} else if( url.toLowerCase().endsWith("tables.html") ) {
			List<String> data = loadTables();
			JSONArray jsonObject = JSONArray.fromObject(data);
			String script = "var list = " + jsonObject.toString() + ";";
			browserBridge.callScript("data",script);
			//System.out.println(jsonObject.toString());
		} else if( url.toLowerCase().endsWith("columns.html") ) {
			columns = loadColumns(tableName);
			JSONArray jsonObject = JSONArray.fromObject(columns);
			String script = jsonObject.toString();
			script = script.replaceAll("\\\\\"", "");
			script = "var list = " + script + ";";
			browserBridge.callScript("data",script);
			//System.out.println(jsonObject.toString());
		} else if( url.toLowerCase().endsWith("path.html") ) {
		}
	}
	
	public static void scriptCalledProcess(HashMap<String,String> map) {
		String url = browser.getUrl();
		if( url.toLowerCase().endsWith("index.html") ) {
			host = map.get("host");
			port = Integer.parseInt(map.get("port"));
			database = map.get("database");
			id = map.get("id");
			password = map.get("password");
			databaseConfiguration = new DatabaseConfiguration(DatabaseType.MYSQL, host, port, database, id, password);
			browser.setUrl(directory+"tables.html");
		} else if( url.toLowerCase().endsWith("tables.html") ) {
			tableName = map.get("name");
			browser.setUrl(directory+"columns.html");
			System.out.println(map);
		} else if( url.toLowerCase().endsWith("columns.html") ) {
			System.out.println(map);
			for( Column column : columns ) {
				String key = column.getName() + "_description";
				String description = map.get(key);
				column.setDescription(description);
			}
			browser.setUrl(directory+"path.html");
		} else if( url.toLowerCase().endsWith("path.html") ) {
			System.out.println(map);
			
			templatePath = map.get("templatePath");
			templatePath = Generator.replaceFolderSperator(templatePath);
			templatePath = templatePath.substring(0,templatePath.lastIndexOf("/"));
			
			generatePath = map.get("generatePath");
			generatePath = Generator.replaceFolderSperator(generatePath);
			generatePath = generatePath.substring(0,generatePath.lastIndexOf("/"));
			
			packageName = map.get("package");
			uri = map.get("uri");
			
			siteTitle = map.get("title");
			
			Generator generator = new Generator();
			generator.setTeamplatePath(templatePath);
			generator.setGeneratePath(generatePath);
			generator.setColumns(columns);
			generator.setTableName(tableName);
			generator.setPackageName(packageName);
			generator.setUri(uri);
			generator.setSiteTitle(siteTitle);
			generator.generate();
			
			browser.setUrl(directory+"done.html");
			System.out.println(map);
		}
	}
	
	public static List<String> loadTables() {
		List<String> tables = null;
		try {
			Connection connection = null;
			DatabaseConnector databaseConnector = new MySQLDatabaseConnector(databaseConfiguration);
			connection = databaseConnector.getConnection();
			tables = DatabaseHelper.getTables(databaseConnector);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tables;
	}
	
	public static List<Column> loadColumns(String table) {
		List<Column> columns = null;
		try {
			Connection connection = null;
			DatabaseConnector databaseConnector = new MySQLDatabaseConnector(databaseConfiguration);
			connection = databaseConnector.getConnection();
			columns = TableHelper.getColumns(databaseConnector, table);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return columns;
	}
	
	public static HashMap<String,String> mapFromJson(String data) {
		HashMap<String,String> map = new HashMap<String, String>();
		JSONArray json = JSONArray.fromObject(data);
		for( int i = 0 ; i < json.size() ; i++ ) {
			JSONObject jsonObject = json.getJSONObject(i);
			String key = jsonObject.getString("name");
			String value = jsonObject.getString("value");
			map.put(key, value);
		}
	    return map;
	}
}
