package shapes;

import java.util.Comparator;

public class VolumeCompare implements Comparator<AbstractShape>{
	
	public int compare(AbstractShape shape1, AbstractShape shape2) {
		if (shape1.calcVolume() > shape2.calcVolume()) {
			return 1;
		}
		if (shape1.calcVolume() < shape2.calcVolume()) {
			return -1;
		}
		else // shape1.calcVolume() == shape2.calcVolume()
		{
			return 0;
		}
	}

}
