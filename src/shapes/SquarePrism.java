package shapes;

public class SquarePrism extends AbstractPrism
{
	//constructor
	public SquarePrism (double height, double side) {
		super(height, side);
	}

	//methods
	@Override
	public double calcBaseArea() {
		double side = getSide();
		double sideSquared = side * side;
		
		double area = sideSquared;
		
		return area;
	}

	@Override
	public double calcVolume() {
		double side = getSide();
		double sideSquared = side * side;
		
		return sideSquared * getHeight();
	}
	
	
}
