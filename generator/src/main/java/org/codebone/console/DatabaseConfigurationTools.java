package org.codebone.console;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.io.FileUtils;
import org.codebone.connector.DatabaseConfiguration;
import org.codebone.connector.DatabaseType;
import org.codebone.generator.Define;

import com.google.gson.Gson;

public class DatabaseConfigurationTools extends BaseCommand {
	
	private static Scanner scanner = new Scanner(System.in);
	
	@Override
	public void run(CommandLine line) throws Exception {
		String path = line.getOptionValue("path");
		init(path);
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
		
		Gson gson = new Gson();
		String json = gson.toJson(databaseConfiguration);
		
		try {
			path = path + "/" + Define.definefile;
            FileUtils.writeStringToFile(new File(path), json);
            System.out.println("Generated config file : " + path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@SuppressWarnings("static-access")
	public Options options() {
		Option initOption = OptionBuilder.withArgName("init").hasArg()
				.isRequired(false)
				.hasArg(false)
				.withDescription("Database configuration init")
				.create("init");
		
		Option pathOption = OptionBuilder.withArgName("path").hasArg()
				.isRequired(true)
				.withDescription("Database configuration file path")
				.create("path");

		Options options = new Options();
		options.addOption(initOption);
		options.addOption(pathOption);
		return options;
	}
}