/**
 * Created on Oct 29, 2012
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.grimpan3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * @author cskim
 *
 */
public class GrimShape implements Serializable {

	private Shape grimShape = null;
	private float grimStrokeWidth = 1f;
	private Color grimStrokeColor = null;
	private Color grimFillColor = null;
	private boolean grimFill = false;
	
	//ADDED FOR IMAGE
	//private Image Image = null;
	private ImageIcon grimImage = null;
	private int grimImageX = 0;
	private int grimImageY = 0;
	private String grimText = null;
	private Font grimFont = null;
	//private int grimImageH = 0;
	//private int grimImageW = 0;
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
	
	// CONSTRUCTOR OVERWRITTEN FOR IMAGE AND TEXT BY JUNG
	public GrimShape(Shape grimShape, ImageIcon grimImage, int x, int y){
		super();
		this.grimShape = grimShape;
		this.grimImage = grimImage;
		this.grimImageX = x;
		this.grimImageY = y;
		//this.grimImageW = w;
		//this.grimImageH = h;
	}
	
	public GrimShape(Shape grimShape, String text, Font font, int x, int y){
		super();
		this.grimShape = grimShape;
		this.grimText = text;
		this.grimFont = font;
		this.grimImageX = x;
		this.grimImageY = y;
		
	}

	public void draw(Graphics2D g2){
		
		if (isGrimFill()){
			g2.setColor(grimFillColor);
			g2.fill(grimShape);
			
		}
		
		if (grimStrokeColor!=null){
			g2.setStroke(new BasicStroke(this.grimStrokeWidth));
			g2.setColor(grimStrokeColor);
			g2.draw(grimShape);
		}
		
		//ADDED FOR IMAGE AND TEXT DRAW BY JUNG
		if(grimImage != null){
			//g2.drawImage(Image, this.grimImageX, this.grimImageY, 
			//		this.grimImageW, this.grimImageH, null);
			//System.out.println("Grimimage !=NULL");
			this.grimImage.paintIcon(null, g2, this.grimImageX, this.grimImageY);
		}
		
		if(grimText != null){
			//System.out.println("Im in:");
			//g2.setColor(Color.BLACK);
			g2.setFont(grimFont);
			g2.drawString(grimText, grimImageX, grimImageY);
			//g2.draw(grimShape);
			
		}
		
	}
	public boolean contains(double px, double py){
		return this.grimShape.contains(px, py);
	}

	public void translate(float dx, float dy){
		AffineTransform tr = new AffineTransform();
		tr.setToTranslation(dx, dy);
		this.grimShape = tr.createTransformedShape(this.grimShape);
		this.grimImageX = this.grimImageX + (int)tr.getTranslateX();
		this.grimImageY = this.grimImageY + (int)tr.getTranslateY();
	}
	/*
	public void setGrimImage(Image grimImg){
		this.Image = Image;
	}
	public Image getGrimImage(){
		return Image;
	}
*/
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
	
}
