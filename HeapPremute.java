import java.util.ArrayList;
import java.util.List;

/**
 *  A utilty class designed to generated and return all instances of a given number size
 *  Heap's Permute is similiar to JonhsonTrotter permute, except this method stacks recursive calls
 *
 *  Programmed by Hugo Felkel
 *  CSCI 310 Assignment 7
 *  Dr. McCauley, College of Charleston
 *
 *  Collaboration: I worked alone on this assignment
 */

public class HeapPremute {
    /** Global variables */
    private static ArrayList list;
    private static int perm[];
    private static String value;
    private static int temp;
    /** Constructor */
    public HeapPremute(int n){
        perm = new int[n];
        list = new ArrayList();
        perm = createPerm(n);
        perm(perm, perm.length);
    }
    private int[] createPerm(int n){
        for(int i = 0; i < n; i++){
            perm[i] = i;
        }
        return perm;
    }
    private void perm(int perm[], int size) {
        value = null;
        /** The size is 1, write the permutation to a list */
        if (size == 1) {
            /** Obtaining value from permutation array and adding it into a list */
            for (int i = 0; i < perm.length; i++) {
                if(value == null)
                    value = String.valueOf(perm[i]);
                else
                    value += String.valueOf(perm[i]);
            }
            list.add(value);
        }
        for (int i = 0; i < size; i++) {
            perm(perm, size-1);

            /** The size is odd, swap first and last element */
            if (size % 2 == 1) {
                temp = perm[0];
                perm[0] = perm[size-1];
                perm[size-1] = temp;
            }
            /** The size is even, swap ith and last element */
            else {
                temp = perm[i];
                perm[i] = perm[size-1];
                perm[size-1] = temp;
            }
        }
    }
    private static void printPrem(List list) {
        for (int i=0; i<list.size(); i++) {
            System.out.print(list.get(i));
            System.out.println();
        }

    }
    public static ArrayList returnList(){
        return list;
    }
    // Driver code
    public static void main(String args[]) {
        HeapPremute obj = new HeapPremute(3);
        List list = obj.returnList();
        HeapPremute.printPrem(list);
    }
}
