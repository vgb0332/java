package ch06;

public class ArrayParmTest {

	public static void main(String[] args) {

		int[] sourceArray = {2, 3, 1, 5, 10};
		int[] targetArray = new int[sourceArray.length];
		
		System.arraycopy(sourceArray, 0, targetArray, 0, sourceArray.length);
		
		printArray(sourceArray);
		
		changeArray(sourceArray);
		printArray(sourceArray);
		
	}
	
	static void changeArray(int[] sarray){
		
		for (int i=0; i<sarray.length; ++i){
			sarray[i]++;
		}
		
	}
	static void printArray(int[] sarray){
		
		for (int i=0; i<sarray.length; ++i){
			System.out.print(sarray[i]+" ");
		}
		System.out.println();
		
	}
	

}
