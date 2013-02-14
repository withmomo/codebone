package org.codebone.console;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.codebone.generator.Define;

public class VersionTools extends BaseCommand {

	@Override
	public void run(CommandLine line) throws Exception {
		System.out.println( "codebone version : " + Define.version);
	}

	@Override
	public Options options() {
		Options options = new Options();
		return options;
	}

}
