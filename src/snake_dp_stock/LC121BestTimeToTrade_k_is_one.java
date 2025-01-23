package snake_dp_stock;

/**
 *
 */
public class LC121BestTimeToTrade_k_is_one {

    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int max = 0;
        for (int price : prices) {
            buy = Math.min(buy, price);//minimum buy
            max = Math.max(max, price - buy);
        }
        return max;
    }

    public int maxProfit_dp(int[] prices) {
        int len = prices.length;
        int[] buy = new int[len];
        buy[0] = -prices[0];
        int[] sell = new int[len];
        for (int i = 1; i < len; i++) {
            buy[i] = Math.max(buy[i - 1], -prices[i]);//minimum buy
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }
        return sell[len - 1];
    }

    public int maxProfit_rollingMemory(int[] prices) {
        int buy = -prices[0];
        int sell = 0;
        for (int price : prices) {
            int nextBuy = Math.max(buy, -price);
            int nextSell = Math.max(sell, buy + price);
            buy = nextBuy;
            sell = nextSell;
        }
        return sell;
    }

    public static void main(String[] args) {
        LC121BestTimeToTrade_k_is_one sol = new LC121BestTimeToTrade_k_is_one();
        //test case 1
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("result for test case 1, simple logical method: " + sol.maxProfit(prices));
        System.out.println("result for test case 1, dp method: " + sol.maxProfit_dp(prices));
        System.out.println("result for test case 1, rolling memory method: " + sol.maxProfit_rollingMemory(prices));
    }

}
