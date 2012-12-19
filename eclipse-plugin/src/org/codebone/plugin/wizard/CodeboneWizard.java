package org.codebone.plugin.wizard;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.codebone.generator.Generator;
import org.codebone.generator.connector.Column;
import org.codebone.generator.connector.DatabaseConfiguration;
import org.codebone.generator.connector.DatabaseConnector;
import org.codebone.generator.connector.DatabaseHelper;
import org.codebone.generator.connector.MySQLDatabaseConnector;
import org.codebone.generator.connector.TableHelper;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

public class CodeboneWizard extends Wizard {

	private IPath projectPath = null;
	
	private DatabaseConfiguration databaseConfiguration;
	private List<String> tables;
	private List<Column> columns;
	
	private Integer selectedTableIdx = 0;

	protected PageOne 	one;
	protected PageTwo 	two;
	protected PageThree three;
	protected PageFour  four;

	public CodeboneWizard() {
	    super();
	    setNeedsProgressMonitor(true);
	  }
	
	@Override
	  public void addPages() {
	    one   = new PageOne();
	    two   = new PageTwo();
	    three = new PageThree();
	    four  = new PageFour();
	    
	    addPage(one);
	    addPage(two);
	    addPage(three);
	    addPage(four);
	  }

	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		columnUpdate();
		
		IPath generatePath = new Path(projectPath.toString()+"/src/main");
		
		Generator generator = new Generator();
		generator.setDatabaseType(one.getDbType());
		generator.setTeamplatePath(four.getTemplatePath());
		generator.setGeneratePath(generatePath.toOSString());
		generator.setColumns(columns);
		generator.setTableName(tables.get(selectedTableIdx));
		generator.setPackageName(four.getPackage());
		generator.setUri(four.getUri());
		generator.setSiteTitle(four.getTitle());
		
		if (generator.generate()) {
			MessageBox message_m = new MessageBox(getShell(), SWT.OK);
			message_m.setText("Codebone");
			message_m.setMessage("Codebone Code Generate Success!");
			message_m.open();
			
			return true;
		}
		else {
			MessageBox message_m = new MessageBox(getShell(), SWT.OK);
			message_m.setText("Codebone");
			message_m.setMessage("Codebone Code Generate fail.");
			message_m.open();
			
			return false;
		}
	}
	
	public List<String> loadTables(DatabaseConfiguration databaseConfiguration) {
		this.databaseConfiguration = databaseConfiguration;
		tables = null;
		
		try {
			Connection connection = null;
			DatabaseConnector databaseConnector = new MySQLDatabaseConnector(databaseConfiguration);
			connection = databaseConnector.getConnection();
			tables = DatabaseHelper.getTables(databaseConnector);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (tables!=null) {
			two.dispose();
			two.setTables(tables);
			two.draw();
		}
		
		return tables;
	}
	
	public List<Column> loadColumns() {
		columns = null;
		String tableName = tables.get(selectedTableIdx);
		try {
			Connection connection = null;
			DatabaseConnector databaseConnector = new MySQLDatabaseConnector(databaseConfiguration);
			connection = databaseConnector.getConnection();
			columns = TableHelper.getColumns(databaseConnector, tableName);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(columns != null) {
			three.setColumns(columns);
			three.draw();
		}
		
		return columns;
	}
	
	public void columnUpdate() {
		for (int i=0; i<columns.size(); i++) {
			Column column = columns.get(i);
			column.setDescription(three.getDescription(i));
			column.setSearchable(three.getSearchable(i));
			column.setPredefined(!three.getWriteables(i));
		}
	}
	
	/**
	 * @return the selectedTableIdx
	 */
	public Integer getSelectedTableIdx() {
		return selectedTableIdx;
	}

	/**
	 * @param selectedTableIdx the selectedTableIdx to set
	 */
	public void setSelectedTableIdx(Integer selectedTableIdx) {
		this.selectedTableIdx = selectedTableIdx;
	}

	/**
	 * @return the projectPath
	 */
	public IPath getProjectPath() {
		return projectPath;
	}

	/**
	 * @param projectPath the projectPath to set
	 */
	public void setProjectPath(IPath projectPath) {
		this.projectPath = projectPath;
	}

}
