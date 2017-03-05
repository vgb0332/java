/**
 * Created on Oct 14, 2012
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.appclock;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class TransparentBackground extends JComponent 
	implements ComponentListener, WindowFocusListener, Runnable { 
	private JFrame frame; 
	private Image background;
	private long lastupdate = 0;
	public boolean refreshRequested = true;

	public TransparentBackground(JFrame frame) {
		this.frame = frame;
		updateBackground( );
		frame.addComponentListener(this); 
		frame.addWindowFocusListener(this);
        //new Thread(this).start( );
	}

	public void updateBackground( ) {
		try {
			Robot rbt = new Robot( );
			Toolkit tk = Toolkit.getDefaultToolkit( );
			Dimension dim = tk.getScreenSize( );
			background = rbt.createScreenCapture(
					new Rectangle(0,0,(int)dim.getWidth( ),	(int)dim.getHeight( )));
		} catch (Exception ex) {
			System.err.println(ex.toString( ));
			ex.printStackTrace( );
		}
	}
	public void componentShown(ComponentEvent evt) { repaint( ); }
	public void componentResized(ComponentEvent evt) { repaint( ); }
	public void componentMoved(ComponentEvent evt) { repaint( ); }
	public void componentHidden(ComponentEvent evt) { }

	public void windowGainedFocus(WindowEvent evt) { refresh( ); }    
	public void windowLostFocus(WindowEvent evt) { refresh( ); }
	public void paintComponent(Graphics g) {
		Point pos = this.getLocationOnScreen( );
		Point offset = new Point(-pos.x,-pos.y);
		g.drawImage(background,offset.x,offset.y,null);
	}
	public void refresh( ) {
		if(frame.isVisible( )) {
			updateBackground( );
			repaint( );
			refreshRequested = true;
			lastupdate = new Date( ).getTime( );
		}
	}
	public void run( ) {
		try {
			while(true) {
				Thread.sleep(1000);
				long now = new Date( ).getTime( );
				if(refreshRequested &&
					((now - lastupdate) > 5000)) {
					if(frame.isVisible( )) {
	                    Point location = frame.getLocation( );
	                    frame.setVisible(false );
	                    updateBackground( );
	                    frame.setVisible(true );
					frame.setLocation(location);
	                    refresh( );
					}
					lastupdate = now;
					refreshRequested = false;
					}
				}
			} catch (Exception ex) {
				System.err.println(ex.toString( ));
				ex.printStackTrace( );
			} 
		} 
}