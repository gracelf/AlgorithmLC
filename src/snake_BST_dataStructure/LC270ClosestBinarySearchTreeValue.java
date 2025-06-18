package snake_BST_dataStructure;

/**
 *
 */
public class LC270ClosestBinarySearchTreeValue {

    public int closestValue(TreeNode root, double target) {
        int res = root.val;
        TreeNode cur = root;
        while (cur != null) {
            //if there are multiple answers, return the smallest
            if (Math.abs(cur.val - target) == Math.abs(res - target)) {
                res = cur.val < res ? cur.val : res;
            }
            if (Math.abs(cur.val - target) < Math.abs(res - target)) {
                res = cur.val;
            }
            if (cur.val > target) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return res;
    }
}
