package snake_dp;

/**
 *
 */
public class LC1444CuttingAPizza_Hard {

    public int ways(String[] pizza, int k) {
        int row = pizza.length;
        int col = pizza[0].length();
        int[][][] dp = new int[k + 1][row][col];
        int[][] prefix = getPrefixSum(pizza);
        //initialization
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                dp[1][i][j] = prefix[i][j] > 0 ? 1 : 0;
            }
        }
        //populate dp, with i, j being decreasing index, k increasing
        for (int kk = 2; kk <= k; kk++) {
            for (int i = row - 1; i >= 0; i--) {
                for (int j = col - 1; j >= 0; j--) {
                    for (int ii = i + 1; ii < row; ii++) {
                        if (prefix[i][j] - prefix[ii][j] > 0) {
                            dp[kk][i][j] = (dp[kk][i][j] + dp[kk - 1][ii][j]) % 1_000_000_007;
                        }
                    }
                    for (int jj = j + 1; jj < col; jj++) {
                        if (prefix[i][j] - prefix[i][jj] > 0) {
                            dp[kk][i][j] = (dp[kk][i][j] + dp[kk - 1][i][jj]) % 1_000_000_007;
                        }
                    }
                }
            }
        }
        return dp[k][0][0];
    }

    private int[][] getPrefixSum(String[] pizza) {
        int row = pizza.length;
        int col = pizza[0].length();
        int[][] prefix = new int[row + 1][col + 1];
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                prefix[i][j] = prefix[i + 1][j] + prefix[i][j + 1]
                        - prefix[i + 1][j + 1] + (pizza[i].charAt(j) == 'A' ? 1 : 0);
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        LC1444CuttingAPizza_Hard sol = new LC1444CuttingAPizza_Hard();
        //test case 1
        String[] pizza = {"A..", "AAA", "..."};
        int k = 3;
        System.out.println("test case 1 res: " + sol.ways(pizza, k));

        //test case 2
        String[] pizza2 = {"A..", "AA.", "..."};
        int k2 = 3;
        System.out.println("test case 2 res: " + sol.ways(pizza2, k2));

    }

}
