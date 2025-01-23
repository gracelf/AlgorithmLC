package snake_dp_stock;

/**
 *
 */
public class LC714BestTimeToTrade_no_k_limit_with_Transaction_fee {

    public int maxProfit_dp(int fee, int[] prices) {
        int len = prices.length;
        int[] buy = new int[len];
        int[] sell = new int[len];
        //initialization
        buy[0] = -prices[0];

        for (int i = 1; i < len; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i] - fee);

        }
        return sell[len - 1];
    }

    public int maxProfit_rollingMemory(int fee, int[] prices) {
        //initialization
        int buy = -prices[0];
        int sell = 0;

        for (int i = 1; i < prices.length; i++) {
            int nextBuy = Math.max(buy, sell - prices[i]);
            int nextSell = Math.max(sell, buy + prices[i] - fee);
            buy = nextBuy;
            sell = nextSell;
        }
        return sell;
    }

    public static void main(String[] args) {
        LC714BestTimeToTrade_no_k_limit_with_Transaction_fee sol = new LC714BestTimeToTrade_no_k_limit_with_Transaction_fee();
        //test case 1
        int fee = 2;
        int[] prices = {1, 3, 2, 8, 4, 9};
        System.out.println("test case 1 res:" + sol.maxProfit_dp(fee, prices));

        //test case 2
        int fee2 = 3;
        int[] prices2 = {1, 3, 7, 5, 10, 3};
        System.out.println("test case 2 res:" + sol.maxProfit_dp(fee2, prices2));

        System.out.println("====rolling memory test case");
        System.out.println("test case 1 res:" + sol.maxProfit_rollingMemory(fee, prices));
        System.out.println("test case 2 res:" + sol.maxProfit_rollingMemory(fee2, prices2));

    }

}
