package snake_dp_stock;

/**
 *
 */
public class LC188BestTimeToTrade_Core_k_is_a_variable {

    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        int[][] dp_buy = new int[k + 1][len];
        int[][] dp_sell = new int[k + 1][len];
        //initialization
        for (int kk = 1; kk <= k; kk++) {
            dp_buy[kk][0] = -prices[0];//can be combined in the next nested loop
            //dp_sell[kk][0] = 0;//default value is 0
        }
        for (int kk = 1; kk <= k; kk++) {
            for (int i = 1; i < len; i++) {
                dp_buy[kk][i] = Math.max(dp_buy[kk][i - 1], dp_sell[kk - 1][i - 1] - prices[i]);
                dp_sell[kk][i] = Math.max(dp_sell[kk][i - 1], dp_buy[kk][i - 1] + prices[i]);
            }
        }
        return dp_sell[k][len - 1];
    }

    public static void main(String[] args) {
        LC188BestTimeToTrade_Core_k_is_a_variable sol = new LC188BestTimeToTrade_Core_k_is_a_variable();
        //test case 1
        int k = 2;
        int[] prices = {2, 4, 1};
        System.out.println("test case 1 res:" + sol.maxProfit(k, prices));

        //test case 2
        int k2 = 10; // same as k2=2
        int[] prices2 = {3, 2, 6, 5, 0, 3};
        System.out.println("test case 2 res:" + sol.maxProfit(k2, prices2));
    }

}
