package snake_dp_stock;

/**
 *
 */
public class LC309BestTimeToTrade_no_k_limit_with_cooldown {

    public int maxProfit_dp(int[] prices) {
        int len = prices.length;
        int[] dp_buy = new int[len];
        int[] dp_sell = new int[len];
        //initialization
        dp_buy[0] = -prices[0];
        dp_sell[0] = 0;
        for (int i = 1; i < len; i++) {
            dp_buy[i] = Math.max(dp_buy[i - 1], (i >= 2) ? dp_sell[i - 2] : 0 - prices[i]);
            dp_sell[i] = Math.max(dp_sell[i - 1], dp_buy[i - 1] + prices[i]);
        }

        return dp_sell[len - 1];
    }

    public int maxProfit_rollingMemory(int[] prices) {
        int len = prices.length;
        //initialization
        //int buy1 = -prices[0];
        int buy2 = Math.max(-prices[0], len == 1 ? 0 : -prices[1]);
        int sell1 = 0;
        int sell2 = Math.max(0, len == 1 ? 0 : prices[1] - prices[0]);
        for (int i = 2; i < len; i++) {
            int nextBuy = Math.max(buy2, sell1 - prices[i]);
            int nextSell = Math.max(sell2, buy2 + prices[i]);
            buy2 = nextBuy;
            sell1 = sell2;
            sell2 = nextSell;
        }
        return sell2;
    }

    public static void main(String[] args) {
        LC309BestTimeToTrade_no_k_limit_with_cooldown sol = new LC309BestTimeToTrade_no_k_limit_with_cooldown();
        //test case 1
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println("test case 1 res:" + sol.maxProfit_dp(prices));
        //test case 2
        int[] prices2 = {1};
        System.out.println("test case 2 res:" + sol.maxProfit_dp(prices2));

        System.out.println("========== rolling memory");
        System.out.println("test case 1 res:" + sol.maxProfit_rollingMemory(prices));
        System.out.println("test case 2 res:" + sol.maxProfit_rollingMemory(prices2));
    }

}
