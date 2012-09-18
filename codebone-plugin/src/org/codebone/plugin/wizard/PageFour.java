package org.codebone.plugin.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
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
	
	private Text titleText;
	private Text uriText;
	private Text packageText;
	private Text templateText;
	private Text generateText;
	
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
	  public void createControl(final Composite parent) {
			
	    container = new Composite(parent, SWT.NULL);
	    GridLayout layout = new GridLayout();
	    container.setLayout(layout);
	    layout.numColumns = 3;
	    
	 // Title 설정
	    Label titleLabel = new Label(container, SWT.NULL);
	    titleLabel.setText("Title");
	    
	    titleText = new Text(container, SWT.BORDER | SWT.SINGLE);
	    titleText.setText("");
	    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
	    titleText.setLayoutData(gd);
	    
	    Label titleDescription = new Label(container, SWT.NONE);
	    titleDescription.setText("This is Title");
	    
	 // URI 설정
	    Label uriLabel = new Label(container, SWT.NULL);
	    uriLabel.setText("URI");
	    
	    uriText = new Text(container, SWT.BORDER | SWT.SINGLE);
	    uriText.setText("");
	    uriText.setLayoutData(gd);
	    
	    Label uriDescription = new Label(container, SWT.NONE);
	    uriDescription.setText("This is URI");
	    
	 // Package 설정
	    Label packageLabel = new Label(container, SWT.NULL);
	    packageLabel.setText("Package");
	    
	    packageText = new Text(container, SWT.BORDER | SWT.SINGLE);
	    packageText.setText("");
	    packageText.setLayoutData(gd);
	    
	    Label packageDescription = new Label(container, SWT.NONE);
	    packageDescription.setText("This is Package");
	    
	 // Template Folder 설정
	    Label templateLabel = new Label(container, SWT.NULL);
	    templateLabel.setText("Template Folder");
	    
	    templateText = new Text(container, SWT.BORDER | SWT.SINGLE);
	    templateText.setText("");
	    templateText.setLayoutData(gd);
	    
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
	    
	 // Generate Folder 설정
	    Label generateLabel = new Label(container, SWT.NULL);
	    generateLabel.setText("Generate Folder");
	    
	    generateText = new Text(container, SWT.BORDER | SWT.SINGLE);
	    generateText.setText("");
	    generateText.setLayoutData(gd);
	    
	    Button generateButton = new Button(container, SWT.PUSH);
	    generateButton.setText("Browse");
	    generateButton.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent e) {
	    DirectoryDialog dialog = new DirectoryDialog(parent.getShell(), SWT.NULL);
	    String path = dialog.open();
	    if (path != null) {
	    	generateText.setText(path);
	    }
	    }
	    });
	    
	    Button completeThisPageButton = new Button(container, SWT.PUSH);
	    completeThisPageButton.setText("Generate");
	    completeThisPageButton.addSelectionListener(new SelectionAdapter() {
	    public void widgetSelected(SelectionEvent e) {

	    	if(isCompleteInputData()){
				TestWizard wizard = (TestWizard)getWizard();
				if(wizard.perfomeStepFour()) {
					setPageComplete(true);
				}
			}
			else {
				setPageComplete(false);
			}
	    }
	    });
	    
	    
	    // Required to avoid an error in the system
	    setControl(container);
	    setPageComplete(false);
	  }

	  public boolean isCompleteInputData() {
		  if (titleText.getText().isEmpty()||
				  uriText.getText().isEmpty()||
				  packageText.getText().isEmpty()||
				  templateText.getText().isEmpty()||
				  generateText.getText().isEmpty() ) {
			  return false;
		  }
		  else {
			  return true;
		  }
	  }
	  
	  //Getter
	  public String getTitle() {
	    return titleText.getText();
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
	  
	  public String getGeneratePath() {
		  return generateText.getText();
	  }
}
