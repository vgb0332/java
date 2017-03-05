/**
 * Created on 2014. 11. 25.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.svgtreedraw;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import org.xml.sax.InputSource;

import oracle.xml.parser.v2.SAXParser;


public class SVGTreeDrawController {

	static final int MINPROGRESSVALUE = 2;

	private SVGTreeDrawModel model = null;
	private SVGTreeDrawView view = null;
	private Scanner inscan = null;

	public SVGTreeDrawController(SVGTreeDrawModel model){
		this.model = model;
		this.view = model.view;
	}

	public void buildXMLTree(){
		TreeBuildWorker tbWorker = new TreeBuildWorker();
		tbWorker.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				if ("progress".equals(e.getPropertyName())) {
					view.progressBar.setValue((Integer)e.getNewValue());
					if (view.progressBar.getValue()<MINPROGRESSVALUE) {
						view.progressBar.setIndeterminate(true);
					}
					else {
						view.progressBar.setStringPainted(true); 
						view.progressBar.setIndeterminate(false);
					}	
				}
			}
		});
		view.progressBar.setValue(0);
		view.progressBar.setStringPainted(false); 
		tbWorker.setProgressValue(0);

		tbWorker.execute();
		System.out.println("TreeBuildWorker Launched");

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
			JOptionPane.showMessageDialog(view, e);
		}

	}
	class TreeBuildWorker extends SwingWorker<Void, Void> {

		@Override
		protected Void doInBackground() throws Exception {
			view.progressBar.setIndeterminate(true);
			setProgressValue(0);
			SaxTreeBuildHandler saxTreeHandler = new SaxTreeBuildHandler(model); 

			try {             
				SAXParser saxParser = new SAXParser();
				saxParser.setContentHandler(saxTreeHandler);
				saxParser.parse(new InputSource(new FileInputStream(model.getSelectedFile())));
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(view, e);
			}

			return null;
		}
		/** Code executed after the background thread finishes */
		@Override
		protected void done() {
			view.jtreeView = new JTree(model.getSaxTreeModel());
			view.jtreeView.setFont(new Font("Consolas", Font.PLAIN, 14));
			view.jtreeView.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			view.jtreeView.addTreeSelectionListener(new TreeSelectionListener() {
				public void valueChanged(TreeSelectionEvent e) {
					DefaultMutableTreeNode node = 
							(DefaultMutableTreeNode)view.jtreeView.getLastSelectedPathComponent();

					/* if nothing is selected */ 
					if (node == null) return;

					/* retrieve the node that was selected */ 
					String nodeInfo = (String)node.getUserObject();
					
					if (nodeInfo.equals("path")){
						Enumeration<DefaultMutableTreeNode> chenum = node.children();
						StringBuilder sb = new StringBuilder();
						while (chenum.hasMoreElements()){
							nodeInfo = (String)chenum.nextElement().getUserObject();
							sb.append(nodeInfo);
							sb.append('\n');
						}
						view.jtaDataView.setText(sb.toString());
					}


				}
			});

			view.jspLeft.setViewportView(view.jtreeView);

			setProgress(100);

		}

		public void setProgressValue(int val){
			setProgress(val);
		}

	}

}
