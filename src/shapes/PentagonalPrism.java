package shapes;

public class PentagonalPrism extends AbstractPrism
{
	// constructor
	public PentagonalPrism (double height, double side) {
		super(height, side);
	}

	// methods
	@Override
	public double calcBaseArea() {
		double side = getSide();
		double sideSquared = side * side;
		
		//convert degrees to radians
		double angleInRadians = Math.toRadians(54);
		double tanOf54 = Math.tan(angleInRadians);

		
		return (5 * sideSquared * tanOf54) / 4;
	}

	@Override
	public double calcVolume() {
		return calcBaseArea() * getHeight();
	}
}
