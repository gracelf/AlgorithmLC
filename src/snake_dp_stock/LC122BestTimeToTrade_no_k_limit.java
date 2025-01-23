package snake_dp_stock;

/**
 *
 */
public class LC122BestTimeToTrade_no_k_limit {

    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                max += prices[i + 1] - prices[i];
            }
        }
        return max;
    }

    //todo dp
    public int maxProfit_dp(int[] prices) {
        int len = prices.length;
        int[] buy = new int[len];
        buy[0] = -prices[0];
        int[] sell = new int[len];
        for (int i = 1; i < len; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 1] - prices[i]);
            //for k variable dp_buy[kk][i] = Math.max(dp_buy[kk][i - 1], dp_sell[kk - 1][i - 1] - prices[i]);
            //for k = 1, buy[i] = Math.max(buy[i - 1], -prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[len - 1];
    }

    //rolling memory 
    public int maxProfit_rollingMemory(int[] prices) {
        int buy = -prices[0];
        int sell = 0;
        for (int i = 1; i < prices.length - 1; i++) {
            int nextBuy = Math.max(buy, sell - prices[i]);
            int nextSell = Math.max(sell, buy + prices[i]);
            buy = nextBuy;
            sell = nextSell;
        }
        return sell;
    }

    public static void main(String[] args) {
        LC122BestTimeToTrade_no_k_limit sol = new LC122BestTimeToTrade_no_k_limit();
        //test case 1
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("result for test case 1: " + sol.maxProfit(prices));
        //test case 2
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("result for test case 2: " + sol.maxProfit(prices2));

        System.out.println("=========dp test result");
        System.out.println("result for test case 1: " + sol.maxProfit_dp(prices));
        System.out.println("result for test case 2: " + sol.maxProfit_dp(prices2));

        System.out.println("=========rolling memeroy");
        System.out.println("result for test case 1: " + sol.maxProfit_rollingMemory(prices));
        System.out.println("result for test case 2: " + sol.maxProfit_rollingMemory(prices2));
    }

}
