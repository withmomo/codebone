package org.codebone.plugin.wizard;

import java.util.ArrayList;
import java.util.List;

import org.codebone.generator.connector.Column;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class PageThree extends WizardPage {
	
	final static String COLUMN_NAME 		= "columnName";
	final static String COLUMN_DESCRIPCION 	= "columnDescription";
	final static String COLUMN_TYPE 		= "columnType";
	final static String COLUMN_SEARCHABLE 	= "columnSearchable";
	
	  private Composite container;

	  private List<Column> columns     = null;
	  private List<Text>   inputText   = null;
	  private List<Button> inputButton = null;
	  
	  public PageThree() {
	    super("Columns");
	    setTitle("Columns");
	    setDescription("Fill columns. If you see 'selectbox', column description reduce your code." +
	    		"When column description is 'operation mode{0=development,1=staging}',wizard replace 'selectbox'." +
	    		"" +
	    		"Column description : mode{0=development, 1=staging}");
	  }

	  @Override
	  public void createControl(Composite parent) {
	    container = new Composite(parent, SWT.NULL);
	    GridLayout layout = new GridLayout();
	    container.setLayout(layout);
	    layout.numColumns = 5;
	    
	    Label label0 = new Label(container, SWT.NULL);
	    label0.setText("");
	    
	    Label label1 = new Label(container, SWT.NULL);
	    label1.setText("Name");
	    
	    Label label2 = new Label(container, SWT.NULL);
	    label2.setText("Description");
	    
	    Label label3 = new Label(container, SWT.NULL);
	    label3.setText("Type");
	    
	    Label label4 = new Label(container, SWT.NULL);
	    label4.setText("Searchable");
	    
	    //check.setSelection(true);
	    // Required to avoid an error in the system
	    setControl(container);
	    setPageComplete(false);
	    
	    setPageComplete(true);
	  }
	  
	  public void draw() {
		  inputText = new ArrayList<Text>();
		  inputButton = new ArrayList<Button>();
		  
		    if (columns != null) {
		    	
		    	for (int i=0;i<columns.size();i++) {
		    		Column column = columns.get(i);
		    		
				    //loop
				    Label label_t1 = new Label(container, SWT.NULL);
				    label_t1.setText(""+(i+1));
				    
				    Label label_t2 = new Label(container, SWT.NULL);
				    label_t2.setText(column.getName());
				    
				    Text text_t1 = new Text(container, SWT.SINGLE);
				    text_t1.setText(column.getName());
				    inputText.add(text_t1);
				    
				    Label label_t3 = new Label(container, SWT.NULL);
				    label_t3.setText(column.getTypeName());
				    
				    Button check = new Button(container, SWT.CHECK);
				    inputButton.add(check);
		    	}
		    }
		    
		    container.layout();
	  }

	/**
	 * @return the columns
	 */
	public List<Column> getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	  
	public String getDescription(int idx) {
		return inputText.get(idx).getText();
	}
	
	public boolean getSearchable(int idx) {
		return inputButton.get(idx).getSelection();
	}
	  
}
