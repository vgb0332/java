package ch5;

public class ParamPassingTest {

	public static void main(String[] args) {

		String s1 = "Hello";
		String s2 = "World";
		
		System.out.println(s1+" "+s2+"!!");
		
		swap(s1, s2);
		System.out.println(s1+" "+s2+"!!");
		
	}
	static void swap (String p1, String p2){
		String t = p1;
		p1 = p2;
		p2 = t;
		
	}

}
