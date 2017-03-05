package hufs.ces.digitalclock;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Formatter;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class DigitalClockFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblDate;
	private JLabel lblTime;
	private JButton btnStart;


	/**
	 * Create the frame.
	 */
	public DigitalClockFrame() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		lblDate = new JLabel("");
		lblDate.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 23));
		lblDate.setForeground(new Color(65, 105, 225));
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setPreferredSize(new Dimension(500, 100));
		contentPane.add(lblDate, BorderLayout.NORTH);
		
		lblTime = new JLabel("");
		lblTime.setForeground(new Color(128, 128, 0));
		lblTime.setFont(new Font("Tw Cen MT", Font.BOLD, 32));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTime, BorderLayout.CENTER);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startClock();
			}
		});
		contentPane.add(btnStart, BorderLayout.SOUTH);
	}
	void startClock(){
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

}
