/**
 * Created on Oct 7, 2012
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.clock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 * @author cskim
 *
 */
public class FancyClockMain extends JFrame {

	private JPanel contentPane;
	private FancyClock clock = new FancyClock();
	private MessagePanel messagePanel = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FancyClockMain frame = new FancyClockMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FancyClockMain() {
		//setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 434);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		contentPane.add(clock, BorderLayout.CENTER);
		clock.setLayout(null);

		messagePanel = new MessagePanel(clock.getHour() +
				":" + clock.getMinute() + ":" + clock.getSecond());
		messagePanel.setCentered(true);
		messagePanel.setForeground(Color.blue);
		messagePanel.setFont(new Font("Courie", Font.BOLD, 16));
		// Add the clock and message panel to the frame
		contentPane.add(messagePanel, BorderLayout.SOUTH);
		
		// Create a timer with delay 1000 ms
		Timer timer = new Timer(1000, new TimerListener());
		timer.start();
	}

	private class TimerListener implements ActionListener {
		/** Handle the action event */
		public void actionPerformed(ActionEvent e) {
			// Set new time and repaint the clock to display current time
			clock.setCurrentTime();
			clock.repaint();
			
			messagePanel.setMessage(clock.getHour() + ":" 
					+ clock.getMinute() + ":" + clock.getSecond());
			
		}
	}

}
