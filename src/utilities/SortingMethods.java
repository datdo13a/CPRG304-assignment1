package utilities;
import java.util.Arrays;
import java.util.Comparator;


public class SortingMethods {
    // SELECTION SORT
    /**
     * Selection sort finds the largest 
     * item going through the list and puts it into a sorted
     * section, it repeats this process until the whole array is sorted
     */
    public static <T> void selectionSort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length - 1; i++ ) {
            int indexOfMax = i;//getting the first value to start on

            for (int j = i;  j < array.length; j++) {
                if (comparator.compare(array[j], array[indexOfMax]) > 0) {
                    indexOfMax = j;//finds the next largest item in the array
                }
            }
            swap(array, i, indexOfMax);//switches the next largest item into the sorted half of the array
        }
    }

    // BUBBLE SORT
    /**
     * bubble sort goes through the array and looks at 
     * every pair of items and switches those items around
     * into proper order. it goes through the array multiple 
     * times until the array is sorted.
     * this allows for the lowest value to find its way to the end 
     * each iteration
     */
    public static <T> void bubbleSort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length - 1; i++ ) {
            for (int j = 0; j < array.length - 1 - i; j++ ) {
                if (comparator.compare(array[j], array[j+1]) > 0) {
                    swap(array, j, j+1);//if the value after the item at j is greater, they switch
                }
            }
        }
    }

    // INSERTION SORT
    /**
     * goes through the array and inserts any unsorted values into 
     * the an unsorted partition on the left 
     */
    public static <T> void insertionSort(T[] array, Comparator<T> comparator) {
        for (int i = 1; i < array.length; i++ ) {
            int j = i;
            while (j > 0 && comparator.compare(array[j], array[j-1]) > 0) {
                swap(array, j, j-1);//if the next item in the list is in the wrong order, 
                                    //insert the item into the sorted partition by going backwards
                j--;
            }
        }
    }

    // MERGE SORT
    /**
     * divides the array into subarrays and 
     * then merges them back together 
     * in their correct order
     */
    public static <T> void mergeSort(T[] array, Comparator<T> comparator, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, comparator, left, middle);
            mergeSort(array, comparator, middle + 1, right);
            merge(array, comparator, left, middle, right);
        }
    }

    //MERGE SORT
    private static <T> void merge(T[] array, Comparator<T> comparator, int left, int middle, int right) {
        int size1 = middle - left + 1;
        int size2 = right - middle;
        //creates two subarrays 
        T[] leftArray = Arrays.copyOfRange(array, left, left + size1); 
        T[] rightArray = Arrays.copyOfRange(array, left + size1, right + size2);

        int i = 0, j = 0, k = left;

        while (i < size1 && j < size2) {
            if (comparator.compare(leftArray[i], rightArray[j]) >= 0) {
                array[k] = leftArray[i];//switches the 
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
    /**
     * selects a pivot in the array and 
     * selects the first items in the array 
     * that are larger than and smaller than
     * the pivot and switches them into their 
     * correct positions 
     */
    public static <T> void quickSort(T[] array, Comparator<T> comparator, int low, int high) {
        if (low < high) {
            int par = partition(array, comparator, low, high);
            //finds the partition that 
            //will be used to sort both sides of the array

            quickSort(array, comparator, low, par - 1);//sorting the array where the valeus are lower than the array
            quickSort(array, comparator, par + 1, high);//sorting the array where the valeus are higher than the array
        }
    }

    // QUICK SORT
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


    // HEAP SORT
    /**
     * goes through the array and finds the lowest 
     * numbers and puts them to the top of the array 
     * and switches it with the lowest to put the lowest 
     * item at the right of the array
     */
    public static <T> void heapSort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        buildMinHeap(array, comparator);
        for(int i = n - 1; i > 0; i--){
            swap(array, 0, i);          // move smallest to the end
            heapify(array, 0, i, comparator); // heap size is now i
        }
    }

    //HEAP SORT
    private static <T> void buildMinHeap(T[] array, Comparator<T> comparator){
        int n = array.length;
        for(int i = n / 2 - 1; i >= 0; i--){
            heapify(array, i, n, comparator);
        }
    }

    //HEAP SORT
    private static <T> void heapify(T[] array, int i, int n, Comparator<T> comparator){
        int left = 2*i + 1;
        int right = 2*i + 2;
        int smallest = i;

        if(left < n && comparator.compare(array[left], array[smallest]) < 0){
            smallest = left;
        }
        if(right < n && comparator.compare(array[right], array[smallest]) < 0){
            smallest = right;
        }
        if(smallest != i){
            swap(array, i, smallest);
            heapify(array, smallest, n, comparator);
        }
    }
    
    // SWAP
    private static <T> void swap (T[] array, int a, int b) {
        T temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
