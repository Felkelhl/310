/**
 * Find the kth smallest integer in an array through the quickSort algorithm
 *
 * Programmed by Hugo Felkel
 * CSCI 310 Assignment 4
 * Dr. McCauley, College of Charleston
 *
 * Constructor:
 * Select2(int[] array, int k) - int k is the position where you want to find the smallest integer
 */

import java.util.ArrayList;

public class Select2 {
    /** Global variables */
    private static int [] medians;
    private static int [] set;
    private static int [][] sets;
    private static ArrayList Sequence1, Sequence2, Sequence3;
    private static int size;
    private static int median;
    private static int addon;
    private static int comparisons;

    /** Constructor */
    public Select2(int[] arr, int k) {
        // reset comparison count for every select instance
        comparisons = 0;
        int value = select(arr, k);
        System.out.println("The element at " + k + " is: "+ value);
        System.out.println("The number of comparisons is: "+ comparisons);
    }
    /** Select2 algorithm */
    private int select(int[] array, int k) {
        /** Variable declaration */
        size = array.length;
        Sequence1 = Sequence2 = Sequence3 = new ArrayList();
        medians = new int[(size + 4) / 5];
        sets = new int[(size + 4 / 5)][];

        if (array.length < 50) {

            return selectLargest(array, k);

        } else {
            /** Check for an array divisible for 5
             *  if not divisible, it will have an unbalanced set for the last group of elements*/
            if(size%5 == 0)
                addon = 0;
            else{
                addon = 1;
            }
            for (int i = 0; i < size / 5 + addon; i++) {
                // decrement size by i*5: so 14 - 10 = 4 < 5, execute
                /** Check for the last group of elements */
                if (size - i * 5 < 5) {
                    /** Create a set for the last group of elements */
                    set = selectSet(array, i * 5, size % 5);
                    /** Determine median for set */
                    //medians[i] = findMedian(selectionSort(set), set.length);
                    medians[i] = findMedian(quickSort(set, 0, (set.length-1)),set.length);
                    /** Add set to overall sets array */
                    sets[i] = set;
                } else {
                    /** Create a set for all the other group of elements */
                    set = selectSet(array, i * 5, 5);
                    //medians[i] = findMedian(selectionSort(set), set.length);
                    medians[i] = findMedian(quickSort(set, 0, (set.length-1)),set.length);
                    sets[i] = set;
                }
            }
            /** Find the median of the medians of each sorted set */
            median = findMedian(medians, medians.length);

            /** Add the values of the set into proper sequences */
            setSequences();

            int[] seq1 = convertArray(Sequence1);
            int[] seq3 = convertArray(Sequence3);
            /** Sequence1 has the elements less than m and there are
             greater than k elements, so its bound to be in this section */
            if (Sequence1.size() >= k) {
                return selectLargest(seq1, k);
            }
            else {
                if (Sequence1.size() + Sequence2.size() >= k){
                    return median;
                } else /** The elements are in Sequence3*/
                    return selectLargest(seq3, k - Sequence1.size() - Sequence2.size());
                }
            }
        }
    /** QuickSort algorithm found in our book */
    public int[] quickSort(int[] array, int low, int high) {
        comparisons++;
        if(low >= high)
            return array;
        int pivot = partition(array, low, high);
        quickSort(array, low, pivot - 1);
        return quickSort(array, pivot+1, high);

    }
    /** Lomuto Partion scheme */
    private int partition(int array[], int left, int right) {
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
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    /** Select2 the interger for the array at k-1 */
    public int selectLargest(int[] array, int k){
        //selectionSort(array);
        quickSort(array, 0, array.length-1);
        return array[k-1];
    }
    /** Select2 the divided sets based on the leftmost integer as well as the desired size*/
    private static int[] selectSet(int[] array, int left, int n){
        int[] set = new int[n];
            for (int i = 0; i < n; i++) {
                set[i] = array[left];
                left++;
            }
        return set;
    }
    /** Return middle element */
    private static int findMedian(int arr[], int n)
    {
        return arr[n/2];
    }
    /** Convert ArrayList structure into an array */
    private static int[] convertArray(ArrayList list){
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = (Integer) list.get(i);
        }
        return array;
    }
    private static void setSequences(){
        for (int i = 0; i < (size + 4) / 5; i++) {
            for (int j = 0; j < sets[i].length; j++) {
                int test = sets[i][j];
                if (sets[i][j] < median)
                    Sequence1.add(test);  /** goes to Sequence1 */
                if (sets[i][j] > median)
                    Sequence3.add(test);  /** goes to Sequence3 */
                if(sets[i][j] == median) {
                    Sequence2.add(test);  /** goes to Sequence2 */
                }
            }
        }
    }
    public static void main(String[] args){
        int arr[] = {5,4,3,2,6,8,11,7,90,54,23,25,61,24};
        int arr2[]  = {4,1,10,8,7,12,9,2,15};
        Select2 select = new Select2(arr2, 5);
    }
}