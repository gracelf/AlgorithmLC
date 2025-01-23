package snake_tree;

/**
 *
 */
public class LC298BinaryTreeLongestConsecutiveSequence {

    public int longestConsecutive(TreeNode root) {
        int[] max = new int[1];
        dfs(root, max);
        return max[0];
    }

    private int dfs(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int maxLen = 1;
        int left = dfs(root.left, max);
        int right = dfs(root.right, max);

        if (left != 0 && root.left.val == root.val + 1) {
            maxLen = 1 + left;
        }

        if (right != 0 && root.right.val == root.val + 1) {
            maxLen = Math.max(maxLen, 1 + right);
        }
        max[0] = Math.max(maxLen, max[0]);
        //System.out.println("root: " + root.val + ", max[0]: " + max[0] + ", maxLen: " + maxLen);
        return maxLen;
    }

}
