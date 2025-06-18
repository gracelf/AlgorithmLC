package snake_twoPointer_slidingWindow;

/**
 *
 */
public class LC159LongestSubstringwithAtMostTwoDistinctCharacters {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int max = 0, idx1 = -1, idx2 = -1;
        char ch1 = '\u0000', ch2 = '\u0000';
        int start = 0;
        int len = s.length();
        for (int end = 0; end < len; end++) {
            char ch = s.charAt(end);
            if (ch == ch1) {
                idx1 = end;
            } else if (ch == ch2) {
                idx2 = end;
            } else { //third
                if (idx1 == -1) {
                    ch1 = ch;
                    idx1 = end;
                } else if (idx2 == -1) {
                    ch2 = ch;
                    idx2 = end;
                } else {
                    if (idx1 > idx2) {
                        start = idx2 + 1;
                        idx2 = end;
                        ch2 = ch;
                    } else {
                        start = idx1 + 1;
                        idx1 = end;
                        ch1 = ch;
                    }
                }
            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        LC159LongestSubstringwithAtMostTwoDistinctCharacters sol = new LC159LongestSubstringwithAtMostTwoDistinctCharacters();
        String s = "ccaabbb";
        System.out.println("test case 1: " + sol.lengthOfLongestSubstringTwoDistinct(s));//expected result is 5

        String s2 = "eceba";
        System.out.println("test case 2: " + sol.lengthOfLongestSubstringTwoDistinct(s2));//expected result is 3

    }

}
