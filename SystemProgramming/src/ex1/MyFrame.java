package ex1;
import java.awt.*;

import javax.swing.*;



public class MyFrame extends JFrame{
	MyFrame(){
		setTitle("ContentPane과 JFrame 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(300,300);
		setVisible(true);
		Container contentPane = getContentPane();
		
	}
	
	public static void main(String[] args){
		MyFrame mf =  new MyFrame();
		
	}
}
