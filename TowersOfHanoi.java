/**
 *  A coded verison of the Towers of Hanoi problem, where the lower bound is O(2^n)
 *
 *  Programmed by Hugo Felkel
 *  CSCI 310 Assignment 7
 *  Dr. McCauley, College of Charleston
 *
 *  Collaboration: I worked alone on this assignment
 */
public class TowersOfHanoi {
    public static void towers(char tower1, char tower2, char tower3, int n) {
        /** If only one block is left, move it from the first tower to the last */
        if (n == 1) {
        } else {
            towers(tower1, tower3, tower2, n - 1);
            towers(tower2, tower1, tower3, n - 1);
        }
    }
    public static void main(String[] args) {
        int num = 3;
        towers('A', 'B', 'C', num);
    }
}