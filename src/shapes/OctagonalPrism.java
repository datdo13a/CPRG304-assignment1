package shapes;

public class OctagonalPrism extends AbstractShape
{
	double side;
	
	/*
	 * Constructor
	 */
	public OctagonalPrism (double height, double side) {
		super(height);
		
		this.side = side;
	}

	/*
	 * methods
	 */
	@Override
	public double calcBaseArea() {
		double sideSquared = side * side;
		double squareRootOfTwo = Math.sqrt(2);
		
		double area = 2 * (1 + squareRootOfTwo) * sideSquared;
		
		return area;
	}

	@Override
	public double calcVolume() {
		return calcBaseArea() * getHeight();
	}
}
