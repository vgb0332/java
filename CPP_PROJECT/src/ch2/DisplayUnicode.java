package ch2;
import javax.swing.JOptionPane;

public class DisplayUnicode {
	public static void main(String[] args){
		JOptionPane.showMessageDialog(null, 
				"\u6822\u8FCE \u03b1 \u03b2 \u03b3",
				"\u6822\u8FCE Welcome",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
