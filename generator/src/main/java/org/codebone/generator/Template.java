package org.codebone.generator;

public class Template {
	
	public static String COLUMN_LOOP_REGEX = "\\{COLUMN_LOOP\\}.*?\\{/COLUMN_LOOP\\}";
	public static String COLUMN_LOOP_EXCLUDE_PREDEFINED_REGEX = "\\{COLUMN_LOOP_EXCLUDE_PREDEFINED\\}.*?\\{/COLUMN_LOOP_EXCLUDE_PREDEFINED\\}";
	
	public static String SEARCH = "\\{SEARCH\\}.*?\\{/SEARCH\\}";
	public static String COLUMN_LOOP_SEARCH_REGEX = "\\{COLUMN_LOOP_SEARCH\\}.*?\\{/COLUMN_LOOP_SEARCH\\}";
	
	public static String SITE_TITLE = "\\{SITE_TITLE\\}";
	public static String PACKAGE = "\\{PACKAGE\\}";
	public static String MAPPING_URI = "\\{MAPPING_URI\\}";
	public static String TABLE_NAME = "\\{TABLE_NAME\\}";
	public static String TABLE_NAME_UPPERCASE = "\\{TABLE_NAME_UPPERCASE\\}";
	public static String TABLE_NAME_CAMELCASE = "\\{TABLE_NAME_CAMELCASE\\}";
	
	public static String COLUMN_ID = "\\{COLUMN_HIBERNATE_ID_ANNOATION\\}";
	public static String COLUMN_ID_GENERATE = "@Id\r\n\t@GeneratedValue(strategy = GenerationType.IDENTITY)\r\n\t";
	public static String COLUMN_NAME = "\\{COLUMN_NAME\\}";
	public static String COLUMN_NAME_CAMELCASE = "\\{COLUMN_NAME_CAMELCASE\\}";
	public static String COLUMN_TYPE = "\\{COLUMN_TYPE\\}";
	public static String COLUMN_DEFAULT_VALUE = "\\{COLUMN_DEFAULT_VALUE\\}";
	public static String COLUMN_DESCRIPTION = "\\{COLUMN_DESCRIPTION\\}";
}
