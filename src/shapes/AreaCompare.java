package shapes;

import java.util.Comparator;

/**
 * Comparator to compare two shapes and their AREA
 */
public class AreaCompare implements Comparator<AbstractShape>{

	public int compare(AbstractShape shape1, AbstractShape shape2) {
		if (shape1.calcBaseArea() > shape2.calcBaseArea()) {
			return 1;
		}
		if (shape1.calcBaseArea() < shape2.calcBaseArea()) {
			return -1;
		}
		else //shape1.calcBaseArea() == shape2.calcBaseArea() 
		{
			return 0;
		}
	}
}
