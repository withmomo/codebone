package org.codebone.console;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

public class Codebone extends BaseCommand {
	
	private String host;
	private String database;
	private String user;
	private String password;
	private String table;
	
	@Override
	public void run(CommandLine line) throws Exception {
	    setValues(line);
	    
	    // TODO write your code
	    //
	}

	private void setValues(CommandLine line) {
		host = line.getOptionValue("host");
	    database = line.getOptionValue("database");
	    user = line.getOptionValue("user");
	    password = line.getOptionValue("password");
	    table = line.getOptionValue("table");
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

		Option tablaeOption = OptionBuilder.withArgName("table").hasArg()
				.withDescription("Database table").create("table");

		Options options = new Options();
		options.addOption(hostOption);
		options.addOption(userOption);
		options.addOption(passwordOption);
		options.addOption(databaseOption);
		options.addOption(tablaeOption);

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
