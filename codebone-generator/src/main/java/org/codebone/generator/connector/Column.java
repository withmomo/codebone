package org.codebone.generator.connector;

public class Column {
	private String name;
	private int type;
	private String typeName;
	private String defaultValue;
	private int size;
	private String description;
	private boolean isPrimaryKey;
	private boolean isSearchable;

	public Column(String name, int type, String typeName, int size, String defaultValue, String description, boolean isPrimaryKey, boolean isSearchable) {
		this.name = name;
		this.type = type;
		this.typeName = typeName;
		this.size = size;
		this.defaultValue = defaultValue;
		this.description = description;
		this.isPrimaryKey = isPrimaryKey;
		this.isSearchable = isSearchable;
	}
	
	public static String defaultValue(String type) {
		type = type.toLowerCase();
		String defaultValue = null;
		if ("int".equals(type)) {
			defaultValue = "0";
		} else if ("tinyint".equals(type)) {
			defaultValue = "0";
		} else if ("float".equals(type)) {
			defaultValue = "0f";
		} else if ("double".equals(type)) {
			defaultValue = "0.0";
		} else if ("bigint".equals(type)) {
			defaultValue = "new Long(0)";
		} else if ("smallint".equals(type)) {
			defaultValue = "0";
		} else if ("varchar".equals(type) || "datetime".equals(type) || "smalldatetime".equals(type) || "text".equals(type)) {
			defaultValue = "\"\"";
		} else if ("timestamp".equals(type)) {
			defaultValue = "new Date()";
		} else {
			defaultValue = "\"\"";
		}
		
		return defaultValue;
	}

	public static String transformJavaType(String type, DatabaseType databaseType) {
		type = type.toLowerCase();
		String transformedType = null;
		if ("int".equals(type) && DatabaseType.MSSQL == databaseType) {
			transformedType = "Integer";
		} else if ("int".equals(type) && DatabaseType.MYSQL == databaseType) {
			transformedType = "Integer";
		} else if ("tinyint".equals(type)) {
			transformedType = "Integer";
		} else if ("float".equals(type)) {
			transformedType = "Float";
		} else if ("double".equals(type)) {
			transformedType = "Float";
		} else if ("bigint".equals(type)) {
			transformedType = "Long";
		} else if ("smallint".equals(type)) {
			transformedType = "Integer";
		} else if ("varchar".equals(type) || "datetime".equals(type) || "smalldatetime".equals(type) || "text".equals(type)) {
			transformedType = "String";
		} else if ("bit".equals(type)) {
			transformedType = "Byte";
		} else if ("timestamp".equals(type)) {
			transformedType = "Date";
		} else {
			transformedType = "String";
		}
		return transformedType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isSearchable() {
		return isSearchable;
	}

	public void setSearchable(boolean isSearchable) {
		this.isSearchable = isSearchable;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
}