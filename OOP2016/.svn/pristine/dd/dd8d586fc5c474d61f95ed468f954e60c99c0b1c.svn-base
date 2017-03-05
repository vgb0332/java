/**
 * Created on 2014. 11. 25.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.svggrim;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class SvgWorkerModel {

	public SvgWorkerView view = null;
	
	private File selectedFile = null;
	
	private TreeModel saxTree = null;
	
	private int pathNodeCount = 0;
	
	private int svgWidth = 400;
	private int svgHeight = 400;
	
	ArrayList<HashMap<String, String>> attsMapList = null;
	
	public SvgWorkerModel(SvgWorkerView view){
		this.view = view;
	}

	public File getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(File selectedFile) {
		this.selectedFile = selectedFile;
	}

	public TreeModel getSaxTree() {
		return saxTree;
	}

	public void setSaxTree(TreeModel xmlTree) {
		this.saxTree = xmlTree;
	}

	public int getPathNodeCount() {
		return pathNodeCount;
	}

	public void setPathNodeCount(int pathNodeCount) {
		this.pathNodeCount = pathNodeCount;
	}
	public int incPathNodeCount(){
		return ++pathNodeCount;
	}

	public int getSvgWidth() {
		return svgWidth;
	}

	public void setSvgWidth(int svgWidth) {
		this.svgWidth = svgWidth;
		view.gmodel.setPanWidth(svgWidth);
	}

	public int getSvgHeight() {
		return svgHeight;
	}

	public void setSvgHeight(int svgHeight) {
		this.svgHeight = svgHeight;
		view.gmodel.setPanHeight(svgHeight);
	}
}
