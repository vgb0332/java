package ch2;
import java.util.Scanner;

public class ComputeAverage {
	public static void main(String[] args){
		//Create a Scanner object
		Scanner input = new Scanner(System.in);
		
		//Promps the user to enter three numbers
		System.out.print("Enter three numbers: ");
		double number1 = input.nextDouble();
		double number2 = input.nextDouble();
		double number3 = input.nextDouble();
		
		//Compute Average
		double average = (number1+number2+number3)/3;
		
		//Display Result
		System.out.println("The average of " + number1 + " " + number2
				+ " " + number3 + " is " + average);
	}
}
