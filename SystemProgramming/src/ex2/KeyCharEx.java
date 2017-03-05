package ex2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class KeyCharEx extends JFrame {
	JLabel la = new JLabel("<Enter>키로 배경색이 바뀝니다.");
	
	KeyCharEx(){
		super("KeyListener의 문자 키 입력 예제");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(la);
		//c.addKeyListener(new MyKeyListener());
		setSize(250,150);
		setVisible(true);
		c.requestFocus();
	}
	
	class MyKeyListener {
		public void keyPressed(KeyEvent e){
			
		}
	}
}
