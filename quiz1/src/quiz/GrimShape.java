/**
 * Created on Oct 29, 2012
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package Grimpan03;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.Serializable;

/**
 * @author cskim
 *
 */
public class GrimShape implements Serializable, ImageObserver {

	private Shape grimShape = null;
	private float grimStrokeWidth = 1f;
	private Color grimStrokeColor = null;
	private Color grimFillColor = null;
	private boolean grimFill = false;
	private Image image = null;
	private String str = null;
	
	private int tx=0;
	private int ty=0;
	
	private int ix=0;
	private int iy=0;
	private int iw=0;
	private int ih=0;
	/**
	 * 
	 */
	public GrimShape(Shape grimShape){
		this(grimShape, 1f, null, null, false);
	}
	/**
	 * @param grimShape
	 * @param grimStrokeWidth
	 * @param grimFillColor
	 * @param grimFill
	 */
	public GrimShape(Shape grimShape, float grimStrokeWidth, Color grimStrokeColor, 
			Color grimFillColor, boolean grimFill) {
		super();
		this.grimShape = grimShape;
		this.grimStrokeWidth = grimStrokeWidth;
		this.grimStrokeColor = grimStrokeColor;
		this.grimFillColor = grimFillColor;
		this.grimFill = grimFill;
	}
	
	public GrimShape(Image image,int ix,int iy,int iw,int ih) {
		super();
		this.image = image;
		this.ix = ix;
		this.iy = iy;
		this.iw = iw;
		this.ih = ih;
	}
	
	public GrimShape(String str, int tx, int ty) {
		super();
		this.str = str;
		this.tx = tx;
		this.ty = ty;
	}
	
	public void draw(Graphics2D g2){
		g2.drawImage(this.image, ix, iy, iw, ih, this);
		if (this.str!=null)
		{
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("굴림",Font.PLAIN,20));
			g2.drawString(this.str, tx, ty);
		}
		
		if (isGrimFill()){
			g2.setColor(grimFillColor);
			g2.fill(grimShape);
		}
		
		if (grimStrokeColor!=null){
			g2.setStroke(new BasicStroke(this.grimStrokeWidth));
			g2.setColor(grimStrokeColor);
			g2.draw(grimShape);
			
		}
		
		//if (icheck){
		//	g2.drawImage(this.image, ix, iy, iw, ih, this);
		//	icheck = false;
		//}
		
	}
	public boolean contains(double px, double py){
		return this.grimShape.contains(px, py);
	}
	public void translate(float dx, float dy){
		AffineTransform tr = new AffineTransform();
		tr.setToTranslation(dx, dy);
		this.grimShape = tr.createTransformedShape(this.grimShape);
	}

	/**
	 * @return the grimShape
	 */
	public Shape getGrimShape() {
		return grimShape;
	}
	/**
	 * @param grimShape the grimShape to set
	 */
	public void setGrimShape(Shape grimShape) {
		this.grimShape = grimShape;
	}
	/**
	 * @return the grimStrokeWidth
	 */
	public float getGrimStrokeWidth() {
		return grimStrokeWidth;
	}
	/**
	 * @param grimStrokeWidth the grimStrokeWidth to set
	 */
	public void setGrimStrokeWidth(float grimStrokeWidth) {
		this.grimStrokeWidth = grimStrokeWidth;
	}
	/**
	 * @return the grimStrokeColor
	 */
	public Color getGrimStrokeColor() {
		return grimStrokeColor;
	}
	/**
	 * @param grimStrokeColor the grimStrokeColor to set
	 */
	public void setGrimStrokeColor(Color grimStrokeColor) {
		this.grimStrokeColor = grimStrokeColor;
	}
	/**
	 * @return the grimFillColor
	 */
	public Color getGrimFillColor() {
		return grimFillColor;
	}
	/**
	 * @param grimFillColor the grimFillColor to set
	 */
	public void setGrimFillColor(Color grimFillColor) {
		this.grimFillColor = grimFillColor;
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
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
