package snake_twoPointer_slidingWindow;

/**
 *
 */
public class LC340LongestSubstringwithAtMostKDistinctCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int max = 0;
        int start = 0;
        int[] count = new int[256];
        int total = 0;
        char[] arr = s.toCharArray();
        for (int end = 0; end < arr.length; end++) {
            char ch = arr[end];
            if (count[ch] == 0) {
                total++;
            }
            count[ch]++;
            while (total > k) {
                if (--count[arr[start++]] == 0) {
                    total--;
                }
            }
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        LC340LongestSubstringwithAtMostKDistinctCharacters sol = new LC340LongestSubstringwithAtMostKDistinctCharacters();
        //test case 1
        System.out.println(sol.lengthOfLongestSubstringKDistinct("eceba", 2));

    }

}
