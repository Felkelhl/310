import java.util.concurrent.TimeUnit;

/**
 *  A driver program to test to overall run times for the TowersOfHanoi problem and
 *  the Travelling Salesperson problem using HeapPremute(n)
 *
 *  Programmed by Hugo Felkel
 *  CSCI 310 Assignment 7
 *  Dr. McCauley, College of Charleston
 *
 *  Collaboration: I worked alone on this assignment
 */
public class RunTests {
    public static void main(String args[]){
        int Hanoi_size = 2;
        int TSP_size = 11;
        runTowersOfHanoi('A', 'B', 'C', Hanoi_size);
        runTSP(TSP_size);
    }
    private static void runTowersOfHanoi(char tower1, char tower2, char tower3, int n){
        System.out.println("Starting the Towers of Hanoi problem: ");
        System.out.println("_______________________________");
        long startTime = System.nanoTime();
        long startTime2 = System.currentTimeMillis();
        System.out.println("Towers of Hanoi when n = " + n);
        TowersOfHanoi.towers(tower1, tower2, tower3, n);
        long endTime   = System.nanoTime();
        long endTime2   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        long totalTime2 = endTime2 - startTime2;
        System.out.println("This program completed in : " + totalTime + " nanoseconds");
        System.out.println("This program completed in : " + totalTime2 + " milliseconds");
        long seconds = TimeUnit.MILLISECONDS.toSeconds(totalTime2);
        System.out.println("This program completed in : " + seconds + " seconds");
    }
    private static void runTSP(int size){
        System.out.println("Starting the Travelling Salesperson problem: ");
        System.out.println("_______________________________");
        long startTime = System.nanoTime();
        long startTime2 = System.currentTimeMillis();
        System.out.println("Travelling Salesperson problem when n = " + size);
        TSP.run(size);
        long endTime   = System.nanoTime();
        long endTime2   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        long totalTime2 = endTime2 - startTime2;
        System.out.println("This program completed in : " + totalTime + " nanoseconds");
        System.out.println("This program completed in : " + totalTime2 + " milliseconds");
        long seconds = TimeUnit.MILLISECONDS.toSeconds(totalTime2);
        System.out.println("This program completed in : " + seconds + " seconds");
    }
}
