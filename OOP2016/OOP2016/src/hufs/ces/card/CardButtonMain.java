/**
 * Created on Sep 4, 2012
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.card;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author cskim
 *
 */
public class CardButtonMain extends JFrame {

	private JPanel contentPane;

	private static final int ROWMAX = 4;
	private static final int COLMAX = 13;

	protected CardButton[][] cBtn = new CardButton[ROWMAX][COLMAX];
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CardButtonMain frame = new CardButtonMain();
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
	public CardButtonMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 400);
		setTitle("Card Buttons");
		this.setSize(new Dimension(910, 410));

		contentPane = new JPanel();
		GridLayout gridLayout = new GridLayout();
		gridLayout.setColumns(COLMAX);
		gridLayout.setRows(ROWMAX);
		gridLayout.setHgap(0);
		gridLayout.setVgap(0);
		contentPane.setLayout(gridLayout);
		setContentPane(contentPane);
		
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		for (int row=0; row< ROWMAX; row++)
			for (int col=0; col< COLMAX; col++) {
				cBtn[row][col] = new CardButton(row,col+1);
				cBtn[row][col].setPreferredSize(new Dimension(72, 97));
				cBtn[row][col].setMnemonic(KeyEvent.VK_UNDEFINED);
				cBtn[row][col].setFocusPainted(false);
				cBtn[row][col].addActionListener(new ButtonListener(row,col));
				contentPane.add(cBtn[row][col], null);
			}
	
	}
	public class ButtonListener implements ActionListener {
		
		private int rowNum = 0;
		private int colNum = 0;
		
		ButtonListener(int row, int col) {
			this.rowNum = row;
			this.colNum = col;
		}

		public void actionPerformed(ActionEvent e) {
			cBtn[rowNum][colNum].setCardOpen(!cBtn[rowNum][colNum].isCardOpen());
		}

	}

}
