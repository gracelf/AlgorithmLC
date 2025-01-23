package snake_BST_dataStructure;

import java.util.TreeSet;

/**
 *
 */
public class LC220ContainsDuplicateIII {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > indexDiff) {
                set.remove(nums[i - indexDiff - 1]);
            }
            int min = nums[i] - valueDiff;
            int max = nums[i] + valueDiff;
            Integer data = set.ceiling(min); // next bigger or equal value of in the set
            //System.out.println(min + ":" + (data != null ? data : 0));
            if (data != null && Integer.compare(data, max) <= 0) { //meaning data<=max
                System.out.println("data :" + data + ", max: " + max + ", i:" + i);
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        LC220ContainsDuplicateIII sol = new LC220ContainsDuplicateIII();
        int[] nums = new int[]{1, 3, 7, 2};
        System.out.println("test case res: " + sol.containsNearbyAlmostDuplicate(nums, 3, 1));

        int[] nums2 = new int[]{1, 2, 3, 1};
        System.out.println("test case 2 res: " + sol.containsNearbyAlmostDuplicate(nums2, 3, 0));

        TreeSet<Integer> treeSet = new TreeSet<>();

        // populate the TreeSet 
        treeSet.add(10);
        treeSet.add(20);
        treeSet.add(30);
        treeSet.add(40);

        // Print the TreeSet 
        System.out.println("====== test for TreeSet: " + treeSet);
        System.out.println("Ceiling value for 25: " + treeSet.ceiling(25)); //return 30, next bigger value in the tree set
        System.out.println("floor value for 25: " + treeSet.floor(25)); //return 20, next smaller value in the tree set

        System.out.println("Ceiling value for 20: " + treeSet.ceiling(20)); //return 20, next bigger or equal value in the tree set
        System.out.println("floor value for 20: " + treeSet.floor(20)); //return 20, next smaller or equal value in the tree set
    }

}
