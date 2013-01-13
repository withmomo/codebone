package org.codebone.connector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codebone.console.Relationship;
import org.codebone.console.RelationshipType;

import schemacrawler.schema.Column;
import schemacrawler.schema.Index;
import schemacrawler.schema.IndexColumn;
import schemacrawler.schema.PrimaryKey;
import schemacrawler.schema.Table;

public class SchemaCrawlerHelper {

	public static boolean isUniqueColumn(Column column) {
		for (Index index : column.getParent().getIndices()) {
			if (index.isUnique()) {
				List<IndexColumn> indexColumns = index.getColumns();
				if (indexColumns.size() == 1
						&& indexColumns.get(0).getFullName()
								.equals(column.getFullName())) {
					return true;
				}
			}
		}
		PrimaryKey pk = column.getParent().getPrimaryKey();
		if (pk != null) {
			List<IndexColumn> PKList = pk.getColumns();
			if (PKList.size() == 1
					&& PKList.get(0).getFullName().equals(column.getFullName())) {
				return true;
			}
		}
		return false;
	}

	public static List<Relationship> findRelationship(Table table, Table originTable) {
		List<Relationship> relList = new ArrayList<Relationship>();
		List<Relationship> oneToManyRelList = new ArrayList<Relationship>();
		for (Column column : table.getColumns()) {
			Relationship rel = null;
			if (column.isPartOfForeignKey()) {
				/*if(!(table.equals(originTable) || column.getReferencedColumn().getParent().equals(originTable))){
					continue;
				}*/
				if (SchemaCrawlerHelper.isUniqueColumn(column)) {
					rel = new Relationship(column, RelationshipType.OneToOne);
					relList.add(rel);
				} else {
					rel = new Relationship(column, RelationshipType.OneToMany);
					relList.add(rel);
					oneToManyRelList.add(rel);
				}
			}
			if (oneToManyRelList.size() == 2
					&& table.getColumns().size() == 2) {
				relList.remove(oneToManyRelList.get(0));
				relList.remove(oneToManyRelList.get(1));
				rel = new Relationship(oneToManyRelList.get(0).getColumn().getReferencedColumn(), oneToManyRelList.get(1).getColumn().getReferencedColumn(), RelationshipType.ManyToMany );
				relList.add(rel);
			}
		}
		return relList;
	}
}
