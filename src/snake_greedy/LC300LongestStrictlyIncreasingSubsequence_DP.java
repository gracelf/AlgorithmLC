package snake_greedy;

/**
 *
 */
public class LC300LongestStrictlyIncreasingSubsequence_DP {

    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        int max = 1;
        for (int i = 0; i < len; i++) {
            ////initialization, dp[i] = 1, at least the length of 1, containing 1 element of itself
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        //PrintMethod.printArray(dp);
        return max;
    }

    public static void main(String[] args) {
        LC300LongestStrictlyIncreasingSubsequence_DP sol = new LC300LongestStrictlyIncreasingSubsequence_DP();
        //test case 1
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("tese 1: " + sol.lengthOfLIS(nums1));
        //test case 2, expected result = 3
        int[] nums2 = {4, 10, 4, 3, 8, 9};
        System.out.println("tese 2: " + sol.lengthOfLIS(nums2));
    }
}
