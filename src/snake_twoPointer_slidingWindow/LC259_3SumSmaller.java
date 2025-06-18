package snake_twoPointer_slidingWindow;

import java.util.Arrays;

/**
 *
 */
public class LC259_3SumSmaller {

    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;
        for (int start = 0; start < nums.length - 2; start++) {
            int mid = start + 1;
            int end = nums.length - 1;
            while (mid < end) {
                int sum = nums[start] + nums[mid] + nums[end];
                if (sum < target) {
                    count += end - mid; // debug, count++; <=wrong
                    mid++;
                } else {
                    end--;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        LC259_3SumSmaller sol = new LC259_3SumSmaller();
        int[] nums = {-2, 0, 1, 3};
        int target = 2;
        System.out.println(sol.threeSumSmaller(nums, target));
    }

}
