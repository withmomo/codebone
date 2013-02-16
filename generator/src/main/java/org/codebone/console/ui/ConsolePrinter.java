package org.codebone.console.ui;

import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
import org.codebone.console.Relationship;
import org.codebone.console.RelationshipType;

import schemacrawler.schema.Column;

public class ConsolePrinter {

	private static Scanner scan = new Scanner(System.in);
	
	public static boolean queryRelationship(Relationship rel) {
		Column referencedColumn = rel.getReferencedColumn();
		if (rel.getType().equals(RelationshipType.OneToOne)) {
			System.out.println("------------- `OneToOne` detected. -------------");
			System.out.println();
			System.out.println("\t[" + referencedColumn.getParent().getName()
					+ "] 1 -> 1 [" + rel.getColumn().getParent().getName() + "]");
			System.out.println();
			return query();
		} else if (rel.getType().equals(RelationshipType.OneToMany)) {
			System.out.println("------------- `OneToMany` detected. -------------");
			System.out.println();
			System.out.println("\t[" + referencedColumn.getParent().getName()
					+ "] 1 -> N [" + rel.getColumn().getParent().getName() + "]");
			System.out.println();
			return query();
		} else if (rel.getType().equals(RelationshipType.ManyToMany)) {
			System.out.println("------------- ManyToMany detected -------------");
			System.out.println();
			System.out.println("\t[" + rel.getColumn().getParent().getName()
					+ "] N <-> N ["
					+ referencedColumn.getParent().getName() + "]");
			System.out.println();
			return query();
		} else {
		}
		return false;
	}

	private static boolean query() {
		
		boolean error = false;
		do {
			error = false;
			
			System.out.println("Q. Do you want to generate relation file?");
			System.out.println("1. Yes");
			System.out.println("2. No");
			
			int choose = -1;
			try {
				choose = Integer.parseInt(scan.nextLine());
			} catch (Exception e) {
				error = true;
			}
			
			if( !error ) {
				if( !(choose >= 1 && choose <= 2) ) {
					System.out.println("Must be select 1 or 2." + choose);
					error = true;
				}
			}
			
			if( !error ) {
				return choose == 1 ? true : false;
			}
		} while (error);
		return false;
	}
	
	public static String queryPackage(String tableName){
		boolean retry = false;
		String answer = "";
		do{
			System.out.print("Input [" + tableName + "] source package (ex. org.coebone) : ");
			answer = scan.nextLine();
			if(answer.equals("")){
				System.out.println("Mustbe fill it.");
				retry = true;
			}
		}while(retry);
		return answer;
	}
	public static String queryUri(String tableName){
		String answer = "";
		System.out.print("Input [" + tableName + "] URI path (ex. /app/'"+tableName.toLowerCase()+"'/**) : ");
		answer = scan.nextLine();
		if(StringUtils.isEmpty(answer)){
			answer = tableName;
		}
		return answer;
	}
	public static String querySiteTitle(String tableName){
		String answer = "";
		System.out.print("Input [" + tableName + "] title : ");
		answer = scan.nextLine();
		if(StringUtils.isEmpty(answer)){
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
