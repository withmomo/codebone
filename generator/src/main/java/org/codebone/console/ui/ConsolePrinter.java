package org.codebone.console.ui;

import java.util.Scanner;

import org.codebone.console.Relationship;
import org.codebone.console.RelationshipType;

import schemacrawler.schema.Column;

public class ConsolePrinter {
	
	public static void queryRelationship(Relationship rel){
		Column referencedColumn = rel.getReferencedColumn();
		Scanner scan = new Scanner(System.in);
		if(rel.getType().equals(RelationshipType.OneToOne)){
			System.out.println("OneToOne Detected!");
			System.out.println(referencedColumn.getParent().getName()
					+ " 1 -> 1 " + rel.getColumn().getParent().getName());
			
			/*System.out
					.println("Codebone will copy this relationship into JPA Model File. copy it? (Y/N)");
			String answer = scan.next();
			if (answer.toLowerCase().equals("y")) {
				System.out.println("Pressed Y");
			} else if (answer.toLowerCase().equals("n")) {
				System.out.println("Pressed N");
			} else {
				System.out.println("Error");
			}*/
		}else if(rel.getType().equals(RelationshipType.OneToMany)){
			System.out.println("OneToMany Detected!");
			System.out.println(referencedColumn.getParent().getName()
					+ " 1 -> N " + rel.getColumn().getParent().getName());
		}else if(rel.getType().equals(RelationshipType.ManyToMany)){
			System.out.println("ManyToMany Detected!");
			System.out.println(rel.getColumn().getReferencedColumn().getParent().getName()
					+ " N <-> N "
					+ referencedColumn.getReferencedColumn().getParent().getName());
		}else{
			
		}
		
	}
}
