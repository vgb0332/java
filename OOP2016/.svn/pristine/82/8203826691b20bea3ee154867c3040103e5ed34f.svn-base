/**
 * Created on 2015. 3. 8.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package tile;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class RectEditDrawPanel 	extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RectEditDrawPanel thisView = this;
	private TileModel model = null;
	DrawPanelMouseAdapter mouseAdapter = null;

	private int baseWidth = 400; // in milli
	private int baseHeight = 300; // in milli

	public JPopupMenu jPopupMenuEdit = null;
	private JMenuItem jpmiZoomIn = null;
	private JMenuItem jpmiZoomOut = null;
	private JMenuItem jpmiMove = null;
	private JMenuItem jpmiResize = null;
	private JMenuItem jpmiDelete = null;

	public RectEditDrawPanel(int w, int h){
		this.model = TileModel.getInstance();

		this.baseWidth = w;
		this.baseHeight = h;
		model.setBaseWidth(getPixelWidth());
		model.setBaseHeight(getPixelHeight());

		this.setPreferredSize(new Dimension(model.getBaseWidth(), model.getBaseHeight()));
		this.setSize(new Dimension(model.getBaseWidth(), model.getBaseHeight()));

		this.mouseAdapter = new DrawPanelMouseAdapter(model, this);
		this.addMouseListener(mouseAdapter);
		this.addMouseMotionListener(mouseAdapter);

		getJPopupMenuEdit();

	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(TileModel.BASE_COLOR);
		g2.fillRect(0, 0, (int)(model.getMilli2Pixel(baseWidth)+1), (int)(model.getMilli2Pixel(baseHeight)+1));

		for (int i=0; i<model.tileRectList.size(); ++i){
			RectShape tile = model.tileRectList.get(i);
			if (i==model.getSelIndex()){
				RectShape gsclone = tile.clone();
				gsclone.setGrimFillColor(TileModel.SELECT_COLOR);
				gsclone.draw(g2);
			}
			else {
				tile.draw(g2);
			}
		}
		if (model.selMarkList!= null){
			for (RectShape mark:model.selMarkList){
				mark.draw(g2);
			}
		}
		if (model.curDrawShape != null){
			model.curDrawShape.draw(g2);
		}
	}
	public int getBaseWidth() {
		return baseWidth;
	}
	public void setBaseWidth(int baseWidth) {
		this.baseWidth = baseWidth;
	}
	public int getBaseHeight() {
		return baseHeight;
	}
	public void setBaseHeight(int baseHeight) {
		this.baseHeight = baseHeight;
	}
	public int getPixelWidth(){
		return (int)model.getMilli2Pixel(baseWidth);
	}
	public int getPixelHeight(){
		return (int)model.getMilli2Pixel(baseHeight);
	}
	private JPopupMenu getJPopupMenuEdit() {
		if (jPopupMenuEdit == null) {
			jPopupMenuEdit = new JPopupMenu();
			jPopupMenuEdit.add(getJpmiZoomIn());
			jPopupMenuEdit.add(getJpmiZoomOut());
			jPopupMenuEdit.addSeparator();
			jPopupMenuEdit.add(getJpmiMove());
			jPopupMenuEdit.add(getJpmiResize());
			jPopupMenuEdit.add(getJpmiDelete());
		}
		return jPopupMenuEdit;
	}
	private JMenuItem getJpmiZoomIn() {
		if (jpmiZoomIn == null) {
			jpmiZoomIn = new JMenuItem("Zoom In",KeyEvent.VK_Z);
			jpmiZoomIn.setText("크게 ");
			jpmiZoomIn.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					viewZoomIn();
				}
			});
			KeyStroke ctrlPlusKeyStroke = 
					KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, ActionEvent.CTRL_MASK);
			jpmiZoomIn.setAccelerator(ctrlPlusKeyStroke);
		}
		return jpmiZoomIn;
	}

	/**
	 * This method initializes jpmiZoomOut	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJpmiZoomOut() {
		if (jpmiZoomOut == null) {
			jpmiZoomOut = new JMenuItem("Zoom Out",KeyEvent.VK_M);
			jpmiZoomOut.setText("작게 ");
			jpmiZoomOut.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					viewZoomOut();
				}
			});
			KeyStroke ctrlMinusKeyStroke = 
					KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.CTRL_MASK);
			jpmiZoomOut.setAccelerator(ctrlMinusKeyStroke);
		}
		return jpmiZoomOut;
	}
	public void viewZoomOut(){
		if (model.scaleIndex < model.getScaleMap().length-1){
			++model.scaleIndex;
			redrawFrame();
			repaint();
		}

	}
	public void viewZoomIn(){
		if (model.scaleIndex > 0){
			--model.scaleIndex;
			redrawFrame();
			repaint();
		}
	}
	private void redrawFrame(){
		model.setBaseWidth(getPixelWidth());
		model.setBaseHeight(getPixelHeight());
		this.setPreferredSize(new Dimension(model.getBaseWidth(), model.getBaseHeight()));
		this.setSize(new Dimension(model.getBaseWidth(), model.getBaseHeight()));

		model.tileFrame.getJspViewArea()
		.setCorner(JScrollPane.UPPER_LEFT_CORNER, new LCorner(model.getScaleRatio()));

		model.tileFrame.validate();
		model.tileFrame.getJspViewArea().revalidate();
	}
	private JMenuItem getJpmiMove() {
		if (jpmiMove == null) {
			jpmiMove = new JMenuItem();
			jpmiMove.setText("이동");
			jpmiMove.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					model.setEditState(TileModel.EDIT_MOVE);
					model.selMarkList = null;
					model.getSelectedShape();
					thisView.repaint();
				}
			});
		}
		return jpmiMove;
	}

	private JMenuItem getJpmiResize() {
		if (jpmiResize == null) {
			jpmiResize = new JMenuItem();
			jpmiResize.setText("크기조정");
			jpmiResize.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					model.setEditState(TileModel.EDIT_RESIZE);
					model.getSelectedShape();
					thisView.mouseAdapter.setMarkOfSelectedRec();
					thisView.repaint();
				}
			});
		}
		return jpmiResize;
	}

	private JMenuItem getJpmiDelete() {
		if (jpmiDelete == null) {
			jpmiDelete = new JMenuItem();
			jpmiDelete.setText("삭제");
			jpmiDelete.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					model.getSelectedShape();
					thisView.repaint();
					
					int delCheck = JOptionPane.showConfirmDialog(thisView, "선택한 타일을 삭제하겠습니까?", 
							"Delete Tile", JOptionPane.OK_CANCEL_OPTION);
					if (delCheck == JOptionPane.OK_OPTION){
						// delete tile action
						model.tileRectList.remove(model.getSelIndex());
						//System.out.println("delete tile");
						model.setSelIndex(-1);
						thisView.repaint();
					}
				}
			});
		}
		return jpmiDelete;
	}


}
