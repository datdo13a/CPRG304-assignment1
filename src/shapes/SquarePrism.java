package shapes;

public class SquarePrism extends AbstractShape
{
	double side;
	
	//constructor
	public SquarePrism (double height, double side) {
		super(height);
		
		this.side = side;
	}

	//methods
	@Override
	public double calcBaseArea() {
		double sideSquared = side * side;
		
		double area = sideSquared;
		
		return area;
	}

	@Override
	public double calcVolume() {
		double sideSquared = side * side;
		
		return sideSquared * getHeight();
	}
	
	
}
