package snake_dp;

/**
 *
 */
public class LC312BurstBallons_wrong {

    public int maxCoins(int[] nums) {
        int len = nums.length;
        int[][] dp = new int[len][len];
        //intialization
        //dp[0][0] = 1;
        for (int i = 0; i < len; i++) {//wrong, i = len -1; i>=0; i--
            for (int j = i; j < len; j++) {
                //dp[i][j] = 0;
                int max = 0;
                for (int k = i; k <= j; k++) {
                    int left = (k == i) ? 0 : dp[i][k - 1];
                    int right = (k == j) ? 0 : dp[k + 1][j];
                    int valueWithK = ((i == 0) ? 1 : nums[i - 1]) * k * ((j == (len - 1)) ? 1 : nums[j + 1]); //wrong, not K, should be num[k] when calculating
                    max = Math.max(max, left + right + valueWithK);
                }
                dp[i][j] = max;
            }
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        LC312BurstBallons_wrong sol = new LC312BurstBallons_wrong();
        int[] nums = {3, 1, 5, 8};
        System.out.println("result: " + sol.maxCoins(nums));
    }
}
