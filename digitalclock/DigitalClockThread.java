package hufs.ces.digitalclock;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Formatter;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class DigitalClockThread extends JFrame {

	private JPanel contentPane;
	private JLabel lblDate;
	private JLabel lblTime;
	private JButton btnStart;
	
	/**
	 * Create the frame.
	 */
	public DigitalClockThread() {
		
		initialize();
		
	}
	
	void initialize(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		lblDate = new JLabel("");
		lblDate.setForeground(new Color(0, 128, 0));
		lblDate.setFont(new Font("Verdana", Font.BOLD, 30));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setPreferredSize(new Dimension(200, 100));
		contentPane.add(lblDate, BorderLayout.NORTH);
		
		lblTime = new JLabel("");
		lblTime.setForeground(Color.BLUE);
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setFont(new Font("Verdana", Font.BOLD, 36));
		contentPane.add(lblTime, BorderLayout.CENTER);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread thread = new TimerThread();
				thread.start();
			}
		});
		contentPane.add(btnStart, BorderLayout.SOUTH);
	}
	
	void setTimeDisplay(){
		
		Calendar calendar = Calendar.getInstance();

		StringBuilder timeText = new StringBuilder();
		Formatter buildTime = new Formatter(timeText);
		buildTime.format("%tT", calendar);
		lblTime.setText(timeText.toString());

		StringBuilder dateText = new StringBuilder();
		Formatter buildDate = new Formatter(dateText);
		buildDate.format("%1$tY.%1$tm.%1$td", calendar);
		lblDate.setText(dateText.toString());
	}
	class TimerThread extends Thread {

		@Override
		public void run() {
			while (true){
				setTimeDisplay();
				//*
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//*/
			}
		}
	}

}
