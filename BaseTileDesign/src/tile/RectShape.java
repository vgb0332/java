/**
 * Created on Oct 29, 2012
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package tile;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * @author cskim
 *
 */
public class RectShape implements Serializable {

	protected TileModel model = TileModel.getInstance();;
	
	protected Rectangle2D rect2DShape = null;
	protected float grimStrokeWidth = 2f;
	protected Color grimStrokeColor = Color.black;
	protected Color grimFillColor = null;
	protected boolean grimFill = false;
	/**
	 * 
	 */
	public RectShape(Rectangle2D grimShape){
		this(grimShape, TileModel.shapeStroke, TileModel.strokeColor, TileModel.fillColor, true);
	}
	/**
	 * @param grimShape
	 * @param grimStrokeWidth
	 * @param grimFillColor
	 * @param grimFill
	 */
	public RectShape(Rectangle2D  grimShape, Stroke grimStroke, Color grimStrokeColor, 
			Color grimFillColor, boolean grimFill) {
		super();
		this.rect2DShape = grimShape;
		this.grimStrokeWidth = ((BasicStroke)grimStroke).getLineWidth();
		this.grimStrokeColor = grimStrokeColor;
		this.grimFillColor = grimFillColor;
		this.grimFill = grimFill;
	}
	public RectShape(Rectangle2D grimShape, float grimStrokeWidth, Color grimStrokeColor, 
			Color grimFillColor, boolean grimFill) {
		super();
		this.rect2DShape = grimShape;
		this.grimStrokeWidth = grimStrokeWidth;
		this.grimStrokeColor = grimStrokeColor;
		this.grimFillColor = grimFillColor;
		this.grimFill = grimFill;
	}
	public RectShape clone(){
		return new RectShape(rect2DShape, grimStrokeWidth, grimStrokeColor, grimFillColor, grimFill);
	}

	/**
	 * @return the grimShape
	 */
	public Rectangle2D getRect2D() {
		return rect2DShape;
	}
	/**
	 * @param grimShape the grimShape to set
	 */
	public void setRect2D(Rectangle2D grimShape) {
		this.rect2DShape = grimShape;
	}
	/**
	 * @return the grimStrokeWidth
	 */
	public Stroke getGrimStroke() {
		return new BasicStroke(this.grimStrokeWidth);
	}
	/**
	 * @param grimStrokeWidth the grimStrokeWidth to set
	 */
	public void setGrimStrokeWidth(Stroke grimStroke) {
		this.grimStrokeWidth = ((BasicStroke)grimStroke).getLineWidth();
	}
	/**
	 * @return the grimFill
	 */
	public boolean isGrimFill() {
		return grimFill;
	}
	/**
	 * @param grimFill the grimFill to set
	 */
	public void setGrimFill(boolean grimFill) {
		this.grimFill = grimFill;
	}
	public void draw(Graphics2D g2){
		double rectX = model.getMilli2Pixel(rect2DShape.getX());
		double rectY = model.getMilli2Pixel(rect2DShape.getY());
		double rectW = model.getMilli2Pixel(rect2DShape.getWidth());
		double rectH = model.getMilli2Pixel(rect2DShape.getHeight());
				
		Rectangle2D scaledRect = new Rectangle2D.Double(rectX, rectY, rectW, rectH);
				
		if (grimStrokeColor!=null){
			g2.setStroke(new BasicStroke(2f));
			g2.setColor(grimStrokeColor);
			g2.draw(scaledRect);
		}

		if (isGrimFill()){
			g2.setColor(grimFillColor);
			g2.fill(scaledRect);
		}

	}
	public boolean contains(double px, double py){
		double rectX = model.getMilli2Pixel(rect2DShape.getX());
		double rectY = model.getMilli2Pixel(rect2DShape.getY());
		double rectW = model.getMilli2Pixel(rect2DShape.getWidth());
		double rectH = model.getMilli2Pixel(rect2DShape.getHeight());
				
		Rectangle2D scaledRect = new Rectangle2D.Double(rectX, rectY, rectW, rectH);
		return scaledRect.contains(px, py);
	}
	public void translate(double dx, double dy){
		double x = rect2DShape.getX();
		double y = rect2DShape.getY();
		double w = rect2DShape.getWidth();
		double h = rect2DShape.getHeight();
		rect2DShape.setRect(x+dx, y+dy, w, h);
	}
	public void setLoc(double x, double y){
		double w = rect2DShape.getWidth();
		double h = rect2DShape.getHeight();
		rect2DShape.setRect(x, y, w, h);
	}
	boolean intersects(RectShape tile){
		return rect2DShape.intersects(tile.getRect2D());
	}
	public Color getGrimStrokeColor() {
		return grimStrokeColor;
	}
	public void setGrimStrokeColor(Color grimStrokeColor) {
		this.grimStrokeColor = grimStrokeColor;
	}
	public Color getGrimFillColor() {
		return grimFillColor;
	}
	public void setGrimFillColor(Color grimFillColor) {
		this.grimFillColor = grimFillColor;
	}
	public float getGrimStrokeWidth() {
		return grimStrokeWidth;
	}
	public void setGrimStrokeWidth(float grimStrokeWidth) {
		this.grimStrokeWidth = grimStrokeWidth;
	}

}
