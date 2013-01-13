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
			System.out.println(rel.getColumn().getReferencedColumn()
					.getParent().getName()
					+ " N <-> N "
					+ referencedColumn.getReferencedColumn().getParent()
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
			String answer = scan.next();
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
	
	public static String queryPackage(){
		boolean retry = false;
		String answer = "";
		do{
			System.out.println("input Package name");
			System.out.println("(For Example, org.codebone.domain.User)");
			System.out.print(">");
			answer = scan.next();
			if(answer.equals("")){
				System.out.println("Please retry");
				retry = true;
			}
		}while(retry);
		return answer;
	}
	public static String queryUri(){
		boolean retry = false;
		String answer = "";
		do{
			System.out.println("input URI Path");
			System.out.println("For Example, if ypu want http://localhost/admin/'user'/** URI then press user))");
			System.out.print(">");
			answer = scan.next();
			if(answer.equals("")){
				System.out.println("Please retry");
				retry = true;
			}
		}while(retry);
		return answer;
	}
	public static String querySiteTitle(){
		boolean retry = false;
		String answer = "";
		do{
			System.out.print("input Site Title >");
			answer = scan.next();
			if(answer.equals("")){
				System.out.println("Please retry");
				retry = true;
			}
		}while(retry);
		return answer;
	}
	
	public static void printResult(boolean isSuccess, String tableName){
		if(isSuccess){
			System.out.println("Table " + tableName + " Code Generate Success!");
		}else{
			System.out.println("Table " + tableName + " Code Generate Fail!");
		}
	}
}
