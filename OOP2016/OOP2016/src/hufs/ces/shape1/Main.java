package hufs.ces.shape1;

import java.util.*;


/** Part of a main program using Shape objects */
public class Main {

	static List<Shape> allShapes;	// created in a Constructor, not shown

	/** Iterate over all the Shapes, getting their areas */
	public static double totalAreas() {
		double total = 0.0;
		/*
		Iterator<Shape> it = allShapes.iterator();
		while (it.hasNext()) {
			Shape s = it.next();
			System.out.println(s.toString());
			total += s.computeArea();
		}
		*/
		for (Shape s:allShapes){
			System.out.println(s.toString());
			total += s.computeArea();
		}
		return total;
	}
	public static void main(String[] argv) {
		allShapes = new ArrayList<Shape>();
		allShapes.add(new Rectangle(2,3));
		allShapes.add(new Circle(5));
		allShapes.add(new Rectangle(5.0,5.0));
		allShapes.add(new Circle(8.0));
		allShapes.add(new Rectangle(9,9));
		allShapes.add(new Circle(0));
		allShapes.add(new Rectangle(0,0));
		
		double tot = totalAreas();
		System.out.println("Total Area = " + tot);
		
	}
}
