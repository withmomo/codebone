package org.codebone.console;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.codebone.connector.DatabaseConfiguration;
import org.codebone.connector.DatabaseType;
import org.codebone.generator.Define;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DatabaseConfigurationTools extends BaseCommand {
	
	private static Scanner scanner = new Scanner(System.in);
	
	@Override
	public void run(CommandLine line) throws Exception {
		String path = line.getOptionValue("path");
		String sourcepath = line.getOptionValue("sourcepath");
		if( !StringUtils.isEmpty(sourcepath) )
			load(sourcepath, path);
		else
			init(path);
	}
	
	private void load(String sourcepath, String path) {
		File sourceFile = new File(sourcepath + "/" + Define.definefile);
		if( !sourceFile.exists() ) {
			System.out.println( sourcepath + " file does not exists.");
			return;
		}
		
		System.out.println("Load configuration file.");
		String data = null;
		try {
			data = FileUtils.readFileToString(sourceFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Type type = new TypeToken<DatabaseConfiguration>(){}.getType();
		Gson gson = new Gson();
		DatabaseConfiguration databaseConfiguration = gson.fromJson(data, type);
		System.out.print(databaseConfiguration);
		
		System.out.println("Is it right?");
		System.out.println("1. Yes");
		System.out.println("2. No");
		int number = -1;
		do {
			number = scanner.nextInt();
		} while( !(number > 0 && number <= 1 ) );
		
		if( number == 1)
			saveConfigFile(path, databaseConfiguration);
	}

	private void init(String path) {
		System.out.println("Please input your database information.");
		System.out.println("Select database type : ");
		int i = 1;
		DatabaseType[] types = DatabaseType.values();
		for(DatabaseType type : types) {
			System.out.println( i + ". " + type.toString());
			i++;
		}
		int typeNumber = -1;
		do {
			typeNumber = scanner.nextInt();
		} while( !(typeNumber > 0 && typeNumber <= types.length ) );
		DatabaseType type = types[typeNumber-1];
		
		System.out.print("Input database host : ");
		String host = scanner.next();
		
		System.out.print("Input database port : ");
		int port = scanner.nextInt();
		
		System.out.print("Input database name : ");
		String name = scanner.next();
		
		System.out.print("Input database username : ");
		String username = scanner.next();
		
		System.out.print("Input database password : ");
		String password = scanner.next();
		
		DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
		databaseConfiguration.setDatabaseType(type);
		databaseConfiguration.setHost(host);
		databaseConfiguration.setPort(port);
		databaseConfiguration.setDatabase(name);
		databaseConfiguration.setId(username);
		databaseConfiguration.setPassword(password);
		
		saveConfigFile(path, databaseConfiguration);
	}

	private void saveConfigFile(String path,
			DatabaseConfiguration databaseConfiguration) {
		Gson gson = new Gson();
		String json = gson.toJson(databaseConfiguration);
		
		StringBuilder builder = new StringBuilder();
        builder.append("codebone.database.host=");
        builder.append(databaseConfiguration.getHost());
        builder.append("\n");
        builder.append("codebone.database.port=");
        builder.append(databaseConfiguration.getPort());
        builder.append("\n");
        builder.append("codebone.database.name=");
        builder.append(databaseConfiguration.getDatabase());
        builder.append("\n");
        builder.append("codebone.database.username=");
        builder.append(databaseConfiguration.getId());
        builder.append("\n");
        builder.append("codebone.database.password=");
        builder.append(databaseConfiguration.getPassword());
        builder.append("\n");
        String propertyString = builder.toString();
        
		try {
			String configPath = path + "/" + Define.definefile;
            FileUtils.writeStringToFile(new File(configPath), json);
            System.out.println("Generated config file : " + configPath);
            
            String siteConfigPath = path + "/src/main/resources/codebone.properties";
            System.out.println("Check site config file : " + siteConfigPath);
            File siteConfigFile = new File(siteConfigPath);
            if(siteConfigFile.exists()) {
            	FileUtils.writeStringToFile(siteConfigFile, propertyString);
                System.out.println("Generated site config file : " + siteConfigFile);
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@SuppressWarnings("static-access")
	public Options options() {
		Option sourcepathOption = OptionBuilder.withArgName("sourcepath").hasArg()
				.isRequired(false)
				.withDescription("Database configuration sourcepath file path")
				.create("sourcepath");
		
		Option pathOption = OptionBuilder.withArgName("path").hasArg()
				.isRequired(true)
				.withDescription("Database configuration file path")
				.create("path");

		Options options = new Options();
		options.addOption(sourcepathOption);
		options.addOption(pathOption);
		return options;
	}
}