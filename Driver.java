import java.util.ArrayList;

/**
 * Created by Hugo on 10/1/16.
 */
// Provide a chart, with the numbers calculated(value and comparisons) and organized in a way that is easy to read.
public class Driver {
    /** Global variables */
    private static int size;
    private static int k;
    /** Create generated list based on desired size of values 0 to 99
     * DISTINCT VALUES */
    public static int randNum(int lower, int upper){
        int r = (int) (Math.random() * (upper - lower)) + lower;
        return r;
    }
    public static int[] generateArray() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        while (list.size() < size) {

            int randomNum = (int)(Math.random() * 100);
            if (!list.contains(randomNum)) {
                list.add(randomNum);
            }
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
    public static void main(String[] args){
        /** Set size of generated Array */
        int [] arr;

        for(int i =0 ; i < 10; i++){
            System.out.println("Test " + (i + 1) + "\n");
            size = randNum(5, 75);
            k = randNum(1, size);
            System.out.println("Size of the array is: " + size);
            System.out.println("The k value is: " + k);
            arr = generateArray();
            System.out.println("\nSelect1 test: " );
            Select1 select = new Select1(arr, k);
            arr = generateArray();
            System.out.println("\nSelect2 test:");
            Select2 select2 = new Select2(arr, k);
            System.out.println("_______________________");
        }
    }
}
