/**
 * Created on Sep 2, 2012
 * @author cskim at hufs.ac.kr
 * Copy Right -- Free for Educational Purpose
 */
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * @author cskim
 *
 */
public class OneImageButtonChange extends JFrame {

	private OneImageButtonChange thisClass = this;
	private JPanel contentPane;
	private JButton btnOneImage;
	
	BufferedImage beforeImage = null;
	BufferedImage afterImage = null;
	int fwidth = 400;
	int fheight = 400;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					OneImageButtonChange frame = new OneImageButtonChange();
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
	public OneImageButtonChange() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {

				fwidth = thisClass.getWidth();
				fheight = thisClass.getHeight();
				btnOneImage
					.setIcon(new ImageIcon(((ImageIcon)btnOneImage.getIcon()).getImage()
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
		btnOneImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnOneImage
				.setIcon(new ImageIcon(afterImage.getScaledInstance(fwidth, fheight, Image.SCALE_SMOOTH)));
			}
		});
		btnOneImage.setIcon(new ImageIcon(OneImageButton.class.getResource("/images/androidlogo500x500.png")));
		contentPane.add(btnOneImage, BorderLayout.CENTER);
		
		initialize();
	}
	void initialize(){
		
		fwidth = this.getWidth();
		fheight = this.getHeight();

		try {
			beforeImage = ImageIO
					.read(getClass().getResource("/images/androidlogo500x500.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			afterImage = ImageIO
					.read(getClass().getResource("/images/simpsons1.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnOneImage.setIcon(new ImageIcon(beforeImage.getScaledInstance(fwidth, fheight, Image.SCALE_SMOOTH)));

	}

}
