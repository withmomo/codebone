package org.codebone.plugin.wizard;

import java.util.ArrayList;

import org.codebone.generator.connector.Column;
import org.eclipse.jface.wizard.Wizard;

public class TestWizard extends Wizard {

	protected TestPageTwo one;
	protected TestPageTwo two;
	protected PageThree   three;
	protected PageFour    four;

	public TestWizard() {
	    super();
	    setNeedsProgressMonitor(true);
	  }
	
	@Override
	  public void addPages() {
	    one   = new TestPageTwo();
	    two   = new TestPageTwo();
	    three = new PageThree();
	    four  = new PageFour();
	    /*
	    Column column = new Column("name", 1, "String", 16, "", "", true, true);
	    ArrayList<Column> list = new ArrayList<Column>();
	    list.add(column);
	    three.setColumnList(list);
	    */
	    addPage(one);
	    addPage(two);
	    addPage(three);
	    addPage(four);
	  }

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		System.out.println(one.getText1());
	    System.out.println(two.getText1());
		return false;
	}

	public boolean perfomeStepOne() {
		
		return true;
	}
	
	public boolean perfomeStepTwo() {
		
		return true;
	}

	public boolean perfomeStepThree() {
	
		return true;
	}

	public boolean perfomeStepFour() {
		
		return true;
	}
}
