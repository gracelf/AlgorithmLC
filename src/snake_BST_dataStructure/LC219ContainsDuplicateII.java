package snake_BST_dataStructure;

import java.util.HashSet;

/**
 *
 */
public class LC219ContainsDuplicateII {
    
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);                
            }
            if (!set.add(nums[i])) {
                return true;
            }            
        }
        return false;
    }
    
    public static void main(String[] args) {
        LC219ContainsDuplicateII sol = new LC219ContainsDuplicateII();
        int[] nums = new int[]{1, 2, 3, 1, 2, 3};
        int k = 2;
        System.out.println("res: " + sol.containsNearbyDuplicate(nums, k));
        
    }
    
}
