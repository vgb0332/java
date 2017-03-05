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
import java.awt.GradientPaint;
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
	private FancyClock2 clock2 = new FancyClock2(); //Added by Yongsuk Jung
	private MessagePanel timeMessageSeoul = null; //Variable name changed by Yongsuk Jung
	private MessagePanel timeMessageHono = null; //Added by Yongsuk Jung
	private MessagePanel titlePanel = null;       //Added by Yongsuk Jung
	private MessagePanel cityMessageSeoul = new MessagePanel("Seoul"); //Added by Yongsuk Jung
	private MessagePanel cityMessageHono = new MessagePanel("Honolulu");//Added by Yongsuk Jung
	
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

		contentPane.add(clock, BorderLayout.WEST);
		clock.setLayout(new BorderLayout(0, 0));
		cityMessageSeoul.setBackground(new Color(244, 164, 96));
		cityMessageSeoul.setForeground(Color.BLUE);
		cityMessageSeoul.setFont(new Font("±Ã¼­", Font.BOLD | Font.ITALIC, 20));
		cityMessageSeoul.setCentered(true);
		
		//Added by Yongsuk Jung
		clock.add(cityMessageSeoul, BorderLayout.SOUTH);

		timeMessageSeoul = new MessagePanel(clock.getHour() +
				":" + clock.getMinute() + ":" + clock.getSecond());
		timeMessageSeoul.setBackground(new Color(250, 128, 114));
		clock.add(timeMessageSeoul, BorderLayout.NORTH);
		timeMessageSeoul.setCentered(true);
		timeMessageSeoul.setForeground(Color.blue);
		timeMessageSeoul.setFont(new Font("Franklin Gothic Medium", Font.BOLD | Font.ITALIC, 19));
		clock2.setBackground(Color.WHITE);
		
		//Added by Yongsuk Jung
		contentPane.add(clock2, BorderLayout.EAST);
		clock2.setLayout(new BorderLayout(0, 0));
		cityMessageHono.setBackground(Color.YELLOW);
		cityMessageHono.setForeground(new Color(255, 0, 51));
		cityMessageHono.setFont(new Font("Courier New", Font.BOLD, 19));
		cityMessageHono.setCentered(true);		
		clock2.add(cityMessageHono, BorderLayout.SOUTH);
		
		
		timeMessageHono = new MessagePanel(clock2.getHour() +
				":" + clock2.getMinute() + ":" + clock2.getSecond());
		timeMessageHono.setBackground(new Color(255, 127, 80));
		timeMessageHono.setCentered(true);
		clock2.add(timeMessageHono, BorderLayout.NORTH);
		timeMessageHono.setForeground(Color.YELLOW);
		timeMessageHono.setFont(new Font("Impact", Font.BOLD, 20));


		//Added by Yongsuk Jung
		titlePanel = new MessagePanel("Welcome to Mark's Clock Demo");	
		titlePanel.setBackground(Color.YELLOW);
		titlePanel.setCentered(true);
		titlePanel.setForeground(Color.DARK_GRAY);
		titlePanel.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 24));
		contentPane.add(titlePanel, BorderLayout.NORTH);
		
		////////////////Added by Yongsuk Jung///////////////////////
		// Create a timer with delay 1000 ms - Seoul Time
		Timer timer = new Timer(1000, new TimerListener());
		timer.start();
		
		/// Thread added - Honolulu Time
		Thread thread = new TimerThread();
		thread.start();
	}

	private class TimerListener implements ActionListener {
		/** Handle the action event */
		public void actionPerformed(ActionEvent e) {
			// Set new time and repaint the clock to display current time
			clock.setCurrentTime();
			clock.repaint();
			
			timeMessageSeoul.setMessage(clock.getHour() + ":" 
					+ clock.getMinute() + ":" + clock.getSecond());
			
		}
	}
	//Added by Yongsuk Jung
	private class TimerThread extends Thread {
		public void run() {
			while (true){
				//*
				try {
					Thread.sleep(100);
					clock2.setCurrentTime();
					clock2.repaint();
					//AM/PM validication
					String dayOrNight = clock2.getHour() <= 11 ? "A.M" : "P.M";
					timeMessageHono.setMessage(dayOrNight + " " + clock2.getHour()%12 + ":" 
							+ clock2.getMinute() + ":" + clock2.getSecond());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//*/
			}
		}
	}

}
