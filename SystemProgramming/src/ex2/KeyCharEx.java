package ex2;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class KeyCharEx extends JFrame {
	JLabel la = new JLabel("<Enter>Ű�� ������ �ٲ�ϴ�.");
	
	KeyCharEx(){
		super("KeyListener�� ���� Ű �Է� ����");
		
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
