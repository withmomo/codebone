package org.codebone.console;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public abstract class BaseCommand {

	public void start(String[] args) {
		CommandLineParser parser = new GnuParser();
		CommandLine line = null;
		try {
			line = parser.parse(options(), args);
		} catch (ParseException pe) {
			printHelp("Parsing failed." + pe.getMessage());
		}

		if (line == null) {
			return;
		}

		try {
			run(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	public void printHelp(String message) {
		System.out.println(message);
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("java -jar codebone-generator.jar ", options());
		System.exit(-1);
	}

	public abstract void run(CommandLine line) throws Exception;
	public abstract Options options();
}