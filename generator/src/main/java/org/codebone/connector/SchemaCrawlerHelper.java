package org.codebone.connector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public static Map<Column, RelationshipType> findRelationship(Table table, Table originTable) {
		Map<Column, RelationshipType> relationshipMap = new HashMap<Column, RelationshipType>();
		List<Column> oneToManyColumnList = new ArrayList<Column>();
		for (Column column : table.getColumns()) {
			if (column.isPartOfForeignKey()) {
				if(!(table.equals(originTable) || column.getReferencedColumn().getParent().equals(originTable))){
					continue;
				}
				if (SchemaCrawlerHelper.isUniqueColumn(column)) {
					relationshipMap.put(column, RelationshipType.OneToOne);
				} else {
					relationshipMap.put(column, RelationshipType.OneToMany);
					oneToManyColumnList.add(column);
				}
			}
			if (oneToManyColumnList.size() == 2
					&& table.getColumns().size() == 2) {
				relationshipMap.remove(oneToManyColumnList.get(0));
				relationshipMap.remove(oneToManyColumnList.get(1));
				relationshipMap.put(oneToManyColumnList.get(0), RelationshipType.ManyToMany);
				relationshipMap.put(oneToManyColumnList.get(1), RelationshipType.ManyToMany);
			}
		}
		return relationshipMap;
	}
}
