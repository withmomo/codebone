package org.codebone.plugin.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class PageFour extends WizardPage {
	
	private Text titleText;
	private Text uriText;
	private Text packageText;
	
	KeyListener keyListner = null;
	
	  private Composite container;

	  public PageFour() {
	    super("Fourth Page");
	    setTitle("Fourth Page");
	    setDescription("Now this is the fourth page");
	    setControl(titleText);
	    setControl(uriText);
	    setControl(packageText);
	  }

	  @Override
	  public void createControl(Composite parent) {
		  keyListner = new KeyListener() {
				
				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			};
			
	    container = new Composite(parent, SWT.NULL);
	    GridLayout layout = new GridLayout();
	    container.setLayout(layout);
	    layout.numColumns = 3;
	    
	 // Title 설정
	    Label titleLabel = new Label(container, SWT.NULL);
	    titleLabel.setText("Title");
	    
	    titleText = new Text(container, SWT.BORDER | SWT.SINGLE);
	    titleText.setText("");
	    titleText.addKeyListener(keyListner);
	    
	    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
	    titleText.setLayoutData(gd);
	    Label titleDescription = new Label(container, SWT.NONE);
	    titleDescription.setText("This is Title");
	    
	 // URI 설정
	    Label uriLabel = new Label(container, SWT.NULL);
	    uriLabel.setText("URI");
	    
	    uriText = new Text(container, SWT.BORDER | SWT.SINGLE);
	    uriText.setText("");
	    uriText.addKeyListener(keyListner);
	    uriText.setLayoutData(gd);
	    
	    Label uriDescription = new Label(container, SWT.NONE);
	    uriDescription.setText("This is URI");
	    
	 // Package 설정
	    Label packageLabel = new Label(container, SWT.NULL);
	    packageLabel.setText("Package");
	    
	    packageText = new Text(container, SWT.BORDER | SWT.SINGLE);
	    packageText.setText("");
	    packageText.addKeyListener(keyListner);
	    packageText.setLayoutData(gd);
	    
	    Label packageDescription = new Label(container, SWT.NONE);
	    packageDescription.setText("This is Package");
	    
	    
	    // Required to avoid an error in the system
	    setControl(container);
	    setPageComplete(false);
	  }

	  public String getText1() {
	    return titleText.getText();
	  }
}
