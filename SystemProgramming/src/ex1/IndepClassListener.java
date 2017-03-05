package ex1;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IndepClassListener extends JFrame {
	IndepClassListener(){
		setTitle("Action Listener Example");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JButton btn = new JButton("Action");
		btn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JButton b = (JButton)e.getSource();
				if(b.getText().equals("Action"))
					b.setText("액션!");
				else
					b.setText("Action");
				setTitle(b.getText());
			}
		});
		c.add(btn);
		setSize(250, 120);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new IndepClassListener();
	}
}
