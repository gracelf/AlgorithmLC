package snake_twoPointer_slidingWindow;

/**
 *
 */
public class LC424LongestRepeatingCharReplacement {

    public int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int maxCount = 0;
        int maxLen = 0;
        int left = 0;
        char[] chars = s.toCharArray();
        for (int right = 0; right < chars.length; right++) {
            char ch = s.charAt(right);
            int currCount = ++count[ch - 'A'];
            maxCount = Math.max(currCount, maxCount);
            while (right - left + 1 - maxCount > k) {
                count[s.charAt(left++) - 'A']--;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LC424LongestRepeatingCharReplacement sol = new LC424LongestRepeatingCharReplacement();
        //test case 1
        String s = "AABABBA";
        int k = 1;
        System.out.println("test case 1: " + sol.characterReplacement(s, k));
    }

}
