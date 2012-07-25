package org.codebone.generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

public class FileUtils {
	public static final String CRLF = "\r\n";
	
	public static String read(String path) {
		return read(new File(path));
	}
	
	public static String read(File file) {
		BufferedReader in = null;
		String s = null;
		String data = "";

		try {
			in = new BufferedReader(new FileReader(file));
			while ((s = in.readLine()) != null) {
				data = data + s + CRLF;
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return data;
	}
	
	public static void write(File file, String data) {
		try {
			if( !file.exists() ) {
				file.createNewFile();
			}
			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
			out.write(data);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void write(String path, String data) {
		write(new File(path), data);
	}
}
