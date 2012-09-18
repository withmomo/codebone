package org.codebone.plugin.wizard;

import org.eclipse.jface.wizard.Wizard;

public class TestWizard extends Wizard {

	protected TestPageOne one;
	protected TestPageTwo two;
	protected PageFour four;

	public TestWizard() {
	    super();
	    setNeedsProgressMonitor(true);
	  }
	
	@Override
	  public void addPages() {
	    one = new TestPageOne();
	    two = new TestPageTwo();
	    four = new PageFour();
	    
	    addPage(one);
	    addPage(two);
	    addPage(four);
	  }

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		System.out.println(one.getText1());
	    System.out.println(two.getText1());
		return false;
	}

}
