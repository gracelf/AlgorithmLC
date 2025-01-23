package snake_tree;

/**
 *
 */
public class LC549BinaryTreeLongestConsecutiveSequence2 {

    public int longestConsecutive(TreeNode root) {
        int[] res = new int[1];
        dfs(root, res);
        return res[0];
    }

    private int[] dfs(TreeNode root, int[] res) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] max = new int[]{1, 1}; //max[0] desc from root, max[0] increase from root
        int[] left = dfs(root.left, res);
        int[] right = dfs(root.right, res);

        if (root.left != null && root.left.val + 1 == root.val) {
            max[0] = 1 + left[0];
        } else if (root.left != null && root.left.val - 1 == root.val) {
            max[1] = 1 + left[1];
        }

        if (root.right != null && root.right.val + 1 == root.val) {
            max[0] = Math.max(max[0], 1 + right[0]);
        } else if (root.right != null && root.right.val - 1 == root.val) {
            max[1] = Math.max(max[1], 1 + right[1]);
        }
        res[0] = Math.max(res[0], max[0] + max[1] - 1);
        //System.out.println("root: " + root.val + ", max[0]: " + max[0] + ", max[1]: " + max[1] + ", res: " + res[0]);
        return max;
    }

}
