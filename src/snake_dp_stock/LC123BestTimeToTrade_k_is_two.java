package snake_dp_stock;

/**
 *
 */
public class LC123BestTimeToTrade_k_is_two {

    public int maxProfit_dp(int[] prices) {
        int len = prices.length;
        int[][] buy = new int[3][len];
        buy[1][0] = -prices[0];
        buy[2][0] = -prices[0];
        int[][] sell = new int[3][len];
        for (int kk = 1; kk <= 2; kk++) {
            for (int i = 1; i < len; i++) {
                buy[kk][i] = Math.max(buy[kk][i - 1], sell[kk - 1][i - 1] - prices[i]);
                sell[kk][i] = Math.max(sell[kk][i - 1], buy[kk][i - 1] + prices[i]);
            }
        }
        return sell[2][len - 1];
    }

    public int maxProfit_rollingMemory(int[] prices) {
        int sell1 = 0, sell2 = 0, buy1 = -prices[0], buy2 = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]); //vs buy 1: buy1 = Math.max(buy1, -prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        //System.out.println("buy1: " + buy1 + ", sell1:" + sell1 + ", buy2:" + buy2 + ", sell2:" + sell2);
        return sell2;
    }

    public static void main(String[] args) {
        //test case 1, using code from LC188, expect result = 6
        LC188BestTimeToTrade_Core_k_is_a_variable sol = new LC188BestTimeToTrade_Core_k_is_a_variable();
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println("test case 1: " + sol.maxProfit(2, prices));//k = 2

        //same test case with new sol
        LC123BestTimeToTrade_k_is_two sol2 = new LC123BestTimeToTrade_k_is_two();
        System.out.println("test case 1, with new dp solution: " + sol2.maxProfit_dp(prices));
        System.out.println("test case 1, with new rolling memorysolution: " + sol2.maxProfit_rollingMemory(prices));
    }
}
