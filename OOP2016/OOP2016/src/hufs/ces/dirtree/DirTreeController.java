/**
 * Created on 2014. 11. 25.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.dirtree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class DirTreeController {
	
	private DirTreeModel model = null;
	private DirTreeView view = null;
	private DefaultMutableTreeNode root = null;
	private Scanner inscan = null;
	
	public DirTreeController(DirTreeModel model){
		this.model = model;
		this.view = model.view;
	}

	public void buildDirTree(){
		
		root = new DefaultMutableTreeNode();
		traverseDir(root, model.getSelectedFile());
		
		model.setDirTree(new DefaultTreeModel(root));
		
	}
	private void traverseDir(DefaultMutableTreeNode node, File file){
		node.setUserObject(file.getName());
		//System.out.println("node="+file.getName());
		if (file.isDirectory()){
			File[] files = file.listFiles();
			for (File f:files){
				DefaultMutableTreeNode child = new DefaultMutableTreeNode();
				traverseDir(child, f);
				node.add(child);
			}
		}
	}
	public void readFile2View(){
		try {
			inscan = new Scanner(model.getSelectedFile());
			String inline = "";
			while (inscan.hasNext()){
				inline = inscan.nextLine();
				view.jtaDataView.append(inline+"\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
