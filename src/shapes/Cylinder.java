package shapes;

public class Cylinder extends AbstractShape
{
	private double radius;

	/*
	 * Constructor
	 */
	public Cylinder (double height, double radius) {
		super(height);
		
		this.radius = radius;
	}

	/*
	 * methods
	 */
	@Override
	public double calcBaseArea() {
		double pi = 3.14;
		double radiusSquared = radius * radius;
		
		double area = pi * radiusSquared;
		
		return area;
	}

	@Override
	public double calcVolume() {
		double pi = 3.14;
		double radiusSquared = radius * radius;
		
		double volume = pi * radiusSquared * getHeight();

		return volume;
	}
}