package snake_dp;

/**
 *
 */
public class Lint395CoinsInALine {

    public boolean firstWillWin(int[] nums) {
        int len = nums.length;
        int sum = nums[len - 1];
        int[] dp = new int[len];
        dp[len - 1] = nums[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            sum += nums[i];
            dp[i] = Math.max(nums[i] + Math.min((i + 2 >= len) ? 0 : dp[i + 2], (i + 3 >= len) ? 0 : dp[i + 3]),
                    nums[i] + nums[i + 1] + Math.min((i + 3 >= len) ? 0 : dp[i + 3], (i + 4 >= len) ? 0 : dp[i + 4])
            );
        }
        //System.out.println("dp[0] : " + dp[0]);
        return dp[0] >= sum - dp[0];

    }

    public static void main(String[] args) {
        //test case 1, true
        Lint395CoinsInALine sol = new Lint395CoinsInALine();
        int[] nums = {1, 2, 2};
        System.out.println("test case 1 result: " + sol.firstWillWin(nums));

        //test case 2, false
        int[] nums2 = {1, 2, 4};
        System.out.println("test case 2 result: " + sol.firstWillWin(nums2));

    }

}
