package snake_greedy;

import snake_dp.*;
import snake_dfs.*;
import snake_bfs.*;
import java.util.List;

/**
 *
 */
public class PrintMethod {

    public static void print2DArray(int[][] array) {
        int rows = array.length;
        int columns = array[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(array[i][j] + ",");
            }
            System.out.println();
        }
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }

    public static void printListOfList(List<List<Integer>> list) {
        for (List<Integer> items : list) {
            for (Integer item : items) {
                System.out.print(item + ",");
            }
            System.out.println(" ");
        }
    }

    public static void printListOfStringList(List<List<String>> list) {
        for (List<String> items : list) {
            for (String item : items) {
                System.out.print(item + ",");
            }
            System.out.println(" ");
        }
    }

}
