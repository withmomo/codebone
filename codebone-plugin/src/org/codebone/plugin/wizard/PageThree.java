package org.codebone.plugin.wizard;

import java.util.List;

import org.codebone.generator.connector.Column;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class PageThree extends WizardPage {
	
	private List<Column> columnList;
	
	  private Composite container;

	  public PageThree() {
	    super("Fourth Page");
	    setTitle("Fourth Page");
	    setDescription("Now this is the fourth page");
	  }

	  @Override
	  public void createControl(final Composite parent) {
			
	    container = new Composite(parent, SWT.NULL);
	    GridLayout layout = new GridLayout();
	    container.setLayout(layout);
	    layout.numColumns = 5;
	    
	    Label idxLabel = new Label(container, SWT.NULL);
	    idxLabel.setText("");
	    Label nameLabel = new Label(container, SWT.NULL);
	    nameLabel.setText("Name");
	    Label descriptionLabel = new Label(container, SWT.NULL);
	    descriptionLabel.setText("Desciption");
	    Label typeLabel = new Label(container, SWT.NULL);
	    typeLabel.setText("Type");
	    Label searchableLabel = new Label(container, SWT.NULL);
	    searchableLabel.setText("Searchable");
	    
	    for(int i=0;i<columnList.size();i++) {
	    	Column column = columnList.get(i);
	    	
	    	Label columnIdxLabel = new Label(container, SWT.NULL);
	    	columnIdxLabel.setText(""+(i+1));
	    	Label columnNameLabel = new Label(container, SWT.NULL);
	    	columnNameLabel.setText(column.getName());
		    Text columnDescriptionText = new Text(container, SWT.NULL);
		    columnDescriptionText.setText("");
		    Label columnTypeLabel = new Label(container, SWT.NULL);
		    columnTypeLabel.setText(column.getTypeName());
		    Button columnSearchableButton = new Button(container, SWT.CHECK);
	    }

	    
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
		  return true;
	  }

	  public void setColumnList(List<Column> columnList){
		  this.columnList = columnList;
	  }
}
