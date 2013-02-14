package org.codebone.console;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.apache.commons.beanutils.MethodUtils;

public class CommandLineTools {

	public static void main(String[] args) {
		if ((args == null) || (args.length < 1)) {
			System.out.println("No command specified");
			return;
		}

		String command = args[0];
		Class<?> clazz = null;

		try {
			clazz = Class.forName(command);
		} catch (ClassNotFoundException e) {
		}

		if (clazz == null) {
			try {
				clazz = Class.forName("org.codebone.console." + command);
			} catch (ClassNotFoundException e) {
			}
		}

		if (clazz == null) {
			System.out.println("Unable to find command");
			return;
		}

		args = Arrays.copyOfRange(args, 1, args.length);

		try {
			if (BaseCommand.class.isAssignableFrom(clazz)) {
				BaseCommand tool = (BaseCommand) clazz.newInstance();
				tool.start(args);
			} else {
				MethodUtils.invokeStaticMethod(clazz, "main", (Object) args);
			}
		} catch (NoSuchMethodException e) {
			System.out.println("Unable to invoke command");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("Unable to invoke command");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println("Error while invoking command");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println("Error while instantiating tool object");
			e.printStackTrace();
		}
	}
}