package hufs.ces.shape2;

public class Geom implements Shape {

	private int x;
	private int y;
	
	@Override
	public double getArea() {
		return 0;
	}

	@Override
	public double getVolume() {
		return 0;
	}

	@Override
	public String getName() {
		return null;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
