/**
 * Created on 2014. 11. 25.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.svggrim;

import java.awt.Dimension;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;


public class SvgWorkerController {
	
	static final int MINPROGRESSVALUE = 2;
	
	private SvgWorkerController thisClass = this;
	private SvgWorkerModel model = null;
	private SvgWorkerView view = null;
	private Scanner inscan = null;
		
	ParseSVGWorker parseSvgWorker = null;
	
	public SvgWorkerController(SvgWorkerModel model){
		this.model = model;
		this.view = model.view;
	}

	public void processSVGFile(){
		
		countPathNode();
		
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
		if (view.drawPanel!=null)
			view.drawPanel.setVisible(false);
		view.jtaDataView.setVisible(true);
		view.jtaDataView.setText("");
		view.invalidate();
		
		parseSvgWorker.execute();
		System.out.println("ParseSVGWorker Launched");
		
	}
	public void loadAction(){
		view.jtaDataView.setVisible(false);
		
		view.drawPanel = new DrawPanel(view.gmodel);
		view.drawPanel.setPreferredSize(new Dimension(view.gmodel.getPanWidth(), view.gmodel.getPanHeight()));
		view.setBounds(50, 50, view.gmodel.getPanWidth()+200, view.gmodel.getPanHeight());
		view.jspRight.setViewportView(view.drawPanel);
		view.invalidate();
		
		view.gmodel.shapeList = new ArrayList<GrimShape>();
		
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
				
		System.out.println("pathcount="+model.attsMapList.size());
		
		sdWorker.execute();
		
	}
	void countPathNode(){
		
		model.setPathNodeCount(0);
		try {
			inscan = new Scanner(model.getSelectedFile());
			String inline = "";
			while (inscan.hasNext()){
				inline = inscan.nextLine();
				if (inline.indexOf("path")>0)
					model.incPathNodeCount();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(view, e);
		}
		System.out.println("path node count="+model.getPathNodeCount());
		inscan.close();
		
	}
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
				SAXParser saxParser = saxf.newSAXParser();
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
			view.jtreeView = new JTree(model.getSaxTree());
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
				
				ArrayList<GrimShape> gslist = SVG2GrimShapeTranslator.translateSVG2Shape(map);
				//System.out.println("shapelist size="+view.gmodel.shapeList.size());
				if (gslist != null && gslist.size()!=0){
					view.gmodel.shapeList.addAll(gslist);
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
