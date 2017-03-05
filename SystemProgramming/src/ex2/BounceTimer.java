package ex2;
import java.awt.*; import java.awt.event.*; import javax.swing.*;  
public class BounceTimer extends JPanel { 
	int x, y, dirX, dirY;    
	BounceTimer() {   
		x = 100;  
		y = 65;
		dirX = 1;
		dirY = 1;
		// 타이머 객체 생성  
		Timer t = new Timer(20, new TimerHandler()); 
		JButton btn = new JButton("Start");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource(); 
				if(b.getText().equals("Start")) {
					t.start();      
					b.setText("Stop");
					}     
				else {  
					t.stop();
					b.setText("Start");
					}
				}
			});
		add(btn); 
		}    

 class TimerHandler implements ActionListener {  
	 // 타이머 이벤트 처리기 
	 public void actionPerformed(ActionEvent e) {   
		 if(dirX == 1){
			x+=10;
			if(x>=200)
				dirX = -1;
		 }
		 else{
			 x-=25;
			 if(x<=0)
				 dirX = 1;
		 }
		 
		 if(dirY == 1){
			 y+=10;
			 if(y>=100)
				 dirY = -1;			 
		 }
		 
		 else{
			 y-=1;
			 if(y <= 0)
				 dirY = 1;
		 }
		 repaint();   
		 } 
	 }  
 public void paintComponent(Graphics g) {
	 super.paintComponent(g);
	 int r = (int)(Math.random()*256);
	 int t = (int)(Math.random()*256);
	 int b = (int)(Math.random()*256);
	 g.setColor(new Color(r,t,b)); 
	 g.fillOval(x, y, 100, 100); 
	 // 원 그리기 
	 }    
 public static void main(String [] args) {   
	 JFrame f = new JFrame("Bounce Timer"); 
	 f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	 f.setContentPane(new BounceTimer()); 
	 f.setSize(300 + 16, 200 + 16 + 24); 
	 f.setVisible(true); 
 }
}
