package hufs.ces.image;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OneImageButton extends JFrame {

	private JPanel contentPane;
	JButton btnOneImage = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OneImageButton frame = new OneImageButton();
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
	public OneImageButton() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		btnOneImage = new JButton("New button");
		btnOneImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnOneImage.setIcon(new ImageIcon(OneImageButton.class.getResource("/images/androidlogo500x500.png")));
			}
		});
		btnOneImage.setIcon(new ImageIcon(OneImageButton.class.getResource("/images/simpsons1.gif")));
		contentPane.add(btnOneImage, BorderLayout.CENTER);
	}

}
