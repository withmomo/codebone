package org.codebone.console.ui;

import java.util.Scanner;

import org.codebone.console.RelationshipType;

import schemacrawler.schema.Column;

public class ConsolePrinter {
	
	public void queryRelationship(RelationshipType type, Column column){
		Column referencedColumn = column.getReferencedColumn();
		Scanner scan = new Scanner(System.in);
		if(type.equals(RelationshipType.OneToOne)){
			System.out.println("OneToOne Detected!");
			System.out.println(referencedColumn.getParent().getName()
					+ " 1 -> 1 " + column.getParent().getName());
			
			System.out
					.println("Codebone will copy this relationship into JPA Model File. copy it? (Y/N)");
			String answer = scan.next();
			if (answer.toLowerCase().equals("y")) {
				System.out.println("Pressed Y");
			} else if (answer.toLowerCase().equals("n")) {
				System.out.println("Pressed N");
			} else {
				System.out.println("Error");
			}
		}else if(type.equals(RelationshipType.OneToMany)){
			System.out.println("OneToMany Detected!");
			System.out.println(referencedColumn.getParent().getName()
					+ " 1 -> N " + column.getParent().getName());
		}else if(type.equals(RelationshipType.ManyToMany)){
			System.out.println("ManyToMany Detected!");
			System.out.println(oneToManyColumnList.get(0)
					.getReferencedColumn().getParent().getName()
					+ " N <-> N "
					+ oneToManyColumnList.get(1).getReferencedColumn()
							.getParent().getName());
		}else{
			
		}
		
	}
}
