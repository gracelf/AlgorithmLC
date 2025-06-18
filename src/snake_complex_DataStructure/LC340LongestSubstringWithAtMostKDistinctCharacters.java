package snake_complex_DataStructure;

import java.util.HashMap;

/**
 *
 */
public class LC340LongestSubstringWithAtMostKDistinctCharacters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) {
            return 0;
        }

        int left = 0;  // Left pointer of the sliding window
        int maxLen = 0;  // To store the maximum length of the substring
        HashMap<Character, Integer> map = new HashMap<>();  // To store the frequency of characters in the window

        for (int right = 0; right < s.length(); right++) {
            // Add the current character to the map
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);

            // If there are more than k distinct characters, shrink the window
            while (map.size() > k) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.get(leftChar) - 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
                left++;  // Move the left pointer to shrink the window
            }

            // Update maxLen with the size of the valid window
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LC340LongestSubstringWithAtMostKDistinctCharacters sol = new LC340LongestSubstringWithAtMostKDistinctCharacters();
        //test case 1
        System.out.println(sol.lengthOfLongestSubstringKDistinct("eceba", 2)); //expected result is 3
        //test case 2
        System.out.println(sol.lengthOfLongestSubstringKDistinct("abbacbabcdbe", 4)); //expected result is 11
        //test case 3
        System.out.println(sol.lengthOfLongestSubstringKDistinct("aa", 1)); //expected result is 11

    }

}
