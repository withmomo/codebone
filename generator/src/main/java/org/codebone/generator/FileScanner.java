package org.codebone.generator;

import java.io.File;
import java.io.FileNotFoundException;

public class FileScanner {
	
	public interface FileListner {
		public void found(File file);
	}
	
	private FileListner fileListner;
	
	public FileScanner(String path, FileListner fileListner) throws FileNotFoundException {
		this.fileListner = fileListner;
		
		File file = new File(path);
		if( !file.exists() )
			throw new FileNotFoundException();
		
		scan(file);
	}
	
	private void scan(File file) {
		if (file.isDirectory()) {
			for (File fileObject : file.listFiles())
				scan(fileObject);
		} else {
			if( fileListner != null )
				fileListner.found(file);
		}
	}
}
