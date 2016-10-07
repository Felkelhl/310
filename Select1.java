/**
 * Find the kth smallest integer in an array through the quickSelect algorithm
 *
 * Programmed by Hugo Felkel
 * CSCI 310 Assignment 3
 * Dr. McCauley, College of Charleston
 *
 * Constructor:
 * Select1(int[] array, int k) - int k is the position where you want to find the smallest integer
 */

public class Select1 {
    /** Global variables */
    private static int value;
    private static int comparisons;
    private final int position;

    /** Constructor */
    public Select1(int[] array, int k) {
        // reset comparison count for every select instance
        comparisons = 0;
        position = k;
        quickSelect(array, 0, array.length - 1, position);
        System.out.println("The element at " + k + " is: "+ value);
        System.out.println("The number of comparisons is: "+ comparisons);
    }
    /** QuickSelect method from the Levintin: Design and Analysis of Algorithms 3rd  */
    private int quickSelect(int[] array, int left, int right, int k) {
        int storeIndex = 0;
        if(left <= right) {
            storeIndex = partition(array, left, right);
            if (storeIndex == position - 1) {
                value = array[storeIndex];
                return array[storeIndex];
            } else if (storeIndex > (left + position - 1)) {
                comparisons++;
                quickSelect(array, left, (storeIndex - 1), k);

            }
        }
        if (storeIndex == position - 1) {
            value = array[storeIndex];
            return array[storeIndex];
        }else {
            return quickSelect(array, storeIndex + 1, right, (k - 1 - storeIndex));
        }
    }
    /** Lomuto Partition from the Levintin: Design and Analysis of Algorithms 3rd  */
    private int partition(int[] array, int left, int right) {
        int pivotValue = array[left];
        int storeIndex = left;
        for(int i = left+1; i < right; i++) {
            comparisons++;
            if(array[i] <= pivotValue) {
                storeIndex++;
                swap(array, storeIndex, i);
            }
        }
        swap(array, left, storeIndex);
        return storeIndex;
    }
    /** Swap method */
    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    /** Example from the book */
    public static void main(String[] args) {
        int[] array = {4, 1, 10, 8, 7, 12, 9, 2, 15};
        Select1 select = new Select1(array, 5);


    }
}
