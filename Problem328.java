/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dailyprogrammer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Calum
 */
public class Problem328 {

    // function to check if all rows and columns agree to the latin square conditions
    private static int isLatin(int[][] array) {
        //generate two 1d arrays to store row and column values
        int[] row = new int[array.length];
        int[] column = new int[array.length];

        //assume it is latin until proven otherwise.
        int latin = 1;

        //copy values from each column and row to the 1d arrays and check they agree with latin square rules.
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                row[j] = array[i][j];
                column[j] = array[j][i];
            }
            if (Latin1D(row) == 0 || Latin1D(column) == 0) {
                //given square is not latin
                latin = 0;
            }
        }

        return latin;
    }

    //check if all numbers in a row agree with the latin square conditions
    private static int Latin1D(int[] values) {
//assume is latin until proven otherwise
        int latin = 1;
        //sort values into order lowest to highest.
        bubbleSort(values);

        //check bubble sort is correct
//        for(int k = 0; k< values.length; k++){
//            System.out.printf("%d ", values[k]);
//        }
//        System.out.printf("\n");
        
        for (int i = 0; i < values.length - 1; i++) {
            //now values have been sorted should be difference of 1 between each adjacent number
            if (values[i + 1] - values[i] != 1) {
                //line is not a latin line, does not contain all numbers in sequence
                latin = 0;
                break;
            }
        }
        return latin;
    }

    private static void bubbleSort(int[] array) {
        int temp;

        // standard bubble sort method to sort array in ascending order
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {

                if (array[j+1] < array[j]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }

        }
    }


public static void solve(String[] args) throws IOException {
        /* This problem concerns checking to see if a given array is a latin square.
         * input will be a value for n (the square dimension) follwed by a single line string of numbers
         * problem is to see if given input forms a latin square (a square matrix of numbers
         * where on each line and column there are the numbers 1->n with no repeats)
         * link to problem: https://www.reddit.com/r/dailyprogrammer/comments/6v29zk/170821_challenge_328_easy_latin_squares/
         */

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String inputString = input.readLine();

//        // create a scanner so we can read the command-line input
//        Scanner scanner = new Scanner(System.in);
        //  prompt for square dimension, n.
        System.out.print("input value for n, the square dimension: ");
        int n = Integer.parseInt(input.readLine());

        //  prompt for input matrix that is to be checked if it is a latin square
        System.out.print("input array values: ");
        //store input line as a string to be converted into an integer array.
        String inputString2 = input.readLine();

        //break string into its parts splitting using space character delimiter " ".
        String[] inputStringArray = inputString2.split("\\s");

        //convert separated parts into integers and copy them to integer array.
        int[][] array = new int[n][n];

        int index = 0; //used to convert 1d string array to 2d integer array

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j] = Integer.parseInt(inputStringArray[index]);
                index++;
            }
        }

        /* now we have input array in proper format, i.e. an n x n array of integers,
         * need to try and check if it is a latin square or not.
         */
        int result = isLatin(array);
        
        if(result == 1){
        System.out.printf("is latin square? => true\n");
        }
        else{
            System.out.printf("is latin square? => false\n");
        }
        
//        System.out.printf("n is %d\n", n);
//         for(int i = 0; i< array.length; i++){
//            System.out.printf("%d\n", array[i][0]);
//        }
    }
}
