package appDomain;
import shapes.*;
import utilities.SortingMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

/**
 * <p>
 * This application driver code is designed to be used as a basis for the
 * Complexity and Sorting assignment that will be developed in the CPRG304 
 * F2025 class at SAIT. The implementors of this applications will be required
 * to add all the correct functionality.
 * </p>
 */
public class AppDriver
{
	/**
	 *  The main method is the entry point of the application.
	 *  
	 *  @param args The input to control the execution of the application.
	 */

    // static variables
    private static String fileName = "res/shapes1.txt"; //the file path: -f
    private static char compareType = 'h'; // the type to compare (height, volume, area): h, v, a
    private static char sortingType; // the sorting type to be used: b, s, i, m, q, z

	public static void main( String[] args )
	{
		// TODO Auto-generated method stub

		// refer to demo00 BasicFileIO.java for a simple example on how to
		// read data from a text file
        // TODO - create way for user to choose which file to read

        parseArgs(args);

        File inputFile = new File(fileName);
        Scanner input = null;

        try
        {
            input = new Scanner ( inputFile );
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        int numberOfShapesInFile = input.nextInt();
        AbstractShape[] shapes = new AbstractShape[numberOfShapesInFile]; // Creating an ARRAY and we need to know the size of the array beforehand

        int index = 0;

        while (input.hasNext())
        {
            String shapeName = input.next(); // Getting the shape name ie. Cone, Pyramid, Prism etc
            double height =  Double.parseDouble(input.next());
            double secondValue = Double.parseDouble(input.next()); // This will either the side or radius depending on shape

            AbstractShape shape = null;

            if (shapeName.equalsIgnoreCase("Cylinder")) {
                shape = new Cylinder(height, secondValue);  // secondValue is radius
            }
            else if (shapeName.equalsIgnoreCase("Cone")) {
                shape = new Cone(height, secondValue);  // secondValue is radius
            }
            else if (shapeName.equalsIgnoreCase("Pyramid")) {
                shape = new Pyramid(height, secondValue);  // secondValue is side
            }
            else if (shapeName.equalsIgnoreCase("SquarePrism")) {
                shape = new SquarePrism(height, secondValue);  // secondValue is side
            }
            else if (shapeName.equalsIgnoreCase("TriangularPrism")) {
                shape = new TriangularPrism(height, secondValue);  // secondValue is side
            }
            else if (shapeName.equalsIgnoreCase("PentagonalPrism")) {
                shape = new PentagonalPrism(height, secondValue);  // secondValue is side
            }
            else if (shapeName.equalsIgnoreCase("OctagonalPrism")) {
                shape = new OctagonalPrism(height, secondValue);  // secondValue is side
            }

            shapes[index] = shape; // we now add the shape we just created to the array
            index++; // move to next index in the array list for next shape to be added
        }

		// refer to demo01 Test.java for an example on how to parse command
		// line arguments and benchmarking tests
        long start, stop;

        // Get comparator based on compareType
        Comparator<AbstractShape> comparator = getComparator(compareType);

        // start the timer
       	long startTime = System.nanoTime();

		if (sortingType == 'b') {
    		SortingMethods.bubbleSort(shapes, comparator);
		}
		else if (sortingType == 's') {
    		SortingMethods.selectionSort(shapes, comparator);
		} 
		else if (sortingType == 'i') {
    		SortingMethods.insertionSort(shapes, comparator);
		} 
		else if (sortingType == 'm') {
    		SortingMethods.mergeSort(shapes, comparator, 0, shapes.length - 1);
		} 
		else if (sortingType == 'q') {
    		SortingMethods.quickSort(shapes, comparator, 0, shapes.length - 1);
		} 
		else if (sortingType == 'z') {
    		SortingMethods.heapSort(shapes, comparator);
		} 
		else {
    		System.out.println("Invalid sorting type entered.");
    	return;
		}

		long endTime = System.nanoTime();
		double totalTime = (endTime - startTime) / 1_000_000.0; // convert to ms


		// refer to demo02 Student.java for comparable implementation, and
		// NameCompare.java or GradeCompare for comparator implementations

		// refer to demo02 KittySort.java on how to use a custom sorting
		// algorithm on a list of comparables to sort using either the
		// natural order (comparable) or other orders (comparators)

		displayShapes(shapes);
	}

    private static void parseArgs(String[] args) {
        if (args.length < 3) {
            System.out.println("Not enough arguments.");
            return;
        }

        for (String arg : args) {
            // Check if argument starts with -f or -F
            if (arg.toLowerCase().startsWith("-f")) {
                // Extract filename (everything after "-f")
                fileName = arg.substring(2);  // Gets string starting at index 2
            }
            // Check if argument starts with -t or -T
            else if (arg.toLowerCase().startsWith("-t")) {
                // Extract compare type (character after "-t")
                compareType = arg.charAt(2);  // Gets character at index 2
            }
            // Check if argument starts with -s or -S
            else if (arg.toLowerCase().startsWith("-s")) {
                // Extract sort type (character after "-s")
                sortingType = arg.charAt(2);
            }
        }
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Error: File name not provided.");
            System.exit(1);
        }
    }

    // method to determine the user's input to know what to compare each shape with
    private static Comparator<AbstractShape> getComparator(char compareType) {
        if (compareType == 'v' || compareType == 'V') {
            return new VolumeCompare();
        }
        else if (compareType == 'a' || compareType == 'A') {
            return new AreaCompare();
        }
        else {  // Default to height (h or H)
            return Comparator.naturalOrder();  // Uses compareTo from AbstractShape
        }
    }

    // need to implement a way to print out each shape into the console
    private static void displayShapes(AbstractShape[] shapes) 
	{
    	if (shapes == null || shapes.length == 0) {
	        System.out.println("No shapes found to display.");
	        return;
	    }
	
	    // print first shape
	    System.out.println("First shape: " + shapes[0]);
	
	    // print every 1000th shape
	    for (int i = 1000; i < shapes.length; i += 1000) {
	        System.out.println("Shape #" + i + ": " + shapes[i]);
	    }
	
	    // print last shape
	    System.out.println("Last shape: " + shapes[shapes.length - 1]);
	}
}


