/**
 * Created on 2014. 11. 25.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.dom2tree;

import java.awt.Dimension;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

public class Dom2TreeController {
	
	private Dom2TreeModel model = null;
	private Dom2TreeView view = null;
	private DefaultMutableTreeNode root = null;
	private Scanner inscan = null;
	
	private Document doc = null;
	private DocumentBuilder builder = null;
	
	public Dom2TreeController(Dom2TreeModel model){
		this.model = model;
		this.view = model.view;
	}

	public void buildXMLTree(){
		if (builder == null)
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try
		{
			doc = builder.parse(model.getSelectedFile());

		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(view, e);
		}
		
		model.setDomTreeModel(new DOMTreeModel(doc));
		view.jtreeView = new JTree(model.getDomTreeModel());
		view.jtreeView.setCellRenderer(new DOMTreeCellRenderer());
		view.jspLeft.setViewportView(view.jtreeView);
		
	}
	public void readFile2View(){
		view.jtaDataView.setText("");
		try {
			inscan = new Scanner(model.getSelectedFile());
			String inline = "";
			int lineCount = 0;
			while (inscan.hasNext()){
				//lineCount++;
				lineCount = view.jtaDataView.getLineCount();
				inline = inscan.nextLine();
				view.jtaDataView.append(inline+"\n");
				//System.out.println(lineCount+":"+inline);
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inscan.close();
		//view.jspRight.setViewportView(view.jtaDataView);
		
	}
}
