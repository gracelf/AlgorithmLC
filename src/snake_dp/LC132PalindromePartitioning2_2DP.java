package snake_dp;

/**
 *
 */
public class LC132PalindromePartitioning2_2DP {

    public int minCut(String s) {
        char[] charArray = s.toCharArray();
        int len = s.length();
        //first DP: isPalindrome[j][i] means if s[j,i] is palindrome or not, j<=i
        boolean[][] isPalindrome = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            isPalindrome[i][i] = true;
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if (charArray[i] == charArray[j] && (j + 1 > i - 1 || isPalindrome[j + 1][i - 1])) {
                    isPalindrome[j][i] = true;
                }
            }
        }
        //second DP: s[0, i] min # of palindrome substring - 1, so dp[0] = 0; dp[1] = 0
        int[] dp = new int[len];
        dp[0] = 0;
        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = 0; j <= i; j++) {
                if (isPalindrome[j][i]) {
                    min = j == 0 ? 0 : Math.min(min, dp[j - 1] + 1); //if j == 0, meaning the first letter, won't need any cut, so min = 0;
                }
            }
            dp[i] = min;
        }
        PrintMethod.printArray(dp);
        return dp[len - 1];
    }

    public static void main(String[] args) {
        LC132PalindromePartitioning2_2DP sol = new LC132PalindromePartitioning2_2DP();
        System.out.println("res: " + sol.minCut("baab"));

    }
}
