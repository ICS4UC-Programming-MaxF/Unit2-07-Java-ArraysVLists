/* 
* Calculates the required length to equal exactly 1 board foot (144 in³).
* 
*
*
*
* @author  MF-ROB
* @version 1.0
* @since   2026-11-04
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Statistics {

    public static void main(String[] args) {
        // Put the three text files in the same folder as this program
        String[] files = {"Unit2-07-Set1.txt", "Unit2-07-Set2.txt", "Unit2-07-Set3.txt"};

        for (String fileName : files) {
            System.out.println("   ");
            System.out.println("File: " + fileName);

            // 1. Read into an ArrayList (we don't know how many items there are)
            ArrayList<Integer> list = new ArrayList<>();
            try {
                Scanner sc = new Scanner(new File(fileName));
                while (sc.hasNext()) {
                    if (sc.hasNextInt()) {
                        list.add(sc.nextInt());
                    } else {
                        System.out.println("Warning: skipping non-integer value '" + sc.next() + "'");
                    }
                }
                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error: could not find " + fileName);
                continue;
            }

            // Error check: empty file
            if (list.isEmpty()) {
                System.out.println("Error: no integers found in " + fileName);
                continue;
            }

            // 2. Copy to a fixed-length array
            int[] arr = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                arr[i] = list.get(i);
            }

            // 3. Display each number on one line
            for (int n : arr) {
                System.out.println(n);
            }

            // 4. Sort
            Arrays.sort(arr);

            // 5. Display results
            System.out.println("Count:  " + arr.length);
            System.out.println("Mean:   " + CalcMean(arr));
            System.out.println("Median: " + CalcMedian(arr));

            int[] modes = CalcMode(arr);
            if (modes.length == arr.length) {
                System.out.println("Mode:   none (every value appears once)");
            } else {
                System.out.println("Mode:   " + Arrays.toString(modes));
            }
        }
    }

    // Mean: sum of all values divided by how many there are
    public static double CalcMean(int[] arr) {
        long sum = 0;
        for (int n : arr) {
            sum += n;
        }
        return (double) sum / arr.length;
    }

    // Median: middle value of a sorted array (average of the two middle values if even)
    public static double CalcMedian(int[] arr) {
        int mid = arr.length / 2;
        if (arr.length % 2 == 1) {
            return arr[mid];
        }
        return (arr[mid - 1] + arr[mid]) / 2.0;
    }

    // BONUS: Mode - returns 1 or more values (array must already be sorted)
    public static int[] CalcMode(int[] arr) {
        int bestCount = 0;
        ArrayList<Integer> modes = new ArrayList<>();

        int i = 0;
        while (i < arr.length) {
            int j = i;
            while (j < arr.length && arr[j] == arr[i]) {
                j++;
            }
            int count = j - i;
            if (count > bestCount) {
                bestCount = count;
                modes.clear();
                modes.add(arr[i]);
            } else if (count == bestCount) {
                modes.add(arr[i]);
            }
            i = j;
        }

        int[] result = new int[modes.size()];
        for (int k = 0; k < result.length; k++) {
            result[k] = modes.get(k);
        }
        return result;
    }
}