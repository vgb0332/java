package hufs.ces.image;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class OneImageButtonFit extends JFrame {

	private JPanel contentPane;
	private JButton btnOneImage;
	OneImageButtonFit thisClass = this;
	
	BufferedImage oneImage = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OneImageButtonFit frame = new OneImageButtonFit();
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
	public OneImageButtonFit() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int fwidth = thisClass.getWidth();
				int fheight = thisClass.getHeight();
				
				btnOneImage
				.setIcon(new ImageIcon(oneImage
						.getScaledInstance(fwidth, fheight, Image.SCALE_SMOOTH)));

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		btnOneImage = new JButton("");
		btnOneImage.setIcon(new ImageIcon(OneImageButton.class.getResource("/images/androidlogo500x500.png")));
		contentPane.add(btnOneImage, BorderLayout.CENTER);
		
		initialize();
	}
	void initialize(){
		
		try {
			oneImage = ImageIO
					.read(getClass().getResource("/images/androidlogo500x500.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int fwidth = this.getWidth();
		int fheight = this.getHeight();
		
		btnOneImage
		.setIcon(new ImageIcon(oneImage
				.getScaledInstance(fwidth, fheight, Image.SCALE_SMOOTH)));

	}

}
