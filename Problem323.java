/* This problem concerns checking to see if triplets of numbers from a provided array
 * can be summed to zero, and if so to display them in the terminal
 * link to problem: https://www.reddit.com/r/dailyprogrammer/comments/6melen/20170710_challenge_323_easy_3sum/
 */
package dailyprogrammer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 *
 * @author Calum
 */
public class Problem323 {

    //function to sort elements in an array into order of increasing value
    static void bubbleSort(int[] array) {
        int temp;

        // standard bubble sort method to sort array in ascending order
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {

                if (array[j + 1] < array[j]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }

        }
    }

    //function to identify how many negative values there are in the given array
    static int numNegatives(int[] values) {
        //choose to set the default value to be -1 so that the number of negatives found can be used as an array index
        int negativeCount = -1;

        for (int i = 0; i < values.length; i++) {
            if (values[i] > 0) {
                //number must be positive, therefore finish and return the result.
                break;
            } else {
                //number is negative so count it and continue counting
                negativeCount++;
            }
        }

        return negativeCount;
    }

//function to print out all values in array, used to check array is functioning properly
    static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d ", array[i]);
        }
        System.out.printf("\n");
    }

    //function to multiply all elements in array by -1 so that its values are inverted
    static void invertArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] *= -1;
        }
    }

    //function to identify triplet sums
    static void triplets(int[] negValues, int[] posValues, boolean invert) {

        /*set it up so that we start with first and second values from negative array
         * and last value from the positive array.
         * the idea here is to progress through all combinations of negative and 
         * positive values but to skip to the next pair of negatives once the sum 
         * becomes negative, i.e. the negative pair is too large for there to be 
         * any further triplet sums.  
         */
        int negIndex1 = 0;
        int negIndex2 = 1;
        int posIndex = posValues.length - 1;

        int sum; //value to hold result of summing three values together.

        //array used to sort the values into ascending order when a triplet sum has been found
        int[] triplet = new int[3];

        //stay in loop until final two negative values 
        while (true) {

            sum = negValues[negIndex1] + negValues[negIndex2] + posValues[posIndex];

            //in case we have a correct triplet sum, print it out
            if (sum == 0) {
                if (invert) {
                    //prepare values to be sorted into ascending order
                    triplet[0] = negValues[negIndex1] * -1;
                    triplet[1] = negValues[negIndex2] * -1;
                    triplet[2] = posValues[posIndex] * -1;
                    bubbleSort(triplet);
                    //if you want to print out negative values (in case of reusing 
                    //this function for inverted input arrays to get the other half of the triplet sums
                    System.out.printf("%d %d %d \n", triplet[0], triplet[1], triplet[2]);
                } else {
                    //prepare values to be sorted into ascending order
                    triplet[0] = negValues[negIndex1];
                    triplet[1] = negValues[negIndex2];
                    triplet[2] = posValues[posIndex];
                    bubbleSort(triplet);
                    System.out.printf("%d %d %d \n", triplet[0], triplet[1], triplet[2]);
                }
                //decrease the positive index incase there is another repeated value to create another zero sum
                //only if posIndex is not at minimum value already
                if (posIndex > 0) {
                    posIndex--;
                } else {
                    //if positive index is already at zero then increase the negative index values if they are also not at their final values
                    if (negIndex1 == negValues.length - 2 && negIndex2 == negValues.length - 1 && posIndex == 0) {
                        break;
                    }
                    negIndex1++;
                    negIndex2 = negIndex1 + 1;
                    posIndex = posValues.length - 1;
                }

            } //if sum is less than zero then shift along the negative index values 
            //since it means that there will not be any more tirplet sums using these negative numbers
            else if (sum < 0) {
                //check whether index2 is at maximum value yet
                if (negIndex2 == negValues.length - 1) {
                    //if it is at maximum value then increase value of index1 if it is not at maximum value
                    if (negIndex1 == negValues.length - 2) {
                        //in this case both index1 and index 2 are at maximum value
                        //therefore break from while loop
                        break;
                    } else {
                        // in this case index2 is at maximum value but index 1 is not
                        //therefore increase index 1, and change index 2 value to index1 + 1.
                        //also reset posIndex to maximum value
                        negIndex1++;
                        negIndex2 = negIndex1 + 1;
                        posIndex = posValues.length - 1;
                    }

                } else {
                    //in this case neither index1 or index2 are at their maximum values so increase the value of index 2.
                    negIndex2++;
                }
            } //if sum is +ve
            else {
                if (posIndex > 0) {
                    //reduce positive index value as the sum is too large.
                    posIndex--;
                } //in this case sum is still too large but there are no more positive values to try
                //therefore shift negative index values to next pair and reset positive index to maximum value
                else {

                    //this is incase there are no more negative values to try either
                    if (negIndex1 == negValues.length - 2 && negIndex2 == negValues.length - 1 && posIndex == 0) {
                        break;
                    }
                    negIndex1++;
                    negIndex2 = negIndex1 + 1;
                    posIndex = posValues.length - 1;
                }

            }

        }
    }

    public static void main(String[] args) throws IOException {

        //create buffered reader to read in input from terminal.
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        //  prompt for inputted array of numbers
        System.out.print("input values to be searched for triplet sums:\n");
        //read in line input to terminal as string and split it into a string array, using whitespace delimiter.
        String[] inputStringAsArray = input.readLine().split("\\s");

        //copy and convert string array to an integer array.
        int[] values = new int[inputStringAsArray.length];

        for (int i = 0; i < inputStringAsArray.length; i++) {
            values[i] = Integer.parseInt(inputStringAsArray[i]);
        }

        //rearrange values into increasing order from lowest to highest.
        bubbleSort(values);

        //numNegatives returns index of final negative value in sorted values array therefore add 1 here.
        int negativeCount = numNegatives(values);
        // System.out.printf("number of negative values inputted is: %d \n", negativeCount+1);

        //check values array correctly read in input.
        // printArray(values);
        //now that we have the index of the last negative number, split the original 
        //values array into two, one with positive values and one with negative values
        int[] negValues = new int[negativeCount + 1];
        int[] posValues = new int[values.length - (negativeCount + 1)];

        //copy negative values from values array to separate array
        for (int i = 0; i < negativeCount + 1; i++) {
            negValues[i] = values[i];
        }

        //copy positive values from values array to separate array
        int index = 0;         //needed to get correct index for posValues array
        for (int i = negativeCount + 1; i < values.length; i++) {
            posValues[index] = values[i];
            index++;
        }

        //check values were successfully copied to new arrays
        // printArray(negValues);
        // printArray(posValues);
        triplets(negValues, posValues, false);

        //so far the triplets method has only returned triplet sums for sums where 
        //there are two negative value now by inverting the positive and negative 
        //arrays so that their values have been multiplied by -1 throughout
        // and then sorting these arrays by value, this means that the triplets 
        //function can be reused in the same way as previouly by setting the third argument to true.
        invertArray(negValues);
        invertArray(posValues);
        bubbleSort(negValues);
        bubbleSort(posValues);

        triplets(posValues, negValues, true);

        System.exit(0);
    }
}
