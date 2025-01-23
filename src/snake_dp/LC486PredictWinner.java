package snake_dp;

/**
 *
 */
public class LC486PredictWinner {

    public boolean predictTheWinner(int[] nums) {
        int len = nums.length;
        int sum = 0;
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            sum += nums[i];
            for (int j = i; j < len; j++) {
                dp[i][j] = Math.max(nums[i] + Math.min((i + 2 > j) ? 0 : dp[i + 2][j], (i + 1 > j - 1) ? 0 : dp[i + 1][j - 1]),
                        nums[j] + Math.min((i + 1 > j - 1) ? 0 : dp[i + 1][j - 1], (i > j - 2) ? 0 : dp[i][j - 2])
                );
            }
        }
        //System.out.println("dp[0][len - 1] : " + dp[0][len - 1]);
        return dp[0][len - 1] >= sum - dp[0][len - 1];
    }

    public static void main(String[] args) {
        //test case 1
        LC486PredictWinner sol = new LC486PredictWinner();
        int[] nums = {1, 5, 2};
        System.out.println("test case 1 result: " + sol.predictTheWinner(nums));

        //test case 2
        int[] nums2 = {1, 1, 1};
        System.out.println("test case 2 result: " + sol.predictTheWinner(nums2));

    }

}
