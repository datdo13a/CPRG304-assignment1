package shapes;
/*
 * Abstract shape class that defines height area and volume
 */
public abstract class AbstractShape implements Comparable<AbstractShape> {
	private double height;
	
	// constructor
	public AbstractShape (double height) {
		this.height = height;
	}

	//getters and setters
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	// abstract methods for area and volume
	public abstract double calcBaseArea();
	
	public abstract double calcVolume();

    //toString
    @Override
    public String toString() {
        return this.getClass().getName();
    }
	
	// Comparable, comparing between two shapes and their HEIGHT
    @Override
	public int compareTo(AbstractShape other) {
		return Double.compare(this.getHeight(), other.getHeight());
	}
}
