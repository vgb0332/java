package hufs.ces.clock;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import javax.swing.*;
import java.util.*;

public class FancyClock extends JPanel {
	private int hour;
	private int minute;
	private int second;

	/** Construct a default clock with the current time*/
	public FancyClock() {
		setCurrentTime();
	}

	/** Construct a clock with specified hour, minute, and second */
	public FancyClock(int hour, int minute, int second) {
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
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Initialize clock parameters
		int clockRadius =
				(int)(Math.min(getWidth(), getHeight()) * 0.8 * 0.5);
		int xCenter = getWidth() / 2;
		int yCenter = getHeight() / 2;
		
		//Added by Yongsuk Jung
		GradientPaint gp = new GradientPaint(0, 0, Color.white, 
				xCenter*2, yCenter*2, Color.red, true); ///Color changed by me
		g2.setPaint(gp);
		//g2.setColor(Color.BLUE);		
		g2.fillOval(0, yCenter - clockRadius-20, 2*(clockRadius+20), 2*(clockRadius+20));
		
		

		// Draw circle
		GradientPaint gp1 = new GradientPaint(0, 0, Color.GRAY, 
				xCenter, yCenter, Color.WHITE, true);
		g2.setPaint(gp1);
		Shape clockCircle = new Ellipse2D.Double(xCenter - clockRadius, yCenter - clockRadius,
				2 * clockRadius, 2 * clockRadius);
		g2.fill(clockCircle);
		
		//Added by Yongsuk Jung - outline of clockCircle
		GradientPaint gp2 = new GradientPaint(0, 0, Color.blue, 
				xCenter*2, yCenter*2, Color.white, true); ///Color changed by me
		g2.setPaint(gp2);
		//g2.setColor(Color.RED);
		g2.setStroke(new BasicStroke(10));
		g2.draw(clockCircle);
		g2.setStroke(new BasicStroke(2));
		
		
		g2.setColor(Color.black);
		g2.setFont(new Font("Impact", Font.ITALIC, 20));
		//Changed by Yongsuk Jung
		g2.drawString("XII", xCenter - 10, yCenter - clockRadius + 25);
		g2.drawString("IX", xCenter - clockRadius + 5, yCenter + 5);
		g2.drawString("III", xCenter + clockRadius - 25, yCenter + 3);
		g2.drawString("VI", xCenter - 5, yCenter + clockRadius - 5);

		//Added by Yongsuk Jung
		g2.setColor(Color.white);
		g2.fillRect(xCenter-25, yCenter, 50, 30);
		g2.setColor(Color.DARK_GRAY);
		g2.drawRect(xCenter-25, yCenter, 50, 30);
		g2.setColor(Color.DARK_GRAY);
		g2.setFont(new Font("Italic", Font.BOLD, 25));
		if(this.hour <= 11)
			g2.drawString("A.M", xCenter - 24, yCenter+25);
		else
			g2.drawString("P.M", xCenter - 24, yCenter+25);
				
				
		// Draw second hand
		int sLength = (int)(clockRadius * 0.8);
		Path2D secHand = new Path2D.Double();
		secHand.moveTo(-3, 0);
		secHand.lineTo(-6, -3);
		secHand.lineTo(sLength, 0);
		secHand.lineTo(-6, 3);
		secHand.closePath();
		AffineTransform tr1 = new AffineTransform();
		tr1.setToRotation(second * (2 * Math.PI / 60)-Math.PI/2);
		Shape secHandTr = tr1.createTransformedShape(secHand);
		tr1.setToTranslation(xCenter, yCenter);
		secHandTr = tr1.createTransformedShape(secHandTr);

		g2.setColor(Color.black);
		g2.fill(secHandTr);

		// Draw minute hand
		int mLength = (int)(clockRadius * 0.65);
		int xMinute = (int)(xCenter + mLength *
				Math.sin(minute * (2 * Math.PI / 60)));
		int yMinute = (int)(yCenter - mLength *
				Math.cos(minute * (2 * Math.PI / 60)));
		g2.setColor(Color.black);
		g2.drawLine(xCenter, yCenter, xMinute, yMinute);

		// Draw hour hand
		int hLength = (int)(clockRadius * 0.5);
		int xHour = (int)(xCenter + hLength *
				Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
		int yHour = (int)(yCenter - hLength *
				Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
		g2.setColor(Color.black);
		g2.drawLine(xCenter, yCenter, xHour, yHour);
		
		// Added by me for center
		g2.setColor(Color.black);
		g2.fillOval(xCenter-7, yCenter-7, 14, 14);
		
		
		
	
	}

	public void setCurrentTime() {
		// Construct a calendar for the current date and time
		Calendar calendar = new GregorianCalendar();
		calendar.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
		// Set current hour, minute and second
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
	}
	

	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}
}
