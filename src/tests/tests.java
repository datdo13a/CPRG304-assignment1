package tests;

import shapes.*;
import utilities.SortingMethods;

import java.util.Arrays;
import java.util.Comparator;

/**
 * sorting algos test
 *  - checks reference (ascending),
 *  - checks reversed reference (descending),
 *  - or is incorrect.
 * and prints the first and last class in the sorted array.
 */
public class tests {
    public static void main(String[] args) {
        AbstractShape[] original = new AbstractShape[] {
        	new Cone(2.5, 1.2),
            new Cylinder(3.0, 1.0),
            new Pyramid(1.5, 2.0),
            new TriangularPrism(2.0, 1.0),
            new SquarePrism(4.0, 0.9)
        };

        Comparator<AbstractShape> comp = Comparator.comparingDouble(s -> s.calcVolume());

        AbstractShape[] reference = Arrays.copyOf(original, original.length);
        Arrays.sort(reference, comp);
        double[] refVals = extractVals(reference);

        double[] revRef = reversed(refVals);

        // yes
        testAlg("bubble", original, comp, refVals, revRef, (arr, c) -> SortingMethods.bubbleSort(arr, c));
        testAlg("selection", original, comp, refVals, revRef, (arr, c) -> SortingMethods.selectionSort(arr, c));
        testAlg("insertion", original, comp, refVals, revRef, (arr, c) -> SortingMethods.insertionSort(arr, c));
        testAlg("merge", original, comp, refVals, revRef, (arr, c) -> SortingMethods.mergeSort(arr, c, 0, arr.length - 1));
        testAlg("quick", original, comp, refVals, revRef, (arr, c) -> SortingMethods.quickSort(arr, c, 0, arr.length - 1));
        testAlg("heap", original, comp, refVals, revRef, (arr, c) -> SortingMethods.heapSort(arr, c));
    }

    private static void testAlg(String name, AbstractShape[] orig, Comparator<AbstractShape> comp, double[] ref, double[] revRef, SortInvoker inv) {
        AbstractShape[] arr = Arrays.copyOf(orig, orig.length);
        try {
            inv.sort(arr, comp);
            double[] got = extractVals(arr);

            if (Arrays.equals(ref, got)) {
                System.out.println(name + " -> MATCH (ascending)");
            } else if (Arrays.equals(revRef, got)) {
                System.out.println(name + " -> MATCH (descending)");
            } else {
                System.out.println(name + " -> WRONG result");
                System.out.println("  expected: " + Arrays.toString(ref));
                System.out.println("  reversed: " + Arrays.toString(revRef));
                System.out.println("  got     : " + Arrays.toString(got));
            }

            if (arr.length > 0) {
                System.out.println("  First class: " + arr[0].getClass().getSimpleName());
                System.out.println("  Last class : " + arr[arr.length - 1].getClass().getSimpleName() + "\n");
            }

        } catch (Throwable t) {
            System.out.println(name + " -> EXCEPTION: " + t);
            t.printStackTrace(System.out);
        }
    }

    private static double[] extractVals(AbstractShape[] arr) {
        double[] vals = new double[arr.length];
        for (int i = 0; i < arr.length; i++) vals[i] = arr[i].calcVolume();
        return vals;
    }

    private static double[] reversed(double[] a) {
        double[] r = new double[a.length];
        for (int i = 0; i < a.length; i++) r[i] = a[a.length - 1 - i];
        return r;
    }

    // for the sorting thingy
    private interface SortInvoker { void sort(AbstractShape[] arr, Comparator<AbstractShape> c); }
}
