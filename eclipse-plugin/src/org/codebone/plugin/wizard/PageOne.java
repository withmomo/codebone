package org.codebone.plugin.wizard;

import java.util.List;

import org.codebone.generator.connector.DatabaseConfiguration;
import org.codebone.generator.connector.DatabaseType;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class PageOne extends WizardPage {

	private Combo dbType_c;
	private Text host_t;
	private Text port_t;
	private Text dbName_t;
	private Text id_t;
	private Text password_t;
	private Button connect_b;
	private MessageBox message_m;
	
	  private Composite container;

	  public PageOne() {
	    super("Database Configuration");
	    setTitle("Database Configuration");
	    setDescription("Fill database configuration");
	  }

	  @Override
	  public void createControl(Composite parent) {
	    container = new Composite(parent, SWT.NULL);
	    final Shell OneShell = parent.getShell();
	    GridLayout layout = new GridLayout();
	    container.setLayout(layout);
	    layout.numColumns = 2;
	    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
	    
	    Label host_l = new Label(container, SWT.NULL);
	    host_l.setText("Host");

	    host_t = new Text(container, SWT.BORDER | SWT.SINGLE);
	    host_t.setText("localhost");
	    host_t.setLayoutData(gd);
	    /*host_t.addKeyListener(new KeyListener() {

	      @Override
	      public void keyPressed(KeyEvent e) {
	      }

	      @Override
	      public void keyReleased(KeyEvent e) {
	        if (!host_t.getText().isEmpty()) {
	          setPageComplete(true);
	        }
	      }

	    });*/
	    
	    Label dbType_l = new Label(container, SWT.NULL);
	    dbType_l.setText("Database Type");
	    
	    dbType_c = new Combo(container, SWT.BORDER | SWT.SINGLE);
	    dbType_c.add("MySQL");
	    dbType_c.add("MSSQL");
	    dbType_c.setText("MySQL");
	    //dbType_c.setLayoutData(gd);
	    
	    Label port_l = new Label(container, SWT.NULL);
	    port_l.setText("Port");
	    
	    port_t = new Text(container, SWT.BORDER | SWT.SINGLE);
		port_t.setText("3306");
		port_t.setLayoutData(gd);
		
		Label dbName_l = new Label(container, SWT.NULL);
		dbName_l.setText("Database Name");
		
		dbName_t = new Text(container, SWT.BORDER | SWT.SINGLE);
		dbName_t.setText("");
		dbName_t.setLayoutData(gd);
		
		Label id_l = new Label(container, SWT.NULL);
		id_l.setText("ID");
		
		id_t = new Text(container, SWT.BORDER | SWT.SINGLE);
		id_t.setText("root");
		id_t.setLayoutData(gd);
		
		Label password_l = new Label(container, SWT.NULL);
		password_l.setText("Password");
		
		password_t = new Text(container, SWT.BORDER | SWT.SINGLE | SWT.PASSWORD);
		password_t.setText("");
		password_t.setLayoutData(gd);
		
		Label connect_t = new Label(container, SWT.NULL);
		connect_t.setText("Connection Test");
	    
	    connect_b = new Button(container, SWT.PUSH);
	    connect_b.setText("Check");
	    connect_b.addMouseListener(new MouseListener() {

			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	
			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				// JDBC
				
				if(loadTables()!=null) {
					message_m = new MessageBox(OneShell, SWT.OK);
					message_m.setText("Connection Test");
					message_m.setMessage("Database connect success.");
					message_m.open();
					
					setPageComplete(true);
				}
				else {
					message_m = new MessageBox(OneShell, SWT.OK);
					message_m.setText("Connection Test");
					message_m.setMessage("Database connect fail. Please retry.");
					message_m.open();
					
					setPageComplete(false);
				}
			}
	
			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
			
		}

	    });
		
	    // Required to avoid an error in the system
	    setControl(container);
	    setPageComplete(false);

	  }
	  
	  public List<String> loadTables() {
		  
		  DatabaseType databaseType = getDbType();
		  if (databaseType == null) {
			  return null;
		  }
		  
		  DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(databaseType, getHost(), getPort(), getDbName(), getId(), getPassword());
		  
		  CodeboneWizard wizard = (CodeboneWizard)getWizard();
		  List<String> tables = wizard.loadTables(databaseConfiguration);
		  
		  return tables;
	}
	  
	  public String getHost() {
		  
		  return host_t.getText();
	  }
	  
	  public Integer getPort() {
		  Integer integerPort = null;
		  integerPort = Integer.parseInt(port_t.getText());
		  return integerPort;
	  }
	  
	  public DatabaseType getDbType() {
		  if (dbType_c.getText().equals("MySQL")) {
			  return DatabaseType.MYSQL;
		  }
		  else if (dbType_c.getText().equals("MSSQL")) {
			  return DatabaseType.MSSQL;
		  }
		  else {
			  return null;
		  }
	  }
	  
	  public String getDbName() {
		  return dbName_t.getText();
	  }
	  
	  public String getId() {
		  return id_t.getText();
	  }
	  
	  public String getPassword() {
		  return password_t.getText();
	  }
}
