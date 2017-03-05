/**
 * Created on Oct 7, 2012
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.appletclock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 * @author cskim
 *
 */
public class AppletClock extends JApplet {

	private AppleClock appleClock = null;

	public void init(){

		//this.setBounds(0, 0, 400, 400);
		appleClock = new AppleClock(this);
		add(appleClock);
		// Create a timer with delay 1000 ms
		Timer timer = new Timer(1000, new TimerListener());
		timer.start();
	}
	private class TimerListener implements ActionListener {
		/** Handle the action event */
		public void actionPerformed(ActionEvent e) {
			// Set new time and repaint the clock to display current time
			appleClock.setCurrentTime();
			appleClock.repaint();
			
		}
	}

}
