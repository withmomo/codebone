package org.codebone.console.ui;

import java.util.Scanner;

import org.codebone.console.Relationship;
import org.codebone.console.RelationshipType;

import schemacrawler.schema.Column;

public class ConsolePrinter {

	private static Scanner scan = new Scanner(System.in);
	
	
	public static boolean queryRelationship(Relationship rel) {
		Column referencedColumn = rel.getReferencedColumn();
		if (rel.getType().equals(RelationshipType.OneToOne)) {
			System.out.println("OneToOne Detected!");
			System.out.println(referencedColumn.getParent().getName()
					+ " 1 -> 1 " + rel.getColumn().getParent().getName());
			return query();
		} else if (rel.getType().equals(RelationshipType.OneToMany)) {
			System.out.println("OneToMany Detected!");
			System.out.println(referencedColumn.getParent().getName()
					+ " 1 -> N " + rel.getColumn().getParent().getName());
			return query();
		} else if (rel.getType().equals(RelationshipType.ManyToMany)) {
			System.out.println("ManyToMany Detected!");
			System.out.println(rel.getColumn()
					.getParent().getName()
					+ " N <-> N "
					+ referencedColumn.getParent()
							.getName());
			return query();
		} else {
		}
		return false;
	}

	private static boolean query() {
		
		boolean error = false;
		do {
			System.out
					.println("Codebone will copy this relationship into JPA Model File. copy it? (Y/N) >");
			String answer = scan.nextLine();
			if (answer.toLowerCase().equals("y")) {
				System.out.println("Apply the relationship");
				return true;
			} else if (answer.toLowerCase().equals("n")) {
				System.out.println("Ignore the relationship");
				return false;
			} else {
				System.out.println("Error");
				error = true;
			}
		} while (error);
		return false;
	}
	
	public static String queryPackage(String tableName){
		boolean retry = false;
		String answer = "";
		do{
			System.out.println("input "+ tableName +"'s Package name");
			System.out.println("(For Example, org.codebone.domain.User)");
			System.out.print(">");
			answer = scan.nextLine();
			if(answer.equals("")){
				System.out.println("Please retry");
				retry = true;
			}
		}while(retry);
		return answer;
	}
	public static String queryUri(String tableName){
		String answer = "";
			System.out.println("input "+ tableName +"'s URI Path");
			System.out.println("For Example, if you want http://localhost:8080/admin/'user'/** URI then press 'user'");
			System.out.println("if you want just table's name to URI, then press enter");
			System.out.print(">");
			answer = scan.nextLine();
			if(answer.equals("")){
				answer = tableName;
			}
		return answer;
	}
	public static String querySiteTitle(String tableName){
		String answer = "";
			System.out.println("input "+ tableName +"'s Site Title");
			System.out.println("if you want just table's name to Title, then press enter");
			System.out.print(">");
			answer = scan.nextLine();
			if(answer.equals("")){
				answer = tableName;
			}
		return answer;
	}
	
	public static void printResult(boolean isSuccess, String tableName){
		if(isSuccess){
			System.out.println("Table " + tableName + " Code Generate Success!");
		}else{
			System.out.println("Table " + tableName + " Code Generate Fail!");
		}
	}

	public static String getTemplatePath() {
		String answer = "";
		System.out.println("input Template file path");
		System.out.println("you can write ../ to locate parent directory, or just press enter to default directory");
		System.out.print(">");
		answer = scan.nextLine();
		if(answer.equals("")){
			//TODO change to just "template"
			answer = "../../template";
		}
		return answer;
	}
}
