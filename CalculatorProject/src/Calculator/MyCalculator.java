package Calculator;

import javax.swing.*;
//import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyCalculator extends JFrame{
	private Container c;
	private JTextField display; //display area
	private JPanel p1;			//Panel 1
	private JPanel p2;			//Panel 2
	private JPanel p3;			//Panel 3
	
	//display Font, Color and displayColor setting
	public Font displayFont = new Font("휴먼편지체", Font.BOLD, 60);
	public Color backgroundColor = new Color(153,153,255);
	public Color displayColor = new Color(255,255,153);
	
	//buttons and their names setting
	private JButton[] operationBtns = new JButton[6]; 				// C, X, /, -, +, = order
	private String[] btnName = {"C", "X", "/", "-", "+", "="};
	private JButton[] numberBtns = new JButton[10];					// 7,8,9,4,5,6,1,2,3 order
	private String[] btnNum = {"7","8","9","4","5","6","1","2","3"};
	
	//current, previous, output value
	int cur = 0;
	int pre = 0;
	int out = 0; 
	String op = "";		   //operator
	boolean isInit = false;	   //checking for initialization
	
	public static void main(String[] args){
		MyCalculator myCalculator = new MyCalculator();
		myCalculator.setVisible(true);
	}
	
	public MyCalculator(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("정용석의 계산기");
		setSize(500,650);
		setLocation(200,50);
		c = getContentPane();
		c.setBackground(backgroundColor);
		c.setLayout(null);
		
		//display Area
		setDisplayArea();
		
		//Panel 1 Area
		setPanel1Area();	
		
		//Panel 2 Area
		setPanel2Area();
		
		//Panel 3 Area
		setPanel3Area();
	}

	private void setPanel3Area() {
		//creating and setting panel p3
		p3 = new JPanel();
		p3.setBounds(360, 220, 85, 350);
		p3.setBackground(new Color(153,153,255));
		p3.setLayout(new GridLayout(2, 1, 0, 20));
		
		//adding buttons + and -
		for(int i = 4; i < 6; i++){
			operationBtns[i] = new JButton(btnName[i]);
			operationBtns[i].setFont(displayFont);
			operationBtns[i].addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton btn = (JButton)e.getSource();
					String text = btn.getText();
					if(text.equals("+")){
						if(isInit == false){op = "NO OPERANDS";}//nothing happens if it's not initialized
						else{
							pre = cur;
							
							out = cur;
							cur = 0;
							op = "+";
						}
					}
					else if(text.equals("=")){
						switch(op){
						case "+":
							cur = pre + cur;	
							op = "";
							break;
						case "-":
							cur = pre - cur;
							op = "";
							break;
						case "X" :
							cur = pre * cur;
							op = "";
							break;
						case "/" :
							cur = pre / cur;
							op = "";
							break;
						default:
							op = "";
						
						}
						out = cur;
					}
					display.setText(String.valueOf(out));
					//System.out.println("press [" + btn.getText() + "] pre = " + pre + " cur = " + cur + " out = "+ out + " op = " + op);
				}
			});
			p3.add(operationBtns[i]);
		}
		
		c.add(p3);
		
	}
	private void setPanel2Area() {
		//creating and setting panel p2
		p2 = new JPanel();
		p2.setBounds(45, 220, 295, 350);
		p2.setBackground(backgroundColor);
		p2.setLayout(null);
		
		//Creating and setting area for Number Buttons: 1 ~ 9
		JPanel btnArea = new JPanel();
		btnArea.setBackground(backgroundColor);
		btnArea.setSize(295,270);
		btnArea.setLayout(new GridLayout(3,3,20,20));
			//ADD BUTTONS: 1~9
		for(int i = 0; i < 9; i ++){
			numberBtns[i] = new JButton(btnNum[i]);
			numberBtns[i].setFont(displayFont);
			numberBtns[i].addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					JButton Numbtn = (JButton)e.getSource();
					
					//System.out.println(Numbtn.getText() + "Clicked");
					
					//set the current number to the value of numberBtn Clicked
					isInit = true;
					int temp = Integer.parseInt(Numbtn.getText());
					cur = 10*cur + temp;
					out = cur;
					display.setText(String.valueOf(out));
					//System.out.println("press [" + Numbtn.getText() + "] pre = " + pre + " cur = " + cur + " out = "+ out + " op = " + op);
				}
			});
			btnArea.add(numberBtns[i]);
		}		
		p2.add(btnArea);
		
		////////////////////////////////////////
		//Area Number Button: 0
		JButton btnZero = new JButton("0");
		btnZero.setBounds(0, 280, 295, 70);
		btnZero.setFont(displayFont);
		btnZero.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton Numbtn = (JButton)e.getSource();
				isInit = true;
				int temp = Integer.parseInt(Numbtn.getText());
				cur = 10*cur + temp;
				display.setText(String.valueOf(cur));
				//System.out.println("press [" + Numbtn.getText() + "] pre = " + pre + " cur = " + cur + " out = "+ out + " op = " + op);
			}
		});
		p2.add(btnZero);
		c.add(p2);
		
	}
	private void setPanel1Area() {
		//Creating and setting panel p1
		p1 = new JPanel();
		p1.setBounds(45, 120, 400, 80);
		p1.setBackground(backgroundColor);
		p1.setLayout(new GridLayout(1,4,20,0));
		
		//creating and adding buttons 'C / X -'
		for(int i = 0; i < 4; i++){
			operationBtns[i] = new JButton(btnName[i]);
			operationBtns[i].setFont(displayFont);
			operationBtns[i].addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					//Get the source of the button clicked
					JButton operBtn =  (JButton)e.getSource();
					
					//Get the text from the source
					String text = operBtn.getText();
					if(text.equals("C")) {
						pre = 0;
						cur = 0;
						out = 0;
						op = "";
						isInit = false;
					}
					else {
						if(isInit == false){op = "NO OPERANDS";}
						else{
							pre = cur; 
							out = cur; 
							cur = 0;   
							if(text.equals("/")) op = "/";
							else if(text.equals("X")) op = "X";
							else if(text.equals("-")) op = "-";
						}
					}
					display.setText(String.valueOf(out));
					//System.out.println("press [" + operBtn.getText() + "] pre = " + pre + " cur = " + cur + " out = "+ out + " op = " + op);
				}				
			});
			p1.add(operationBtns[i]);
		}
		c.add(p1);
	}
	private void setDisplayArea() {
		display = new JTextField();
		display.setBounds(45, 20, 400, 80);
		display.setBackground(displayColor);
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setText("WELCOME!");
		display.setFont(displayFont);
		//display.addDocumentListener(new DocumentListener());
		c.add(display);
		
	}

}
