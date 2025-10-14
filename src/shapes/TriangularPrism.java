package shapes;

public class TriangularPrism extends AbstractPrism
{
	//constructor
	public TriangularPrism (double height, double side) {
		super(height, side);
	}
	
	//methods
	@Override
	public double calcBaseArea() {
		double side = getSide();
		double sideSquared = side * side;
		double squareRoot3 = Math.sqrt(3);
		
		return (sideSquared * squareRoot3) / 4;
	}

	@Override
	public double calcVolume() {
		
		return calcBaseArea() * getHeight();
	}
	

}
