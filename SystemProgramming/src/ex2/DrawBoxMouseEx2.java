package ex2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class DrawBoxMouseEx2 extends JPanel {
	int x, y ,x1,x2, y1,y2, w, h;
	
	public static void main(String[] args){
		JFrame f = new JFrame("Draw Box Mouse 2");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setContentPane(new DrawBoxMouseEx2());
		f.setSize(300, 300);
		f.setVisible(true);
	}
	
	

	DrawBoxMouseEx2(){
		x = y = x1 = x2 = y1 = y2 = w = h = 0;
		
		MyMouseListener listener = new MyMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}
	public void setStartPoint(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setEndPoint(int x, int y){
		this.x2 = x;
		this.y2 = y;
		
		if(x1 > x2) x= x1;
		else x = x2;
		
		if(y1 > y2) y = y1;
		else y = y2;
		
		w = Math.abs(x2-x1);
		h = Math.abs(y2-y1);
	}
	
	class MyMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e){
			setStartPoint(e.getX(), e.getY());
		}
		public void mouseDragged(MouseEvent e){
			setEndPoint(e.getX(),e.getY());
			repaint();
		}
		public void mouseReleased(MouseEvent e){
			setEndPoint(e.getX(), e.getY());
			repaint();
		}
	}
	
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.drawRect(x, y, w, h);
	}
}
