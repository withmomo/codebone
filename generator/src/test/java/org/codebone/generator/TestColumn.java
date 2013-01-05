package org.codebone.generator;

import junit.framework.Assert;

import org.codebone.connector.Column;
import org.junit.Test;

public class TestColumn {
	@Test
	public void transformJavaType() {
		String type;
		String javaType;
		
		type = "int unsigned";
		javaType = Column.transformJavaType(type);
		Assert.assertEquals("Integer", javaType);
	}
}
