package shapes;

public class Pyramid extends AbstractShape
{
	double side;
	
	// constructor
	public Pyramid (double height, double side) {
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
		
		
		return (1.0/3.0) * sideSquared * getHeight();
	}
	

}
