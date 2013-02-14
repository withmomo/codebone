package org.codebone.connector;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codebone.console.Relationship;

public class Column {
	private String name;
	private int type;
	private String typeName;
	private String javaType;
	private String defaultValue;
	private int size;
	private String description;
	private boolean isPrimaryKey;
	private boolean isSearchable;
	private boolean isPredefined = false;
	private boolean isForeignKey = false;
	private String relationAnnotation;
	private String optionAnnotation;
	private String anotherPackage;
	private Relationship relation;
	
	
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
	
	public Column() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static String defaultValue(String type) {
		type = type.toLowerCase();
		String defaultValue = null;
		if (type.startsWith("int")) {
			defaultValue = "0";
		} else if (type.startsWith("tinyint")) {
			defaultValue = "0";
		} else if (type.startsWith("float")) {
			defaultValue = "0f";
		} else if (type.startsWith("double")) {
			defaultValue = "0.0";
		} else if (type.startsWith("bigint")) {
			defaultValue = "new Long(0)";
		} else if (type.startsWith("smallint")) {
			defaultValue = "0";
		} else if (type.startsWith("bit")){
			if(type.equals("bit(1)")){
				defaultValue = "false";
			}else{
				defaultValue = "0";
			}
		} else if (type.startsWith("varchar") || type.startsWith("text")) {
			defaultValue = "\"\"";
		} else if (type.startsWith("timestamp") || type.equals("date") || type.startsWith("datetime") || type.startsWith("smalldatetime")) {
			defaultValue = "new Date()";
		} else {
			defaultValue = "\"\"";
		}
		
		return defaultValue;
	}

	public static String transformJavaType(String type) {
		type = type.toLowerCase();
		String transformedType = null;
		if (type.startsWith("int")) {
			transformedType = "Integer";
		} else if (type.startsWith("tinyint")) {
			transformedType = "Integer";
		} else if (type.startsWith("float")) {
			transformedType = "Float";
		} else if (type.startsWith("double")) {
			transformedType = "Double";
		} else if (type.startsWith("bigint")) {
			transformedType = "Long";
		} else if (type.startsWith("smallint")) {
			transformedType = "Integer";
		} else if (type.startsWith("varchar") || type.startsWith("text")) {
			transformedType = "String";
		} else if (type.startsWith("bit")) {
			if(type.equals("bit(1)")){
				transformedType = "boolean";
			}else{
				transformedType = "Byte";
			}
		} else if (type.startsWith("timestamp") || type.equals("date") || type.startsWith("datetime") || type.startsWith("smalldatetime")) {
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
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

	public boolean isPredefined() {
		return isPredefined;
	}

	public void setPredefined(boolean isPredefined) {
		this.isPredefined = isPredefined;
	}

	public String getJavaType() {
		if(this.javaType!=null){
			return this.javaType;
		}
		return transformJavaType(this.typeName);
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	/**
	 * @return the isForeignKeyField
	 */
	public boolean isForeignKey() {
		return isForeignKey;
	}

	/**
	 * @param isForeignKeyField the isForeignKeyField to set
	 */
	public void setForeignKey(boolean isForeignKey) {
		this.isForeignKey = isForeignKey;
	}

	public String getRelationAnnotation() {
		return relationAnnotation;
	}

	public void setRelationAnnotation(String relationAnnotation) {
		this.relationAnnotation = relationAnnotation;
	}

	public String getOptionAnnotation() {
		return optionAnnotation;
	}

	public void setOptionAnnotation(String optionAnnotation) {
		this.optionAnnotation = optionAnnotation;
	}

	/**
	 * @return the relation
	 */
	public Relationship getRelation() {
		return relation;
	}

	/**
	 * @param relation the relation to set
	 */
	public void setRelation(Relationship relation) {
		this.relation = relation;
	}

	/**
	 * @return the anotherPackage
	 */
	public String getAnotherPackage() {
		return anotherPackage;
	}

	/**
	 * @param anotherPackage the anotherPackage to set
	 */
	public void setAnotherPackage(String anotherPackage) {
		this.anotherPackage = anotherPackage;
	}
}