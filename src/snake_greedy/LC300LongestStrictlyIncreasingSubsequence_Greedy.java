package snake_greedy;

/**
 *
 */
public class LC300LongestStrictlyIncreasingSubsequence_Greedy {

    public int lengthOfLIS(int[] nums) {
        //todo, code in doc https://docs.google.com/document/d/1bR4Hvxw35pCl6PszqcRqF1dUNWYxpTo6hTEh7s_91nc/edit?tab=t.0#heading=h.qi6kbjguyax6
        return 1;
    }

    public static void main(String[] args) {
        LC300LongestStrictlyIncreasingSubsequence_Greedy sol = new LC300LongestStrictlyIncreasingSubsequence_Greedy();
        //test case 1
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("tese 1: " + sol.lengthOfLIS(nums1));
        //test case 2, expected result = 3
        int[] nums2 = {4, 10, 4, 3, 8, 9};
        System.out.println("tese 2: " + sol.lengthOfLIS(nums2));
    }
}
