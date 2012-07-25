package org.codebone.generator;

public class Template {
	
	public static String COLUMN_LOOP_REGEX = "<COLUMN_LOOP>.*?</COLUMN_LOOP>";
	
	public static String PACKAGE = "<PACKAGE>";
	public static String MAPPING_URI = "<MAPPING_URI>";
	public static String TABLE_NAME = "<TABLE_NAME>";
	public static String TABLE_NAME_CAMELCASE = "<TABLE_NAME_CAMELCASE>";
	
	public static String COLUMN_ID = "<COLUMN_ID>";
	public static String COLUMN_ID_GENERATE = "@Id\r\n\t";
	public static String COLUMN_NAME = "<COLUMN_NAME>";
	public static String COLUMN_NAME_CAMELCASE = "<COLUMN_NAME_CAMELCASE>";
	public static String COLUMN_TYPE = "<COLUMN_TYPE>";
	public static String COLUMN_DESCRIPTION = "<COLUMN_DESCRIPTION>";
}
