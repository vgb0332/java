package ex1;
import java.awt.*;
import javax.swing.*;

public class NullLayoutEx extends JFrame {
	NullLayoutEx(){
		setTitle("null Layout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		JLabel la = new JLabel("Hello, Press Buttons");
		la.setLocation(130, 50);
		la.setSize(200, 20);
		contentPane.add(la);
		
		for(int i = 1; i <= 9; i++){
			JButton btn = new JButton(""+i);
			btn.setSize(100,40);
			btn.setLocation(20+i*20,30+i*20);
			contentPane.add(btn);
		}
		setSize(300, 200);
		setVisible(true);
		
	}
	public static void main(String[] args){
		new NullLayoutEx();
	}
}
