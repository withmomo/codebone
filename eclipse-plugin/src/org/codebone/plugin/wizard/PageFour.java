package org.codebone.plugin.wizard;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class PageFour extends WizardPage {
	
	private Text websiteTitleText;
	private Text uriText;
	private Text packageText;
	private Text templateText;
	private Text generateText;
	
	private KeyListener keyListener;
	
	  private Composite container;

	  public PageFour() {
	    super("Settings");
	    setTitle("Settings");
	    setDescription("Generate Code Settings");
	    setControl(websiteTitleText);
	    setControl(uriText);
	    setControl(packageText);
	  }

	  @Override
	  public void createControl(final Composite parent) {
			
		  keyListener = new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
				if(isCompleteInputData()){
					setPageComplete(true);
				}
				else {
					setPageComplete(false);
				}
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
	    
	    websiteTitleText = new Text(container, SWT.BORDER | SWT.SINGLE);
	    websiteTitleText.setText("");
	    websiteTitleText.addKeyListener(keyListener);
	    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
	    websiteTitleText.setLayoutData(gd);
	    
	    Label titleDescription = new Label(container, SWT.NONE);
	    titleDescription.setText("Website Title");
	    
	 // URI 설정
	    Label uriLabel = new Label(container, SWT.NULL);
	    uriLabel.setText("URI");
	    
	    uriText = new Text(container, SWT.BORDER | SWT.SINGLE);
	    uriText.setText("");
	    uriText.addKeyListener(keyListener);
	    uriText.setLayoutData(gd);
	    
	    Label uriDescription = new Label(container, SWT.NONE);
	    uriDescription.setText("cf. @RequestMapping(\"/app\")");
	    
	 // Package 설정
	    Label packageLabel = new Label(container, SWT.NULL);
	    packageLabel.setText("Package");
	    
	    packageText = new Text(container, SWT.BORDER | SWT.SINGLE);
	    packageText.setText("");
	    packageText.addKeyListener(keyListener);
	    packageText.setLayoutData(gd);
	    
	    Label packageDescription = new Label(container, SWT.NONE);
	    packageDescription.setText("Java package, C# Namespace , etc");
	    
	 // Template Folder 설정
	    Label templateLabel = new Label(container, SWT.NULL);
	    templateLabel.setText("Template Folder");
	   
	    CodeboneWizard wizard = (CodeboneWizard)getWizard();
	    IPath templatePath = new Path(wizard.getProjectPath().toString()+"/template");
	    
	    templateText = new Text(container, SWT.BORDER | SWT.SINGLE);
	    IPath fullPath = new Path(templatePath.toOSString());
	    templateText.setText(fullPath.toOSString());
	    templateText.setLayoutData(gd);
	    templateText.setEditable(false);
	   
	    
	    
	    Button templateButton = new Button(container, SWT.PUSH);
	    templateButton.setText("Browse");
	    templateButton.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent e) {
	    DirectoryDialog dialog = new DirectoryDialog(parent.getShell(), SWT.NULL);
	    String path = dialog.open();
	    if (path != null) {
	    	templateText.setText(path);
	    	
	    	if(isCompleteInputData()){
				setPageComplete(true);
			}
			else {
				setPageComplete(false);
			}
	    }
	    }
	    });  
	    
	    // Required to avoid an error in the system
	    setControl(container);
	    setPageComplete(false);
	  }

	  public boolean isCompleteInputData() {
		  if (websiteTitleText.getText().isEmpty()||
				  uriText.getText().isEmpty()||
				  packageText.getText().isEmpty()||
				  templateText.getText().isEmpty() ) {
			  return false;
		  }
		  else {
			  return true;
		  }
	  }
	  
	  //Getter
	  public String getTitle() {
	    return websiteTitleText.getText();
	  }
	  
	  public String getUri() {
		  return uriText.getText();
	  }
	  
	  public String getPackage() {
		  return packageText.getText();
	  }
	  
	  public String getTemplatePath() {
		  return templateText.getText();
	  }
	  
	  public void setTemplatePath(String path) {
		  templateText.setText(path);
	  }
	  
	  public String getGeneratePath() {
		  return generateText.getText();
	  }
}
