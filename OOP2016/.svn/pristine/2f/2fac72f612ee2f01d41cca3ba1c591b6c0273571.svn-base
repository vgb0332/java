/**
 * Created on Sep 3, 2012
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.image;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author cskim
 *
 */
public class NxNImageLabel extends JFrame {

	private JPanel contentPane;

	private static final int PROWS = 10;
	private static final int PSIZE = PROWS*PROWS;

	private JLabel[] tiles = new JLabel[PSIZE];
	private BufferedImage tileImage = null;

	int scrHeight = 0;
	int scrWidth = 0;
    int bwidth = 0;
    int bheight = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					NxNImageLabel frame = new NxNImageLabel();
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
	public NxNImageLabel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(PROWS, PROWS, 0, 0));
		initialize();
	}

	void initialize(){
		scrHeight = this.getHeight();
		scrWidth = this.getWidth();
		//System.out.println("w="+scrWidth+" h="+scrHeight);
		try {
			tileImage = ImageIO
					.read(getClass().getResource("/images/androidlogo500x500.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        bwidth = scrWidth/PROWS;
        bheight = scrHeight/PROWS;
        ImageIcon tileIcon = new ImageIcon(tileImage
        		.getScaledInstance(bwidth, bheight, Image.SCALE_SMOOTH));
		for (int i=0; i<PSIZE; ++i){
			tiles[i] = new JLabel();
			tiles[i].setIcon(tileIcon);
			contentPane.add(tiles[i]);
		}
	}
	
}
