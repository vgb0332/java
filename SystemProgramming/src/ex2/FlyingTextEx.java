package ex2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class FlyingTextEx extends JFrame{
	JPanel contentPane = new JPanel();
	JLabel la = new JLabel("HELLO");
	int xP = 10;
	int yP = 10;
	Point currentLoc;
	
	FlyingTextEx(){
		super("quiz");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.addKeyListener(new MyKeyListener());
		contentPane.addMouseListener(new MyMouseListener());
		
		la.setLocation(50,50);
		la.setSize(100, 20);
		contentPane.add(la);
		
		setSize(200, 200);
		setVisible(true);
		
		contentPane.requestFocus();
	}
	
	class MyMouseListener implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			int x = e.getX();
			int y = e.getY();
			la.setLocation(x,y);
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	class MyKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			System.out.println(e.getKeyChar());
			int keyCode = e.getKeyCode();
			switch(keyCode){
			case KeyEvent.VK_UP:
				currentLoc = la.getLocation();
				//System.out.println("HI");
				la.setLocation(currentLoc.x, currentLoc.y-10);
				break;
			case KeyEvent.VK_DOWN:
				currentLoc = la.getLocation();
				la.setLocation(currentLoc.x, currentLoc.y+10);
				break;
			case KeyEvent.VK_RIGHT:
				currentLoc = la.getLocation();
				la.setLocation(currentLoc.x+10, currentLoc.y);
				break;
			case KeyEvent.VK_LEFT:
				currentLoc = la.getLocation();
				la.setLocation(currentLoc.x-10, currentLoc.y);
				break;	
			}
			repaint();
			
		}
	}
	
	public static void main(String[] args){
		new FlyingTextEx();
	}

}
