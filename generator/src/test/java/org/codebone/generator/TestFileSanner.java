package org.codebone.generator;

import java.io.File;
import java.io.FileNotFoundException;

import org.codebone.generator.FileScanner;
import org.codebone.generator.FileScanner.FileListner;
import org.junit.Test;

public class TestFileSanner {
	@Test
	public void scan() throws FileNotFoundException {
		String path = "src/main/template";
		new FileScanner(path, new FileListner() {
			public void found(File file) {
				System.out.println(file.getPath());
			}
		});
	}
}
