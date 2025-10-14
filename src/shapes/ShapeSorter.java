package shapes;
import java.util.Arrays;
import java.util.Comparator;


public class ShapeSorter {
    // SELECTION SORT
    public static <T> void selectionSort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length - 1; i++ ) {
            int indexOfMax = i;

            for (int j = i;  j < array.length; j++) {
                if (comparator.compare(array[j], array[indexOfMax]) > 0) {
                    indexOfMax = j;
                }
            }
            swap(array, i, indexOfMax);
        }
    }

    // BUBBLE SORT
    public static <T> void bubbleSort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length - 1; i++ ) {
            for (int j = 0; j < array.length - 1 - i; j++ ) {
                if (comparator.compare(array[j], array[j+1]) > 0) {
                    swap(array, j, j+1);
                }
            }
        }
    }

    // INSERTION SORT
    public static <T> void insertionSort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length - 1; i++ ) {
            int j = i;
            while (j > 0 && comparator.compare(array[j], array[j-1]) > 0) {
                swap(array, j, j-1);
                j--;
            }
        }
    }

    // MERGE SORT
    public static <T> void mergeSort(T[] array, Comparator<T> comparator, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, comparator, left, middle);
            mergeSort(array, comparator, middle + 1, right);
            merge(array, comparator, left, middle, right);
        }
    }

    // MERGE
    private static <T> void merge(T[] array, Comparator<T> comparator, int left, int middle, int right) {
        int size1 = middle - left + 1;
        int size2 = right - middle;

        T[] leftArray = Arrays.copyOfRange(array, left, left + size1);
        T[] rightArray = Arrays.copyOfRange(array, left + size1, right + size2);

        int i = 0, j = 0, k = left;

        while (i < size1 && j < size2) {
            if (comparator.compare(leftArray[i], rightArray[j]) >= 0) {
                array[k] = leftArray[i];
                i++;
            }
            else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < size1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < size2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // QUICK SORT
    public static <T> void quickSort(T[] array, Comparator<T> comparator, int low, int high) {
        if (low < high) {
            int par = partition(array, comparator, low, high);

            quickSort(array, comparator, low, par - 1);
            quickSort(array, comparator, par + 1, high);
        }
    }

    // PARTITION
    private static <T> int partition (T[] array, Comparator<T> comparator, int low, int high) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) >= 0) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, high);
        return i + 1;
    }

    // BUCKET SORT
    private static <T> void bucketSort(T[] array, Comparator<T> comparator, int low, int high) {
        
    }

    // SWAP
    private static <T> void swap (T[] array, int a, int b) {
        T temp = array[a];
        array[a] = array[b];
        array[b] = temp;

    }
}
