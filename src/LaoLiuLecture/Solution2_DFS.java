package LaoLiuLecture;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Solution2_DFS {

    private static final void helper(char[] array, int index, StringBuilder sb, List<String> res) {

        // if we decide on all positions
        // we have a complete subset, all subset locate on the leaf node
        if (index == array.length) {
            res.add(sb.toString()); // deep copy  arr.clone() vs Arrays.Copyof()
            return;
        }

        // case1. add character at index;
        sb.append(array[index]);
        //res.add("true"+sb.toString());
        helper(array, index + 1, sb, res);

        // wall
        // remove the added character when backtracking to the upper level
        sb.deleteCharAt(sb.length() - 1); // backtracing	

        // case2. NOT add character at index
        //res.add("false"+sb.toString());
        helper(array, index + 1, sb, res);
        // wall
    }

    public static void main(String[] args) {
        char[] array = {'a', 'b', 'c'};
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(array, 0, sb, result);
        System.out.println("result size: " + result.size() + ", result : " + result);
    }

}
