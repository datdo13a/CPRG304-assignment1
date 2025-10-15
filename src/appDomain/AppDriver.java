package appDomain;
import shapes.*;
import utilities.SortingMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Application driver for sorting geometric shapes.
 * <p>
 * This application reads shape data from a file, sorts the shapes using various
 * sorting algorithms, and displays the results. Command line arguments control
 * which file to read, what property to compare (height/volume/area), and which
 * sorting algorithm to use.
 * </p>
 *
 * @author Team5
 * @version 1.0
 */
public class AppDriver
{
    /**
     * The file path for the shapes data file.
     */
    private static String fileName = "res/shapes1.txt";

    /**
     * The type of comparison to use: 'h' for height, 'v' for volume, 'a' for area.
     */
    private static char compareType = 'h';

    /**
     * The sorting algorithm to use: 'b' bubble, 's' selection, 'i' insertion,
     * 'm' merge, 'q' quick, 'z' heap.
     */
    private static char sortingType;

    /**
     * Main entry point of the application.
     * Parses command line arguments, reads shapes from file, sorts them,
     * and displays the results with timing information.
     *
     * @param args Command line arguments in format: -ffilename -t[h/v/a] -s[b/s/i/m/q/z]
     */
    public static void main( String[] args )
    {
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
        AbstractShape[] shapes = new AbstractShape[numberOfShapesInFile];

        int index = 0;

        while (input.hasNext())
        {
            String shapeName = input.next();
            double height =  Double.parseDouble(input.next());
            double secondValue = Double.parseDouble(input.next());

            AbstractShape shape = null;

            if (shapeName.equalsIgnoreCase("Cylinder")) {
                shape = new Cylinder(height, secondValue);
            }
            else if (shapeName.equalsIgnoreCase("Cone")) {
                shape = new Cone(height, secondValue);
            }
            else if (shapeName.equalsIgnoreCase("Pyramid")) {
                shape = new Pyramid(height, secondValue);
            }
            else if (shapeName.equalsIgnoreCase("SquarePrism")) {
                shape = new SquarePrism(height, secondValue);
            }
            else if (shapeName.equalsIgnoreCase("TriangularPrism")) {
                shape = new TriangularPrism(height, secondValue);
            }
            else if (shapeName.equalsIgnoreCase("PentagonalPrism")) {
                shape = new PentagonalPrism(height, secondValue);
            }
            else if (shapeName.equalsIgnoreCase("OctagonalPrism")) {
                shape = new OctagonalPrism(height, secondValue);
            }

            shapes[index] = shape;
            index++;
        }

        long start, stop;

        Comparator<AbstractShape> comparator = getComparator(compareType);

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
        double totalTime = (endTime - startTime) / 1_000_000.0;

        displayShapes(shapes);
        System.out.println("sort total time taken: " + totalTime + "ms, using sortingType: " + sortingType);
    }

    /**
     * Parses command line arguments to extract file name, comparison type, and sorting type.
     * Arguments are case-insensitive and order-independent.
     * If no path is specified for the file, automatically prepends "res/".
     *
     * @param args Array of command line arguments
     */
    private static void parseArgs(String[] args) {
        if (args.length < 3) {
            System.out.println("Not enough arguments.");
            return;
        }

        for (String arg : args) {
            if (arg.toLowerCase().startsWith("-f")) {
                fileName = arg.substring(2);
                if (!fileName.contains("/") && !fileName.contains("\\")) {
                    fileName = "res/" + fileName;
                }
            }
            else if (arg.toLowerCase().startsWith("-t")) {
                compareType = arg.charAt(2);
            }
            else if (arg.toLowerCase().startsWith("-s")) {
                sortingType = arg.charAt(2);
            }
        }
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Error: File name not provided.");
            System.exit(1);
        }
    }

    /**
     * Returns the appropriate comparator based on the comparison type.
     *
     * @param compareType The type of comparison: 'v'/'V' for volume,
     *                    'a'/'A' for area, 'h'/'H' for height
     * @return Comparator for comparing AbstractShape objects
     */
    private static Comparator<AbstractShape> getComparator(char compareType) {
        if (compareType == 'v' || compareType == 'V') {
            return new VolumeCompare();
        }
        else if (compareType == 'a' || compareType == 'A') {
            return new AreaCompare();
        }
        else {
            return Comparator.naturalOrder();
        }
    }

    /**
     * Displays the sorted shapes to the console.
     * Shows the first shape, every 1000th shape, and the last shape
     * along with their comparison values.
     *
     * @param shapes Array of sorted AbstractShape objects
     */
    private static void displayShapes(AbstractShape[] shapes)
    {
        String comparisonString = "";
        if (compareType == 'v' || compareType == 'V') {
            comparisonString = "Volume";
        }
        else if (compareType == 'a' || compareType == 'A') {
            comparisonString = "Area";
        }
        else {
            comparisonString = "Height";
        }
        if (shapes == null || shapes.length == 0) {
            System.out.println("No shapes found to display.");
            return;
        }

        System.out.println("First shape: " + shapes[0] + " " + comparisonString + ": " + getValueToDisplay(shapes[0], compareType));

        for (int i = 1000; i < shapes.length; i += 1000) {
            System.out.println("Shape #" + i + ": " + shapes[i] + " " + comparisonString + ": " + getValueToDisplay(shapes[i], compareType));
        }

        System.out.println("Last shape: " + shapes[shapes.length - 1] + " " + comparisonString + ": " + getValueToDisplay(shapes[shapes.length - 1], compareType));
    }

    /**
     * Helper method to extract the appropriate value from a shape based on comparison type.
     *
     * @param shape The AbstractShape to get the value from
     * @param compareType The type of value to retrieve: 'v'/'V' for volume,
     *                    'a'/'A' for area, or 'h'/'H' for height
     * @return The numerical value for the specified property
     */
    private static double getValueToDisplay(AbstractShape shape, char compareType) {
        if (compareType == 'v' || compareType == 'V') {
            return shape.calcVolume();
        }
        else if (compareType == 'a' || compareType == 'A') {
            return shape.calcBaseArea();
        }
        else {
            return shape.getHeight();
        }
    }
}