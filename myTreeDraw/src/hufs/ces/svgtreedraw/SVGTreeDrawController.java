/**
 * Created on 2014. 11. 25.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.svgtreedraw;

import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;




public class SVGTreeDrawController {

	static final int MINPROGRESSVALUE = 2;

	//ADDED
	private SVGTreeDrawController thisClass = this;

	private SVGTreeDrawModel model = null;
	private SVGTreeDrawView view = null;
	private Scanner inscan = null;

	public static boolean drawState = false;

	//
	ParseSVGWorker parseSvgWorker = null;

	public SVGTreeDrawController(SVGTreeDrawModel model){
		this.model = model;
		this.view = model.view;
	}
	/*
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
		//NODE COUNT ADDED
		model.setPathNodeCount(0);
		try {
			inscan = new Scanner(model.getSelectedFile());
			String inline = "";
			while (inscan.hasNext()){
				inline = inscan.nextLine();
				view.jtaDataView.append(inline+"\n");
				if(inline.indexOf("path") > 0){
					model.incPathNodeCount();
				}
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(view, e);
		}
		System.out.println("path node count="+model.getPathNodeCount());
		inscan.close();
	}
	 */
	public void buildXMLTree(){
		if (view.drawPanel!=null){
			view.drawPanel.setVisible(false);
			view.jtaDataView.setVisible(true);
			view.jtaDataView.setText("");
			view.jspRight.setViewportView(view.jtaDataView);
			view.invalidate();
		}
		countPathNodeNDisplaySVGText();

		parseSvgWorker = new ParseSVGWorker();
		//*
		parseSvgWorker.addPropertyChangeListener(new PropertyChangeListener() {
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
		parseSvgWorker.setProgressValue(0);
		//*/
		parseSvgWorker.execute();
		//System.out.println("ParseSVGWorker Launched");
	}

	void countPathNodeNDisplaySVGText(){

		model.setPathNodeCount(0);
		try {
			inscan = new Scanner(model.getSelectedFile());
			String inline = "";
			while (inscan.hasNext()){
				inline = inscan.nextLine();
				//System.out.println(inline);
				view.jtaDataView.append(inline+"\n");
				if (inline.indexOf("path")>0){
					model.incPathNodeCount();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(view, e);
		}
		//System.out.println("path node count="+model.getPathNodeCount());

		inscan.close();

	}
	////////////////////////////
	public void drawAction(){
		view.jtaDataView.setVisible(false);

		view.drawPanel = new DrawPanel(view.gmodel);
		//System.out.println("Why?");
		view.drawPanel.setPreferredSize(new Dimension(view.gmodel.getPanWidth(), view.gmodel.getPanHeight()));
		view.setBounds(50, 50, view.gmodel.getPanWidth()+200, view.gmodel.getPanHeight());
		view.jspRight.setViewportView(view.drawPanel);
		view.invalidate();


		view.gmodel.shapeList = new ArrayList<GrimShape>();

		//Tree View Selection Listener ADDED BY JUNG
		view.jtreeView.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		view.jtreeView.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				if (drawState){
					DefaultMutableTreeNode node = 
							(DefaultMutableTreeNode)view.jtreeView.getLastSelectedPathComponent();

					/* if nothing is selected */ 
					if (node == null) return;

					/* retrieve the node that was selected */     
					String nodeInfo = (String)node.getUserObject();
					//System.out.println(model.attsMapList.toString());


					if (nodeInfo.equals("path")){
						//ADDED FOR CHECKING INDEX
						HashMap<String, String> attsMap = new HashMap<String, String>();
						Enumeration<DefaultMutableTreeNode> chenum = node.children();
						StringBuilder sb = new StringBuilder();
						while (chenum.hasMoreElements()){
							nodeInfo = (String)chenum.nextElement().getUserObject();
							//System.out.println(nodeInfo);
							String attname = nodeInfo.split(" = ")[0];
							String attvalue = nodeInfo.split(" = ")[1];
							attsMap.put(attname, attvalue);
							sb.append(nodeInfo);
							//System.out.println(attname+"="+attvalue);

							sb.append('\n');
						}
						int drawCount = 0;

						//System.out.println(attsMap.get("d"));

						ArrayList<GrimShape> gslist = SVG2GrimShapeTranslator.translateSVG2Shape(attsMap);

						if (gslist != null && gslist.size()!=0){
							view.gmodel.shapeList.addAll(gslist);
							//view.gmodel.shapeList.add(gslist.get(0));
							view.drawPanel.repaint();

						}
						System.out.println(attsMap.keySet());
					}

				}
			}
		});
	}
	/*
		SvgDrawWorker sdWorker = new SvgDrawWorker();
		sdWorker.addPropertyChangeListener(new PropertyChangeListener() {
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
		sdWorker.setProgressValue(0);

		//System.out.println("pathcount="+model.attsMapList.size());
		//for(int i = 0; i < model.attsMapList.size(); ++i){
		//	System.out.println(model.attsMapList.get(i));
		//}
		sdWorker.execute();
	 */


	public void clearAction(){
		view.jtaDataView.setVisible(false);

		view.drawPanel = new DrawPanel(view.gmodel);
		view.drawPanel.setPreferredSize(new Dimension(view.gmodel.getPanWidth(), view.gmodel.getPanHeight()));
		view.setBounds(50, 50, view.gmodel.getPanWidth()+200, view.gmodel.getPanHeight());
		view.jspRight.setViewportView(view.drawPanel);
		view.invalidate();

		/*
		 * 
		 */
		//view.gmodel.shapeList = new ArrayList<GrimShape>();
		view.gmodel.shapeList.clear();
		view.drawPanel.repaint();

		//Tree View Selection Listener ADDED BY JUNG



	}
	/*
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
	/*
		@Override
		protected void done() {
			System.out.println("Is this ever called?");
			view.jtreeView = new JTree(model.getSaxTreeModel());
			view.jtreeView.setFont(new Font("Consolas", Font.PLAIN, 14));
			view.jtreeView.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			view.jtreeView.addTreeSelectionListener(new TreeSelectionListener() {
				public void valueChanged(TreeSelectionEvent e) {
					DefaultMutableTreeNode node = 
							(DefaultMutableTreeNode)view.jtreeView.getLastSelectedPathComponent();

					/* if nothing is selected  
					if (node == null) return;*/

	/* retrieve the node that was selected */ /*
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
						System.out.println(sb);
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
	 */
	//ADDED INTERNEAL CLASSES
	class ParseSVGWorker extends SwingWorker<Void, Void> {

		@Override
		protected Void doInBackground() throws Exception {
			view.progressBar.setIndeterminate(true);
			setProgressValue(0);

			model.attsMapList = new ArrayList<HashMap<String, String>>();
			SaxSVGParseHandler saxTreeHandler = new SaxSVGParseHandler(thisClass, model); 

			try {
				SAXParserFactory saxf = SAXParserFactory.newInstance();
				//saxf.setFeature("http://xml.org/sax/features/validation", false);
				javax.xml.parsers.SAXParser saxParser = saxf.newSAXParser();
				saxParser.parse(new InputSource(new FileInputStream(model.getSelectedFile())), saxTreeHandler);
			}
			catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(view, e);
			}

			return null;
		}
		/** Code executed after the background thread finishes */
		@Override
		protected void done() {
			setProgress(100);
			view.progressBar.setIndeterminate(false);
			view.jtreeView = new JTree(model.getSaxTreeModel());
			view.jtreeView.setFont(new Font("Consolas", Font.PLAIN, 14));
			view.jspLeft.setViewportView(view.jtreeView);
		}

		public void setProgressValue(int val){
			setProgress(val);
		}

	}

	class SvgDrawWorker extends SwingWorker<Void, Void> {

		@Override
		protected Void doInBackground() throws Exception {
			view.progressBar.setIndeterminate(true);
			setProgressValue(0);

			int drawCount = 0;
			for (HashMap<String, String> map:model.attsMapList){
				System.out.println();
				//System.out.println(map.keySet());
				//System.out.println(map.values());
				ArrayList<GrimShape> gslist = SVG2GrimShapeTranslator.translateSVG2Shape(map);
				//System.out.println("shapelist size="+view.gmodel.shapeList.size());
				if (gslist != null && gslist.size()!=0){
					view.gmodel.shapeList.addAll(gslist);
					//view.gmodel.shapeList.add(gslist.get(0));
					view.drawPanel.repaint();
					Thread.sleep(100);
				}
				drawCount++;
				int percentProgress = (int)(100.0 * drawCount / model.getPathNodeCount());
				if (percentProgress % 5 == 0){
					setProgressValue(percentProgress);
					//System.out.println("progress="+percentProgress);
				}
				//System.out.println("drawcount="+drawCount);
			}
			return null;
		}
		/** Code executed after the background thread finishes */
		@Override
		protected void done() {
			setProgress(100);
			view.drawPanel.repaint();
		}

		public void setProgressValue(int val){
			setProgress(val);
		}
	}
}

