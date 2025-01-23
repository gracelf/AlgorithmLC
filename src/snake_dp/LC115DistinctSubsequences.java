package snake_dp;

/**
 *
 */
public class LC115DistinctSubsequences {

    public int numDistinct(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        char[] charArr1 = s.toCharArray();
        char[] charArr2 = t.toCharArray();
        //dp initialization, dp[0][j] = 0;
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (charArr1[i - 1] == charArr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        //for debug, print result
//        for (int i = 0; i <= len1; i++) {
//            for (int j = 0; j <= len2; j++) {
//                System.out.println("i: " + i + ", j: " + j + ", dp[i][j]" + dp[i][j]);
//            }
//        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        //test case 1
        String s = "rabbbit";
        String t = "rabbit";
        LC115DistinctSubsequences sol = new LC115DistinctSubsequences();
        System.out.println("res: " + sol.numDistinct(s, t));

        //test case 2
        String s2 = "dp";
        String t2 = "d";
        System.out.println("res: " + sol.numDistinct(s2, t2));

        //test case 3
        String s3 = "dp";
        String t3 = "d";
        System.out.println("res: " + sol.numDistinct(s3, t3));
    }
}
