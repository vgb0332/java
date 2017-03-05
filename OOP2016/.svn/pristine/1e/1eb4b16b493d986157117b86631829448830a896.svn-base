package hufs.ces.image;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class OneImageAvgButton extends JFrame {

	private OneImageAvgButton thisClass = this;
	private JPanel contentPane;
	private JButton btnOneImage;

	BufferedImage origImage = null;
	BufferedImage avgImage = null;
	int fwidth = 400;
	int fheight = 400;

	boolean isAvgImage = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					OneImageAvgButton frame = new OneImageAvgButton();
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
	public OneImageAvgButton() {
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
				if (!isAvgImage){
					btnOneImage
					.setIcon(new ImageIcon(avgImage.getScaledInstance(fwidth, fheight, Image.SCALE_SMOOTH)));
					isAvgImage = true;
				}
				else {
					btnOneImage
					.setIcon(new ImageIcon(origImage.getScaledInstance(fwidth, fheight, Image.SCALE_SMOOTH)));
					isAvgImage = false;
				}
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
			origImage = ImageIO
					.read(getClass().getResource("/images/simpsons1.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Color bcol = getAverageColor(origImage);
		avgImage = OneColorBufferedImage.getBufferedImage(fwidth, fheight, bcol);


		btnOneImage.setIcon(new ImageIcon(origImage.getScaledInstance(fwidth, fheight, Image.SCALE_SMOOTH)));

	}
	Color getAverageColor(BufferedImage tile){
		int twidth = tile.getWidth();
		int theight =  tile.getHeight();
		double pixSize = twidth*theight;
		double sumRed = 0;
		double sumGreen = 0;
		double sumBlue = 0;
		Color pixColor = null;
		for (int i=0; i<theight; ++i){
			for (int j=0; j<twidth; ++j){
				pixColor = new Color(tile.getRGB(i,j));
				sumRed += pixColor.getRed();
				sumGreen += pixColor.getGreen();
				sumBlue += pixColor.getBlue();
			}
		}
		int avgRed = (int)(sumRed/pixSize);
		int avgGreen = (int)(sumGreen/pixSize);
		int avgBlue = (int)(sumBlue/pixSize);
		return new Color(avgRed, avgGreen, avgBlue);
	}

}