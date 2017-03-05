package hufs.ces.grimpan3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

public class DrawPanel extends JPanel implements MouseInputListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static final int MINPOLYDIST = 6;

	//POPUPMENU BY JUNG
	//FOR RIGHT CLICk
	//NEW POPUP MENU FOR RIGHT CLICK
	private JPopupMenu jPopupMenuEdit = null;
	private JMenuItem jpmiDelete = null;
	private JMenuItem jpmiAddShape = null;
	private JMenuItem jpmiMove = null;


	private GrimPanModel model = null;

	public DrawPanel(GrimPanModel model){
		this.model = model;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}



	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		for (GrimShape grimShape:model.shapeList){
			grimShape.draw(g2);
		}

		if (model.curDrawShape != null){
			GrimShape curGrimShape;
			if(model.curImage != null){ //if image is being Added
				//Point pi = model.getMousePosition();
				Point topleft = model.getClickedMousePosition();
				curGrimShape = new GrimShape(model.curDrawShape, model.curImage,
						topleft.x, 	topleft.y);
				model.curImage = null;
			}

			else{
				curGrimShape = new GrimShape(model.curDrawShape, 
						model.getShapeStroke().getLineWidth(), 
						model.getStrokeColor(),
						model.getFillColor(), model.isShapeFill());
			}
			curGrimShape.draw(g2);
		}

	}
	@Override
	//DELETE ADDED BY JUNG
	public void mouseClicked(MouseEvent ev) {
		Point p1 = ev.getPoint();
		model.setMousePosition(p1);
		//System.out.println("Pressed Mouse Point=("+p1.x+","+p1.y+")");

		if (SwingUtilities.isLeftMouseButton(ev)){
			model.setClickedMousePosition(p1);
			switch (model.getEditState()){
			case GrimPanModel.EDIT_DELETE:
				getSelectedShape();
				deleteShapeByClick();
				break;
			}
		}

		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	private JPopupMenu getJPopupMenuEdit() {
		if (jPopupMenuEdit == null) {
			jPopupMenuEdit = new JPopupMenu();
			jPopupMenuEdit.add(getJpmiMove());
			jPopupMenuEdit.addSeparator();
			jPopupMenuEdit.add(getJpmiDelete());
			jPopupMenuEdit.addSeparator();
			jPopupMenuEdit.add(getJpmiAddShape());

		}
		return jPopupMenuEdit;
	}
	private JMenuItem getJpmiMove(){
		if(jpmiMove == null){
			jpmiMove = new JMenuItem("MOVE");
			jpmiMove.setText("이동");
			jpmiMove.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					model.setEditState(GrimPanModel.EDIT_MOVE);
				}
			});

		}
		return jpmiMove;
	}

	private JMenuItem getJpmiDelete(){
		if(jpmiDelete == null){
			jpmiDelete = new JMenuItem("DELETE");
			jpmiDelete.setText("삭제");
			jpmiDelete.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					model.setEditState(GrimPanModel.EDIT_DELETE);
				}
			});
		}
		return jpmiDelete;
	}
	private JMenuItem getJpmiAddShape(){
		if(jpmiAddShape == null){
			jpmiAddShape = new JMenuItem("ADD SHAPE");
			jpmiAddShape.setText("도형 삽입");
			jpmiAddShape.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					Object[] possibleValues = { 
							"Line", "Pencil", "Polygon", "Regular", "Oval",
							"NEW1", "NEW2"
					};
					Object selectedValue = JOptionPane.showInputDialog(jpmiAddShape,
							"Choose one", "SHAPE",
							JOptionPane.INFORMATION_MESSAGE, null,
							possibleValues, possibleValues[0]);

					if(selectedValue != null){
						String chosen = (String)selectedValue;
						switch (chosen){
						case "Line":
							model.setEditState(GrimPanModel.SHAPE_LINE);
							break;
						case "Pencil":
							model.setEditState(GrimPanModel.SHAPE_PENCIL);
							break;
						case "Polygon":
							model.setEditState(GrimPanModel.SHAPE_POLYGON);
							break;
						case "Regular":
							model.setEditState(GrimPanModel.SHAPE_REGULAR);
							break;
						case "Oval":
							model.setEditState(GrimPanModel.SHAPE_OVAL);
							break;
						case "NEW1":
							model.setEditState(GrimPanModel.SHAPE_NEW1);
							break;
						case "NEW2":
							model.setEditState(GrimPanModel.SHAPE_NEW2);
							break;
						}
					}

				}
			});
		}
		return jpmiAddShape;
	}

	public void mousePressed(MouseEvent ev) {
		Point p1 = ev.getPoint();
		model.setMousePosition(p1);
		//System.out.println("Pressed Mouse Point=("+p1.x+","+p1.y+")");

		//ADDED FOR RIGHT CLICK POP-UP

		if(SwingUtilities.isRightMouseButton(ev)){
			model.setClickedMousePosition(p1);
			//System.out.println("Right Mouse Button Clicked");
			jPopupMenuEdit = getJPopupMenuEdit();
			jPopupMenuEdit.show(this, ev.getPoint().x, ev.getPoint().y);
			//System.out.println(ev.getPoint());
			repaint();
		}


		if (SwingUtilities.isLeftMouseButton(ev)){
			model.setClickedMousePosition(p1);
			switch (model.getEditState()){
			case GrimPanModel.SHAPE_LINE:
				genLineShape();
				break;
			case GrimPanModel.SHAPE_PENCIL:
				model.setLastMousePosition(model.getMousePosition());				
				model.curDrawShape = new Path2D.Double();
				((Path2D)model.curDrawShape).moveTo((double)p1.x, (double)p1.y);
				break;
			case GrimPanModel.SHAPE_POLYGON:
				break;
			case GrimPanModel.SHAPE_REGULAR:
				genCenteredPolygon();
				break;
			case GrimPanModel.SHAPE_OVAL:
				genEllipse2D();
				break;

				//NEW SHAPES ADDED BY JUNG
			case GrimPanModel.SHAPE_NEW1:
				genStar();
				break;
			case GrimPanModel.SHAPE_NEW2:
				genRoundRectangle();
				break;
				//////////////////////////	
			case GrimPanModel.EDIT_MOVE:
				getSelectedShape();
				break;
			case GrimPanModel.ADDITIONAL_IMAGE:
				//System.out.println("Image Adding");
				genRecImg();
				if (model.curDrawShape != null){
					//System.out.println("Am i in?");
					//Point pi = model.getMousePosition();
					Point topleft = model.getClickedMousePosition();
					model.shapeList.add(new GrimShape(model.curDrawShape, model.curImage,
							topleft.x, 	topleft.y));
					model.curDrawShape = null;
					//model.curImage = null;
				}
				break;

			}

		}
		repaint();
	}

	public void mouseReleased(MouseEvent ev) {
		Point p1 = ev.getPoint();
		model.setMousePosition(p1);
		//System.out.println("Released Mouse Point=("+p1.x+","+p1.y+")");

		switch (model.getEditState()){
		case GrimPanModel.SHAPE_LINE:
			genLineShape();
			if (model.curDrawShape != null){
				model.shapeList.add(new GrimShape(model.curDrawShape, 
						model.getShapeStroke().getLineWidth(), 
						model.getStrokeColor(),
						model.getFillColor(), model.isShapeFill()));
				model.curDrawShape = null;
			}
			break;
		case GrimPanModel.SHAPE_PENCIL:
			((Path2D)model.curDrawShape).lineTo((double)p1.x, (double)p1.y);
			if (model.curDrawShape != null){
				model.shapeList.add(new GrimShape(model.curDrawShape, 
						model.getShapeStroke().getLineWidth(), 
						model.getStrokeColor(),
						model.getFillColor(), model.isShapeFill()));
				model.curDrawShape = null;
			}
			break;
		case GrimPanModel.SHAPE_POLYGON:
			model.polygonPoints.add(p1);
			model.curDrawShape = buildPath2DFromPoints(model.polygonPoints);
			if (ev.isShiftDown()) {
				((Path2D)(model.curDrawShape)).closePath();
				model.polygonPoints.clear();
				model.shapeList.add(new GrimShape(model.curDrawShape, 
						model.getShapeStroke().getLineWidth(), 
						model.getStrokeColor(),
						model.getFillColor(), model.isShapeFill()));
			}
			break;
		case GrimPanModel.SHAPE_REGULAR:
			genCenteredPolygon();
			if (model.curDrawShape != null){
				model.shapeList.add(new GrimShape(model.curDrawShape, 
						model.getShapeStroke().getLineWidth(), model.getStrokeColor(),
						model.getFillColor(), model.isShapeFill()));
				model.curDrawShape = null;
			}
			break;
		case GrimPanModel.SHAPE_OVAL:
			genEllipse2D();
			if (model.curDrawShape != null){
				model.shapeList.add(new GrimShape(model.curDrawShape, 
						model.getShapeStroke().getLineWidth(), model.getStrokeColor(),
						model.getFillColor(), model.isShapeFill()));
				model.curDrawShape = null;
			}
			break;

			//NEW SHAPES ADDED BY JUNG
		case GrimPanModel.SHAPE_NEW1:
			genStar();
			if (model.curDrawShape != null){
				//System.out.println("NULL?");
				model.shapeList.add(new GrimShape(model.curDrawShape, 
						model.getShapeStroke().getLineWidth(), model.getStrokeColor(),
						model.getFillColor(), model.isShapeFill()));
				//System.out.println(model.shapeList.size());
				model.curDrawShape = null;
			}
			break;
		case GrimPanModel.SHAPE_NEW2:
			genRoundRectangle();
			if (model.curDrawShape != null){
				model.shapeList.add(new GrimShape(model.curDrawShape, 
						model.getShapeStroke().getLineWidth(), model.getStrokeColor(),
						model.getFillColor(), model.isShapeFill()));
				model.curDrawShape = null;
			}
			break;
			//////////////////////////	
		case GrimPanModel.ADDITIONAL_IMAGE:
			break;

		case GrimPanModel.ADDITIONAL_TEXT:
			genTextImg();
			if (model.curDrawShape != null){
				//System.out.println("Am i in?");
				//Point pi = model.getMousePosition();
				Point topleft = model.getClickedMousePosition();
				model.shapeList.add(new GrimShape(model.curDrawShape, model.textMessage, model.textFont,
						topleft.x, 	topleft.y));
				model.curDrawShape = null;
			}
			break;		


		case GrimPanModel.EDIT_MOVE:
			endShapeMove();
			break;

		}

		repaint();

	}

	public void mouseDragged(MouseEvent ev) {
		Point p1 = ev.getPoint();
		model.setLastMousePosition(model.getMousePosition());
		model.setMousePosition(p1);
		//System.out.println("Dragged Mouse Point=("+p1.x+","+p1.y+")");

		switch (model.getEditState()){
		case GrimPanModel.SHAPE_LINE:
			genLineShape();
			break;
		case GrimPanModel.SHAPE_PENCIL:
			((Path2D)model.curDrawShape).lineTo((double)p1.x, (double)p1.y);
			break;
		case GrimPanModel.SHAPE_POLYGON:
			break;
		case GrimPanModel.SHAPE_REGULAR:
			genCenteredPolygon();
			break;
		case GrimPanModel.SHAPE_OVAL:
			genEllipse2D();
			break;

			//NEW SHAPES ADDED BY JUNG
		case GrimPanModel.SHAPE_NEW1:
			genStar();
			break;
		case GrimPanModel.SHAPE_NEW2:
			genRoundRectangle();
			break;
			//////////////////////////	
		case GrimPanModel.ADDITIONAL_IMAGE:
			break;

		case GrimPanModel.EDIT_MOVE:
			moveShapeByMouse();
			break;
		}
		repaint();

	}
	private void genLineShape() {
		Point p1 = model.getClickedMousePosition();
		Point p2 = model.getMousePosition();
		model.curDrawShape = new Line2D.Double(p1, p2);

	}
	public static Path2D buildPath2DFromPoints(ArrayList<Point> points){
		Path2D result = new Path2D.Double();
		if (points != null && points.size() > 0) {
			Point p0 = points.get(0);
			result.moveTo((double)(p0.x), (double)(p0.y));
			for (int i=1; i<points.size(); ++i){
				p0 = points.get(i);
				result.lineTo((double)(p0.x), (double)(p0.y));
			}
		}

		return result;
	}
	private void genCenteredPolygon(){
		Point pi = model.getMousePosition();
		Point center = model.getClickedMousePosition();
		if (pi.distance(new Point2D.Double(center.x, center.y)) <= MINPOLYDIST)
			return;

		getPolygonWithCenterNVertex(center, new Point2D.Double(pi.x, pi.y));
	}
	private void getPolygonWithCenterNVertex(Point center, Point2D pi){
		AffineTransform rotNTimes = new AffineTransform();
		rotNTimes.setToRotation(2*Math.PI/model.getNPolygon()); //360/n degree

		Point2D[] polyPoints = new Point2D[model.getNPolygon()];
		polyPoints[0] = new Point2D.Double(pi.getX()-center.x, pi.getY()-center.y); 
		for (int i=1; i<polyPoints.length; ++i){
			polyPoints[i] = (Point2D)polyPoints[i-1].clone();
			rotNTimes.transform(polyPoints[i], polyPoints[i]);
		}

		polyPoints[0] = new Point2D.Double(pi.getX(), pi.getY()); 
		for (int i=1; i<polyPoints.length; ++i){
			polyPoints[i].setLocation(
					polyPoints[i].getX()+center.x, 
					polyPoints[i].getY()+center.y);
		}
		Path2D polygonPath = new Path2D.Double();
		polygonPath.moveTo(polyPoints[0].getX(), polyPoints[0].getY());
		//System.out.println("x= "+polyPoints[0].getX()+" y= "+polyPoints[0].getY());
		for (int i=1; i<polyPoints.length; ++i){
			polygonPath.lineTo(polyPoints[i].getX(), polyPoints[i].getY());
			//System.out.println("x= "+polyPoints[i].getX()+" y= "+polyPoints[i].getY());
		}
		polygonPath.closePath();

		model.curDrawShape = polygonPath;

	}
	////////////ADDED BY JUNG/////////////////////////////////////////////
	private void genRecImg(){

		Point pi = model.getMousePosition();
		Point topleft = model.getClickedMousePosition();
		//if (pi.distance(new Point2D.Double(topleft.x, topleft.y)) <= MINPOLYDIST){
		//	System.out.println("Here@3232");
		//	return;}

		Rectangle2D rec = new Rectangle2D.Double(topleft.x, topleft.y, model.getImageWidth(), model.getImageHeight());
		model.curDrawShape = rec;

		//System.out.println(model.curDrawShape+"is created");

	}

	private void genTextImg(){
		Point pi = model.getMousePosition();
		Point topleft = model.getClickedMousePosition();

		Rectangle2D rec = new Rectangle2D.Double(topleft.x, topleft.y-model.textFont.getSize()/2, 
				model.textFont.getSize()/2*model.textMessage.length(), model.textFont.getSize());

		model.curDrawShape = rec;
	}
	private void genStar(){
		Point pi = model.getMousePosition();
		Point center = model.getClickedMousePosition();
		if (pi.distance(new Point2D.Double(center.x, center.y)) <= MINPOLYDIST)
			return;

		genStarWithCenter(center, new Point2D.Double(pi.x, pi.y));
	}
	private void genStarWithCenter(Point center, Point2D pi){
		AffineTransform rotNTimes = new AffineTransform();		
		rotNTimes.setToRotation(2*Math.PI/model.getNStar()); //360/n degree

		Point2D[] starPoints = new Point2D[model.getNStar()];
		starPoints[0] = new Point2D.Double(pi.getX()-center.x, pi.getY()-center.y); 
		for (int i=1; i<starPoints.length; ++i){
			starPoints[i] = (Point2D)starPoints[i-1].clone();
			rotNTimes.transform(starPoints[i], starPoints[i]);
		}

		starPoints[0] = new Point2D.Double(pi.getX(), pi.getY()); 
		for (int i=1; i<starPoints.length; ++i){
			starPoints[i].setLocation(
					starPoints[i].getX()+center.x, 
					starPoints[i].getY()+center.y);
		}
		Path2D starPath = new Path2D.Double();

		starPath.moveTo(starPoints[0].getX(), starPoints[0].getY());
		starPath.lineTo(starPoints[2].getX(), starPoints[2].getY());
		starPath.lineTo(starPoints[4].getX(), starPoints[4].getY());
		starPath.lineTo(starPoints[0].getX(), starPoints[0].getY());

		starPath.moveTo(starPoints[1].getX(), starPoints[1].getY());
		starPath.lineTo(starPoints[1].getX(), starPoints[1].getY());
		starPath.lineTo(starPoints[3].getX(), starPoints[3].getY());
		starPath.lineTo(starPoints[5].getX(), starPoints[5].getY());

		starPath.closePath();

		model.curDrawShape = starPath;

	}
	private void genRoundRectangle(){
		Point pi = model.getMousePosition();
		Point topleft = model.getClickedMousePosition();
		if (pi.distance(new Point2D.Double(topleft.x, topleft.y)) <= MINPOLYDIST)
			return;
		RoundRectangle2D roundRec = new RoundRectangle2D.Double(
				topleft.x, topleft.y,
				pi.x-topleft.x, pi.y-topleft.y,
				100, 100);
		model.curDrawShape = roundRec;
	}

	//////////////////////////////////////////////////////
	private void genEllipse2D(){
		Point pi = model.getMousePosition();
		Point topleft = model.getClickedMousePosition();
		if (pi.distance(new Point2D.Double(topleft.x, topleft.y)) <= MINPOLYDIST)
			return;
		Ellipse2D oval = new Ellipse2D.Double(
				topleft.x, topleft.y,
				pi.x-topleft.x, pi.y-topleft.y);
		model.curDrawShape = oval;
	}
	private void getSelectedShape(){
		//System.out.println("Selected getSelected");
		int selIndex = -1;
		GrimShape shape = null;
		for (int i=model.shapeList.size()-1; i >= 0; --i){
			shape = model.shapeList.get(i);
			if (shape.contains(model.getMousePosition().getX(), model.getMousePosition().getY())){
				selIndex = i;
				break;
			}
		}
		//System.out.println("Ind: "+selIndex);
		if (selIndex != -1){
			model.setLastMousePosition(model.getClickedMousePosition());
			Color scolor = shape.getGrimStrokeColor();
			Color fcolor = shape.getGrimFillColor();
			if (scolor!=null){
				shape.setGrimStrokeColor(new Color (scolor.getRed(), scolor.getGreen(), scolor.getBlue(), 127));
			}
			if (fcolor!=null){
				shape.setGrimFillColor(new Color (fcolor.getRed(), fcolor.getGreen(), fcolor.getBlue(), 127));
			}
		}
		model.setSelectedShape(selIndex);
	}
	private void moveShapeByMouse(){
		int selIndex = model.getSelectedShape();
		GrimShape shape = null;
		if (selIndex != -1){
			shape = model.shapeList.get(selIndex);
			shape.translate(
					(float)(model.getMousePosition().x-model.getLastMousePosition().x), 
					(float)(model.getMousePosition().y-model.getLastMousePosition().y));
		}
	}
	//FUNCTION ADDED FOR DELETE BY JUNG
	private void deleteShapeByClick(){
		int selIndex = model.getSelectedShape();
		if(selIndex == -1) return;
		//System.out.println("Curr Ind: " + selIndex);
		model.shapeList.remove(selIndex);
	}
	private void endShapeMove(){
		int selIndex = model.getSelectedShape();
		GrimShape shape = null;
		if (selIndex != -1){
			shape = model.shapeList.get(selIndex);
			Color scolor = shape.getGrimStrokeColor();
			Color fcolor = shape.getGrimFillColor();
			if (scolor!=null){
				shape.setGrimStrokeColor(new Color (scolor.getRed(), scolor.getGreen(), scolor.getBlue()));
			}
			if (fcolor!=null){
				shape.setGrimFillColor(new Color (fcolor.getRed(), fcolor.getGreen(), fcolor.getBlue()));
			}
		}
	}

}
