/**
 * Created on Oct 7, 2012
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.appclock;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import javax.swing.*;
import java.util.*;

public class AppClock extends TransparentBackground {
	private int hour;
	private int minute;
	private int second;

	/** Construct a default clock with the current time*/
	public AppClock(JFrame frame) {
		super(frame);
		setCurrentTime();
	}

	/** Construct a clock with specified hour, minute, and second */
	public AppClock(JFrame frame, int hour, int minute, int second) {
		super(frame);
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
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Initialize clock parameters
		int clockRadius =
				(int)(Math.min(getWidth(), getHeight()) * 0.8 * 0.5);
		int xCenter = getWidth() / 2;
		int yCenter = getHeight() / 2;

		// Draw circle
		Shape clockCircle = new Ellipse2D.Double(xCenter - clockRadius, yCenter - clockRadius,
				2 * clockRadius, 2 * clockRadius);
		g2.setColor(Color.WHITE);
		g2.fill(clockCircle);
		
		g2.setColor(Color.BLACK);
		g2.translate(xCenter, yCenter);

		// Draw Min Line
		g2.setStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		int minLeng = 12;
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
	    float strokeWidth = 11f;
	    int hourLeng = 35;
		g2.setStroke(new BasicStroke(11f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
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
		g2.drawString("Apple Zaktung", (float)(xCenter-45), (float)(yCenter+clockRadius*0.6));

		// Draw minute hand
		p1 = new Point2D.Double(0, clockRadius*0.2);
		p2 = new Point2D.Double(0, -clockRadius*0.85);
		Shape minHand = new Line2D.Double(p1, p2);
		AffineTransform tr1 = new AffineTransform();
		tr1.setToRotation(minute * (2 * Math.PI / 60));
		Shape trHand = tr1.createTransformedShape(minHand);
		tr1.setToTranslation(xCenter, yCenter);
		trHand = tr1.createTransformedShape(trHand);
		g2.draw(trHand);

		// Draw hour hand
		p1 = new Point2D.Double(0, clockRadius*0.2);
		p2 = new Point2D.Double(0, -clockRadius*0.5);
		Shape hourHand = new Line2D.Double(p1, p2);
		tr1.setToRotation((hour % 12 + minute / 60.0) * (2 * Math.PI / 12));
		trHand = tr1.createTransformedShape(hourHand);
		tr1.setToTranslation(xCenter, yCenter);
		trHand = tr1.createTransformedShape(trHand);
		g2.draw(trHand);

		// Draw second hand
		g2.setColor(Color.RED);
		g2.fillOval(xCenter-5, yCenter-5, 10, 10);
		g2.setStroke(new BasicStroke(4f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
		p1 = new Point2D.Double(0, clockRadius*0.18);
		p2 = new Point2D.Double(0, -clockRadius*0.7);
		Line2D secHand = new Line2D.Double(p1, p2);
		tr1.setToRotation(second * (2 * Math.PI / 60));
		trHand = tr1.createTransformedShape(secHand);
		tr1.setToTranslation(xCenter, yCenter);
		trHand = tr1.createTransformedShape(trHand);
		g2.draw(trHand);
		// Draw Red Circle at End Point
		double endS = 20;
		double endX = -endS/2;
		double endY = -clockRadius*0.7;
		Ellipse2D secHandEnd = new Ellipse2D.Double(endX, endY, endS, endS);
		tr1.setToRotation(second * (2 * Math.PI / 60));
		trHand = tr1.createTransformedShape(secHandEnd);
		tr1.setToTranslation(xCenter, yCenter);
		trHand = tr1.createTransformedShape(trHand);
		g2.fill(trHand);
		
	}

	public void setCurrentTime() {
		// Construct a calendar for the current date and time
		Calendar calendar = new GregorianCalendar();

		// Set current hour, minute and second
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
	}

	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}
}
