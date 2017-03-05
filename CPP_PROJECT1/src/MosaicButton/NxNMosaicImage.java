/**
 * Created on Sep 3, 2012
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package MosaicButton;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author cskim
 *
 */
public class NxNMosaicImage extends JFrame {

	private JPanel contentPane;

	private static final int PROWS = 20;
	private static final int PSIZE = PROWS*PROWS;

	//Corrected: JPanel changed to JButton
	private JButton[] tiles = new JButton[PSIZE];
	//Added: Array of BufferedImage variable for storing segments of the image
	private BufferedImage[] origData = new BufferedImage[PSIZE];
	private BufferedImage tileImage = null;
	//Added: Array of Boolean variable for checking whether current button is in Mosaic or Original.
    boolean[] isOrig = new boolean[PSIZE];
	
	
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
					NxNMosaicImage frame = new NxNMosaicImage();
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
	public NxNMosaicImage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Added: Personally changed the x, y coordinate and the size of the contentPane
		setBounds(100, 20, 700, 700);
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
					.read(getClass().getResource("/Sulhyun2.png"));//images/androidlogo500x500.png
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int pwidth = tileImage.getWidth()/PROWS; //tileImage/50
		int pheight =  tileImage.getHeight()/PROWS;
        bwidth = scrWidth/PROWS;
        bheight = scrHeight/PROWS;
        
        //Added: Add the segements of the image into BufferedImage array variable
        for (int i=0; i<PROWS; ++i){
        	for (int j=0; j<PROWS; ++j){//1~2500源뚯� �뒪�뵆由�
        		origData[PROWS*i+j] = 
        				tileImage.getSubimage(j*pwidth, i*pheight, pwidth, pheight);
        	}
        }
      
        //ImageIcon tileIcon = new ImageIcon(tileImage
        //		.getScaledInstance(bwidth, bheight, Image.SCALE_SMOOTH));
        
        //Added: Create buttons and add them to the contentPane
		for (int i=0; i<PSIZE; ++i){
			tiles[i] = new JButton();
			//Added: Tag an index to every button
			tiles[i].setName(""+i);
			//Added: Insert a respective image to a respective button
			tiles[i].setIcon(new ImageIcon(origData[i]
					.getScaledInstance(bwidth, bheight, Image.SCALE_SMOOTH)));
			//Added: Set isOrig to true
			isOrig[i] = true;
			//Added: Add actionListener to every button
			tiles[i].addActionListener(new MyActionListener());
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
		//System.out.println("twidth : " + twidth + ", theight: " + theight);
		
		//Added: i shouldve been up to twidth not theight
		//Corrected: theight -> twidth,  twidth -> theight
		for (int i=0; i<twidth; ++i){
			for (int j=0; j<theight; ++j){
				//System.out.println("Error ocurred at " + i + " " + j);
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
	
	//Added: my own actionlistener class definition
	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//Extract the button that was clicked
			JButton temp = (JButton)e.getSource();
			//Get the index of the button clicked
			int currentIdx = Integer.parseInt(temp.getName());
			
			//if the image is original, set the image to mosaic image
			//else, set the image back to original.
			if(isOrig[currentIdx]){
				Color bcol = getAverageColor(origData[currentIdx]);
				temp.setIcon(new ImageIcon(OneColorBufferedImage.getBufferedImage(bwidth, bheight, bcol)));
				isOrig[currentIdx] = false;
			}
			else{
				temp.setIcon(new ImageIcon(origData[currentIdx]
						.getScaledInstance(bwidth, bheight, Image.SCALE_SMOOTH)));
				isOrig[currentIdx] = true;
			}
		}
		
	}
	
}
