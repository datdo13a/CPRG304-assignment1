package shapes;

public class Cone extends AbstractShape
{
	private double radius;
	

	/*
	 * Constructor
	 */
	public Cone(double height, double radius) {
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
		
		double volume = (1/3) * pi * radiusSquared * getHeight();
		
		return volume;
	}

}
