package quiz;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PopQuiz extends JFrame{
	PopQuiz(){
		setTitle("Action Listener Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		JButton btn = new JButton("Action");
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JButton b = (JButton)e.getSource();				
				if(b.getText().equals("Action")){
					b.setText("액션!");
					Point currentLoc = b.getLocation();
					b.setLocation(currentLoc.x+20, currentLoc.y+20);
				}
				else{
					b.setText("Action");
					Point currentLoc = b.getLocation();
					b.setLocation(currentLoc.x+20, currentLoc.y+20);
				}
				setTitle(b.getText());
			}
		});
		btn.setSize(100,50);
		btn.setLocation(20,30);
		c.add(btn);
		setSize(300, 300);
		
		setVisible(true);
	}
	
	public static void main(String[] args){
		new PopQuiz();
	}

}
