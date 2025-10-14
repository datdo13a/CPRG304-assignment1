package shapes;
/*
 * Abstract shape class that defines height area and volume
 */
public abstract class AbstractPrism extends AbstractShape {
	private double side;
	
	// constructor
	public AbstractPrism (double height, double side) {
		super(height);
		this.side = side;
	}

	//getters and setters
	public double getSide() {
		return side;
	}
	public void setSide(double side) {
		this.side = side;
	}
	
	
	
	// abstract methods for area and volume
	public abstract double calcBaseArea();
	
	public abstract double calcVolume();
}
