/**
 * Created on Sep 3, 2012
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.image;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
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
public class MosaicImage extends JFrame {

	private JPanel contentPane;

	private static final int PROWS = 50;
	private static final int PSIZE = PROWS*PROWS;

	private JLabel[] tiles = new JLabel[PSIZE];
	private BufferedImage[] imageData = new BufferedImage[PSIZE];
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
					MosaicImage frame = new MosaicImage();
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
	public MosaicImage() {
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
		int pwidth = tileImage.getWidth()/PROWS;
		int pheight =  tileImage.getHeight()/PROWS;
        bwidth = scrWidth/PROWS;
        bheight = scrHeight/PROWS;

        for (int i=0; i<PROWS; ++i){
        	for (int j=0; j<PROWS; ++j){
        		imageData[PROWS*i+j] = 
        				tileImage.getSubimage(j*pwidth, i*pheight, pwidth, pheight);
        	}
        }
       
		for (int i=0; i<PSIZE; ++i){
			Color bcol = getAverageColor(imageData[i]);
			tiles[i] = new JLabel();
			tiles[i].setIcon(new ImageIcon(OneColorBufferedImage.getBufferedImage(bwidth, bheight, bcol)));
			contentPane.add(tiles[i]);
		}
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
