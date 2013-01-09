package org.codebone.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandLineTools {

	private static final Logger logger = LoggerFactory.getLogger(CommandLineTools.class);

	public static void main(String[] args) {
		Codebone codebone = new Codebone();
		codebone.start(args);
	}
}