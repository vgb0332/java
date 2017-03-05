package ch08;

public class Circle {

	private double radius;
	private double x;
	private double y;
	
	public Circle(double radius, double x, double y) {
		this.radius = radius;
		this.x = x;
		this.y = y;
	}
	public Circle(double radius) {
		this(radius, 0, 0);
	}
	public Circle(){
		this(1, 0, 0);
	}
	
	public double getArea(){
		return Math.PI*radius*radius;
	}
	
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
}
