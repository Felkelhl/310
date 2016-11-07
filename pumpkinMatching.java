/**
 * Created by Hugo on 10/26/16.
 * Matching of two strings based on largest subsequence match between the two
 *
 * Programmed by Hugo Felkel
 * CSCI 310 Assignment 6
 * Dr. McCauley, College of Charleston
 *
 * Collaboration: I worked alone on this assignment
 */
public class pumpkinMatching {

    private static int[][] table;
    private static String input1;
    private static String input2;

    public static int match(char[] arr1, char[] arr2) {
        table = new int[arr1.length + 1][arr2.length + 1];
        /** Start a 1 since the values of the first column and row must remain at zero */
        for (int i = 1; i <= arr1.length; i++) {
            for (int j = 1; j <= arr2.length; j++) {
                /** If the first character of each sequence matches, increases the table entry by 1 */
                if (arr1[i-1] == arr2[j-1]) {
                    table[i][j] = 1 + table[i-1][j-1];
                } else {
                    /** Set the table position to the max value return by looking in two spots on the table
                     *  Either one back in the row or one up in the column */
                    table[i][j] = Math.max(table[i][j-1], table[i-1][j]);
                }
            }
        }
        /** Return the element at the bottom right of the table, which will be your max value */
        return table[arr1.length][arr2.length];
    }
    public static String pumpkinMatch(char[] arr1, char[] arr2){
        if(match(arr1, arr2) == arr1.length)
            return "true";
        return Integer.toString(match(arr1, arr2));
    }
    public static void main(String[] args) {
        System.out.println("_____________Test Case 1__________");
        input1 = "ASAB";
        input2 = "AJSADB";
        System.out.println("Output: " + pumpkinMatch(input1.toCharArray(), input2.toCharArray()));
        System.out.println("Output should be: true");

        System.out.println("_____________Test Case 2__________");
        input1 = "ASAB";
        input2 = "HDSAJSW";
        System.out.println("Output: " + pumpkinMatch(input1.toCharArray(), input2.toCharArray()));
        System.out.println("Ouput should be: 2");

        System.out.println("_____________Test Case 3__________");
        input1 = "ASABIBBI";
        input2 = "QARSAGFTRABKPIRBEBNXZIAA";
        System.out.println("Output: " + pumpkinMatch(input1.toCharArray(), input2.toCharArray()));
        System.out.println("Ouput should be: true");

        System.out.println("_____________Test Case 4__________");
        input1 = "ASAB";
        input2 = "DSAJWBK";
        System.out.println("Output: " + pumpkinMatch(input1.toCharArray(), input2.toCharArray()));
        System.out.println("Ouput should be: 3");

        System.out.println("_____________Test Case 5__________");
        input1 = "AA";
        input2 = "AAAAA";
        System.out.println("Output: " + pumpkinMatch(input1.toCharArray(), input2.toCharArray()));
        System.out.println("Ouput should be: true");

    }
}