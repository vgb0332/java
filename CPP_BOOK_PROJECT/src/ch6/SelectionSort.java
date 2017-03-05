package ch6;

public class SelectionSort {
	public static void selectionSort(double[] list){
		for(int i = 0; i < list.length -1 ; i++){
			//Fine the minimum in the list[i...list.legnth-1]
			double currentMin = list[i];
			int currentMinIndex = i;
			
			for(int j = i + 1; j < list.length; j++){
				if(currentMin > list[j]){
					currentMin = list[j];
					currentMinIndex = j;
				}
			}
			
			// Swap list[i] with list[currentMinIndex] if necessary;
			if(currentMinIndex != i){
				list[currentMinIndex] = list[i];
				list[i] = currentMin;
			}
		}
	}
}
