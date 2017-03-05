/**
 * Created on Oct 7, 2012
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.cse.svg.svggen;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.*;
import java.util.*;

public class AppleClock extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int hour;
	private int minute;
	private int second;

	int clockRadius;
	int xCenter;
	int yCenter;
	
	/** Construct a default clock with the current time*/
	public AppleClock() {
		setCurrentTime();
	}

	/** Construct a clock with specified hour, minute, and second */
	public AppleClock(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	/** Return hour */
	public int getHour() {
		return hour;
	}

	/** Set a new hour */
	public void setHour(int hour) {
		this.hour = hour;
		repaint();
	}

	/** Return minute */
	public int getMinute() {
		return minute;
	}

	/** Set a new minute */
	public void setMinute(int minute) {
		this.minute = minute;
		repaint();
	}

	/** Return second */
	public int getSecond() {
		return second;
	}

	/** Set a new second */
	public void setSecond(int second) {
		this.second = second;
		repaint();
	}

	/** Draw the clock */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		paintPanel(g2);
	}
	void paintPanel(Graphics2D g2){
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Initialize clock parameters
		clockRadius =
				(int)(Math.min(getWidth(), getHeight()) * 0.8 * 0.5);
		xCenter = getWidth() / 2;
		yCenter = getHeight() / 2;
		
		// Draw circle
		Shape clockCircle = new Ellipse2D.Double(xCenter - clockRadius, yCenter - clockRadius,
				2 * clockRadius, 2 * clockRadius);
		g2.setColor(new Color(255, 245, 238)); // seashell
		g2.fill(clockCircle);

		g2.setColor(Color.BLACK);
		g2.translate(xCenter, yCenter);

		// Draw Min Line
		float strokeWidth = Math.max(clockRadius*3/160, 1f);
		g2.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		int minLeng = clockRadius*16/160;
		Point2D p1 = new Point2D.Double(0, -clockRadius + 5);
		Point2D p2 = new Point2D.Double(0, -clockRadius + 5 + minLeng);
		Shape minLine = new Line2D.Double(p1, p2);
		AffineTransform tr2 = new AffineTransform();
		for (int i = 0; i < 60; i++) {
			tr2.setToRotation(i*2*Math.PI/60);
			Shape trLine = tr2.createTransformedShape(minLine);
			g2.draw(trLine);
		}
		// Draw Hour Line
		strokeWidth = clockRadius*13/160;
		int hourLeng = clockRadius*36/160;
		g2.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		p1 = new Point2D.Double(0, -clockRadius + 5);
		p2 = new Point2D.Double(0, -clockRadius + 5 + hourLeng);
		Shape hourLine = new Line2D.Double(p1, p2);
		for (int i = 0; i < 12; i++) {
			tr2.setToRotation(i*2*Math.PI/12);
			Shape trLine = tr2.createTransformedShape(hourLine);
			g2.draw(trLine);
		}
		g2.translate(-xCenter, -yCenter);

		// Draw Name of Clock
		g2.drawString("Apple Zaktung", xCenter-45, (float)(yCenter+clockRadius*0.6));
		//System.out.println("Font Size="+g2.getFont().getSize());

		// Draw Shadow
		drawShadow(g2);

		// Draw minute hand
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		p1 = new Point2D.Double(0, clockRadius*0.3);
		p2 = new Point2D.Double(0, -clockRadius*0.85);
		//Shape minHand = new Line2D.Double(p1, p2);
		Shape minHand = new Rectangle2D.Double(-strokeWidth*0.5, -clockRadius*0.85, (double)strokeWidth, clockRadius*0.3+clockRadius*0.85);
		AffineTransform tr1 = new AffineTransform();
		tr1.setToRotation(minute * (2 * Math.PI / 60));
		Shape trHand = tr1.createTransformedShape(minHand);
		tr1.setToTranslation(xCenter, yCenter);
		trHand = tr1.createTransformedShape(trHand);
		g2.fill(trHand);

		// Draw hour hand
		p1 = new Point2D.Double(0, clockRadius*0.3);
		p2 = new Point2D.Double(0, -clockRadius*0.5);
		Shape hourHand = new Rectangle2D.Double(-strokeWidth*0.5, -clockRadius*0.5, (double)strokeWidth, clockRadius*0.3+clockRadius*0.5);
		tr1.setToRotation((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		trHand = tr1.createTransformedShape(hourHand);
		tr1.setToTranslation(xCenter, yCenter);
		trHand = tr1.createTransformedShape(trHand);
		g2.fill(trHand);

		// Draw second hand
		g2.setColor(Color.RED);
		int centerS = clockRadius*5/160;
		g2.fillOval(xCenter-centerS, yCenter-centerS, centerS*2, centerS*2);
		strokeWidth = clockRadius*4/160;
		g2.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		p1 = new Point2D.Double(0, clockRadius*0.26);
		p2 = new Point2D.Double(0, -clockRadius*0.68);
		Line2D secHand = new Line2D.Double(p1, p2);
		tr1.setToRotation(second * (2 * Math.PI / 60));
		trHand = tr1.createTransformedShape(secHand);
		tr1.setToTranslation(xCenter, yCenter);
		trHand = tr1.createTransformedShape(trHand);
		g2.draw(trHand);
		// Draw Red Circle at End Point
		double endS = clockRadius*20/160d;
		double endX = -endS/2;
		double endY = -clockRadius*0.7;
		Ellipse2D secHandEnd = new Ellipse2D.Double(endX, endY, endS, endS);
		tr1.setToRotation(second * (2 * Math.PI / 60));
		trHand = tr1.createTransformedShape(secHandEnd);
		tr1.setToTranslation(xCenter, yCenter);
		trHand = tr1.createTransformedShape(trHand);
		g2.fill(trHand);

		// Draw Clock Base Rect
		Color baseColor = new Color(135, 206, 250, 50); // lightskyblue
		g2.setColor(baseColor);
		g2.fillRect((int)(xCenter-clockRadius*1.1), (int)(yCenter-clockRadius*1.1), (int)(clockRadius*2.2), (int)(clockRadius*2.2));

	
	}
	private void drawShadow(Graphics2D gclock){

		int strokeWidth = clockRadius*13/160;
		BufferedImage shadowImage 
			= BufferedImageUtility.getOneColorImage(getWidth(), getHeight(), new Color(0, true));

		Graphics2D g2 = (Graphics2D)(shadowImage.getGraphics());
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(new Color(50,50,50,127));
		g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));

		// Draw minute hand
		g2.translate(2, 2);
		Point2D p1 = new Point2D.Double(0, clockRadius*0.3);
		Point2D p2 = new Point2D.Double(0, -clockRadius*0.85);
		Shape minHand = new Rectangle2D.Double(-strokeWidth*0.5, -clockRadius*0.85, (double)strokeWidth, clockRadius*0.3+clockRadius*0.85);
		AffineTransform tr1 = new AffineTransform();
		tr1.setToRotation(minute * (2 * Math.PI / 60));
		Shape trHand = tr1.createTransformedShape(minHand);
		tr1.setToTranslation(xCenter, yCenter);
		trHand = tr1.createTransformedShape(trHand);
		g2.fill(trHand);

		// Draw hour hand
		g2.translate(2, 2);
		p1 = new Point2D.Double(0, clockRadius*0.3);
		p2 = new Point2D.Double(0, -clockRadius*0.5);
		Shape hourHand = new Rectangle2D.Double(-strokeWidth*0.5, -clockRadius*0.5, (double)strokeWidth, clockRadius*0.3+clockRadius*0.5);
		tr1.setToRotation((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		trHand = tr1.createTransformedShape(hourHand);
		tr1.setToTranslation(xCenter, yCenter);
		trHand = tr1.createTransformedShape(trHand);
		g2.fill(trHand);

		// Draw second hand
		g2.translate(1, 1);
		int centerS = clockRadius*5/160;
		g2.fillOval(xCenter-centerS, yCenter-centerS, centerS*2, centerS*2);
		strokeWidth = clockRadius*4/160;
		g2.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		p1 = new Point2D.Double(0, clockRadius*0.26);
		p2 = new Point2D.Double(0, -clockRadius*0.68);
		Line2D secHand = new Line2D.Double(p1, p2);
		tr1.setToRotation(second * (2 * Math.PI / 60));
		trHand = tr1.createTransformedShape(secHand);
		tr1.setToTranslation(xCenter, yCenter);
		trHand = tr1.createTransformedShape(trHand);
		g2.draw(trHand);
		// Draw Red Circle at End Point
		double endS = clockRadius*20/160d;
		double endX = -endS/2;
		double endY = -clockRadius*0.7;
		Ellipse2D secHandEnd = new Ellipse2D.Double(endX, endY, endS, endS);
		tr1.setToRotation(second * (2 * Math.PI / 60));
		trHand = tr1.createTransformedShape(secHandEnd);
		tr1.setToTranslation(xCenter, yCenter);
		trHand = tr1.createTransformedShape(trHand);
		g2.fill(trHand);
		
		shadowImage = BufferedImageUtility.getBlurImage(shadowImage, 5);
		gclock.drawImage(shadowImage, 0, 0, null);
	}
	public void setCurrentTime() {
		// Construct a calendar for the current date and time
		Calendar calendar = new GregorianCalendar();

		// Set current hour, minute and second
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
	}

}
