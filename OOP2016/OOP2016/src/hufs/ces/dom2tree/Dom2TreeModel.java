/**
 * Created on 2014. 11. 25.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.dom2tree;

import java.io.File;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class Dom2TreeModel {

	public Dom2TreeView view = null;
	
	private File selectedFile = null;
	
	private TreeModel domTreeModel = null;	
	
	public Dom2TreeModel(Dom2TreeView view){
		this.view = view;
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}

	public TreeModel getDomTreeModel() {
		return domTreeModel;
	}

	public void setDomTreeModel(TreeModel dirTree) {
		this.domTreeModel = dirTree;
	}
}
