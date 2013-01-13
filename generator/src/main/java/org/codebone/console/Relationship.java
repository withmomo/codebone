package org.codebone.console;

import schemacrawler.schema.Column;

public class Relationship {
	private Column column;
	private Column referencedColumn;
	private RelationshipType type;
	@Override
	public String toString() {
		return "Relationship [column=" + column + ", referencedColumn="
				+ referencedColumn + ", type=" + type + "]";
	}
	public Relationship(Column column, Column referencedColumn,
			RelationshipType type) {
		super();
		this.column = column;
		this.referencedColumn = referencedColumn;
		this.type = type;
	}
	public Relationship(Column column,
			RelationshipType type) {
		super();
		this.column = column;
		this.referencedColumn = column.getReferencedColumn();
		this.type = type;
	}
	public Relationship() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Column getColumn() {
		return column;
	}
	public void setColumn(Column column) {
		this.column = column;
	}
	public Column getReferencedColumn() {
		return referencedColumn;
	}
	public void setReferencedColumn(Column referencedColumn) {
		this.referencedColumn = referencedColumn;
	}
	public RelationshipType getType() {
		return type;
	}
	public void setType(RelationshipType type) {
		this.type = type;
	}
	
}
