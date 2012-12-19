package org.codebone.plugin.popup.actions;

import org.codebone.plugin.wizard.CodeboneWizard;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class CodeboneAction implements IObjectActionDelegate {

	private Shell shell;
	
	private IPath projectPath;
	
	/**
	 * Constructor for Action1.
	 */
	public CodeboneAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		
		CodeboneWizard wizard = new CodeboneWizard();
		wizard.setProjectPath(projectPath);
		
		WizardDialog wizardDialog = new WizardDialog(shell, wizard);
		wizardDialog.setPageSize(200, 300);

	    if (wizardDialog.open() == Window.OK) {
	      System.out.println("Ok pressed");
	    } else {
	      System.out.println("Cancel pressed");
	       }
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		
		if (selection != null) {
	        if (selection instanceof IStructuredSelection) {
	            if (selection instanceof ITreeSelection) {
	                TreeSelection treeSelection = (TreeSelection) selection;
	                TreePath[] treePaths = treeSelection.getPaths();
	                TreePath treePath = treePaths[0];

	                Object lastSegmentObj = treePath.getLastSegment();
	                
	                if(lastSegmentObj instanceof IAdaptable) {
	                	IProject project = (IProject) ((IAdaptable) lastSegmentObj).getAdapter(IProject.class);
	                    if(project != null) {
	                    	projectPath = project.getLocation();
	                    }
	                }
	                
	                
	            }
	        }
		}
	}

}
