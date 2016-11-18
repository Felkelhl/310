import java.util.ArrayList;

/**
 *
 *  An exhaustive search over the travelling salesperson problem where the path for generated
 *  based on PermUtils, whick returns of instances of the permutations for a set of numbers
 *  defined by the size
 *
 *  Time complexity of lower bound is O(n!)
 *
 *  Programmed by Hugo Felkel
 *  CSCI 310 Assignment 7
 *  Dr. McCauley, College of Charleston
 *
 *  Collaboration: I worked alone on this assignment
 */
/**  An undirected graph with no cycles present */
public class TSP {
    /** Global variables */
    private static int [ ][ ] adjMatrix;
    private static ArrayList list;
    private static String currentPerm, path;
    private static int[] currentPath;
    private static int weight, min, vertexValue1, vertexValue2, vertexValue3;

    /** Constructor */
    public static void run(int size){
        randomMatrix(size);
        travelling(size);
        printPath(path, weight);
    }
    public static void travelling(int size) {
        /** Setting the minimum weight at zero and creating the permutations
         *  based on the size of the matrix */
        min = 0;
        HeapPremute heap = new HeapPremute(size);
        list = heap.returnList();
        /** Iterate over every path to see if one exists(Ex. 0-1, 1-2, 2-0) */
        for(int i = 0; i < list.size(); i++){
            weight = 0;
            currentPerm = list.get(i).toString();
            currentPath = new int[currentPerm.length()];

            for(int j = 0; j <= currentPerm.length(); j++) {
                /** If for loop doesn't terminate then store the weight then compare to find min*/
                if(j == currentPerm.length()) {
                    storeMin(min, weight, currentPerm);
                    break;
                }
                /** Store the vertexValue found with the raw permutation string instance */
                vertexValue1 = currentPerm.charAt(j) - '0';
                if (j + 1 >= currentPerm.length()) {
                    vertexValue2 = currentPath[0];
                } else {
                    vertexValue2 = currentPerm.charAt(j+1) - '0';
                }
                /** Test if an edge has been found */
                if (adjMatrix[vertexValue1][vertexValue2] != 0) {
                    if (weight == 0)
                        weight = adjMatrix[vertexValue1][vertexValue2];
                    else
                        weight += adjMatrix[vertexValue1][vertexValue2];
                /** No edge found is the permutation is no good */
                }
                if (adjMatrix[vertexValue1][vertexValue2] == 0) {
                    break;
                }
            }
        }
    }
    /** Store the minimum path weight found */
    private static void storeMin(int minimum, int pathWeight, String currentPath){
        if(minimum == 0){
            min = pathWeight;
            path = currentPath;
        }else if(minimum > pathWeight){
            min = pathWeight;
            path = currentPath;
        }// else do nothing, current minimum is the smallest weight

    }
    /** Print the lowest weight path found */
    private static void printPath(String path, int weight){
        char[] test = path.toCharArray();
        System.out.print("Path generated: ");
        for(int i = 0; i < test.length; i++){
            if (i + 1 == test.length)
                System.out.println(test[i] + "->" + test[0]);
            else
                System.out.print(test[i] + "->" + test[i+1] + ",");
        }
        System.out.println("The weight of this path is: " + weight);
    }
    /** Generate a random matrix based on the given size */
    private static void randomMatrix(int size){
        adjMatrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(i != j) {
                    if(adjMatrix[i][j] == 0) {
                        adjMatrix[i][j] = adjMatrix[j][i] = randNum(0, 10);
                    }
                }
            }
        }
    }
    private static void printMatrix(int size){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print(adjMatrix[i][j]);
            }
            System.out.println();
        }
    }
    /** Generate a random number(Represents the weight of the edges in the matrix) */
    public static int randNum(int lower, int upper){
        int randValue = (int) (Math.random() * (upper - lower)) + lower;
        return randValue;
    }
    public static void main(String args[]){
        int size = 9;
        randomMatrix(size);
        printMatrix(size);
        travelling(size);
        printPath(path, weight);
    }
}
