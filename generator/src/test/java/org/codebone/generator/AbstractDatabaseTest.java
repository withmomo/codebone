package org.codebone.generator;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractDatabaseTest {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractDatabaseTest.class);
	
	@Before
	public void tearup() {
		logger.info("tearup");
	}
	
	@After
	public void teardown() {
		logger.info("teardown");
	}
}
