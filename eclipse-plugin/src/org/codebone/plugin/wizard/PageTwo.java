package org.codebone.plugin.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class PageTwo extends WizardPage {
	
	  private Composite container;
	  
	  private MouseListener mouseListener = null;
	  
	  private List<String> tables = null;
	  private List<Button> checkButtons = null;
	  
	  private ScrolledComposite sc;

	public PageTwo() {
	    super("Tables");
	    setTitle("Tables");
	    setDescription("Choose database table");
	  }

	  @Override
	  public void createControl(Composite parent) {
		  mouseListener = new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (checkButtons != null) {
					
					for (int t = 0; t<checkButtons.size(); t++) {
						if(checkButtons.get(t).getSelection()) {
							CodeboneWizard wizard = (CodeboneWizard) getWizard();
							wizard.setSelectedTableIdx(t);
							
							if (wizard.loadColumns() != null) {
								setPageComplete(true);
							}
							else {
								setPageComplete(false);
							}
							
							break;
						}
					}
				}
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		};
		  
		sc = new ScrolledComposite(parent, SWT.BORDER | SWT.V_SCROLL);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		container = new Composite(sc, SWT.NULL);
		sc.setContent(container);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		container.setLayout(layout);
		sc.setMinSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	        
		    Label label0 = new Label(container, SWT.NULL);
		    label0.setText("Select");
		    
		    Label label1 = new Label(container, SWT.NULL);
		    label1.setText("Name");
		    
		  draw();
	    
	    //check.setSelection(true);
	    // Required to avoid an error in the system

		setControl(sc);
	    setPageComplete(false);
	  }
	  
	  void draw(){

		    //loop
		    if (tables != null ) {
		    	checkButtons = new ArrayList<Button>();
		    	
		    	for (int i=0;i<tables.size();i++) {
		    		String tableName = tables.get(i);
		    		
		    		Button check = new Button(container, SWT.RADIO);
		    		check.addMouseListener(mouseListener);
		    		checkButtons.add(check);
		    		
		    	    Label label_t1 = new Label(container, SWT.NULL);
		    	    label_t1.setText(tableName);
		    	}
		    }
		    
		    container.layout();
		    sc.setMinSize(container.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	  }
	  
	  /**
	 * @return the tables
	 */
	public List<String> getTables() {
		return tables;
	}

	/**
	 * @param tables the tables to set
	 */
	public void setTables(List<String> tables) {
		this.tables = tables;
	}
}
