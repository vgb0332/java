package ex1;
import javax.swing.*;
import java.awt.*;

public class ContentPaneEx extends JFrame {
	ContentPaneEx(){
		setTitle("ContentPane과 JFrame 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setLayout(new FlowLayout(FlowLayout.LEFT,30,40));
		
		contentPane.add(new JButton("add"));
		contentPane.add(new JButton("sub"));
		contentPane.add(new JButton("mul"));
		contentPane.add(new JButton("div"));
		contentPane.add(new JButton("Calculate"));
		
		setSize(300,200);
		setVisible(true);
		//System.exit(0);
	}
	public static void main(String[] args){
		new ContentPaneEx();
	}
}
