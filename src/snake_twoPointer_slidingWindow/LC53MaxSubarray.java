package snake_twoPointer_slidingWindow;

/**
 *
 */
public class LC53MaxSubarray {

    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            maxSum = Math.max(dp[i], maxSum);
        }
        PrintMethod.printArray(dp);
        return maxSum;
    }

    public static void main(String[] args) {
        LC53MaxSubarray sol = new LC53MaxSubarray();
        //test case 1
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("test case 1 result:" + sol.maxSubArray(nums));
    }
}
