package snake_dp;

/**
 *
 */
public class LC97InterleavingString {

    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        int len3 = s3.length();
        // corner case
        if (len1 + len2 != len3) {
            return false;
        }
        char[] charArr1 = s1.toCharArray();
        char[] charArr2 = s2.toCharArray();
        char[] charArr3 = s3.toCharArray();
        // s1[0,i) s2[0,j) open bracket, cause s1, s2, s3 can be empty strings. dp value from 0 up
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        // initialize dp[i][0] and dp[0][j]
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i - 1][0] && (charArr1[i - 1] == charArr3[i - 1]);
        }
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = dp[0][j - 1] && (charArr2[j - 1] == charArr3[j - 1]);
        }
        // build dp from [0][0] up
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int k = i + j;
                //logic here, can't combine the 2 conditions below
                if (charArr1[i - 1] == charArr3[k - 1]) {
                    dp[i][j] = dp[i - 1][j];
                }
                if (charArr2[j - 1] == charArr3[k - 1]) {
                    dp[i][j] |= dp[i][j - 1];
                }
            }
        }
//        //for debug
//        for (int i = 0; i <= len1; i++) {
//            for (int j = 0; j <= len2; j++) {
//                System.out.println("i :" + i + ", j: " + j + ", dp[i][j]" + dp[i][j]);
//            }
//        }
        // nested loop s3[i+j+1] => s1[i] + s2[j]
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        //test case 1
        LC97InterleavingString sol = new LC97InterleavingString();
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        boolean res = sol.isInterleave(s1, s2, s3);
        System.out.println("result for test case 1: " + res);

        //test case 2
        String s2_1 = "";
        String s2_2 = "";
        String s2_3 = "a";
        boolean res2 = sol.isInterleave(s2_1, s2_2, s2_3);
        System.out.println("result for test case 2: " + res2);

        //test case 3
        String s3_1 = "ab";
        String s3_2 = "bc";
        String s3_3 = "bbac";
        boolean res3 = sol.isInterleave(s3_1, s3_2, s3_3);
        System.out.println("result for test case 3: " + res3);
    }
}
