package snake_tree;

/**
 *
 */
public class LC250CountUniValSubtrees {

    public int countUnivalSubtrees(TreeNode root) {
        int[] totalCount = new int[1];
        dfs(root, totalCount);
        return totalCount[0];
    }

    private boolean dfs(TreeNode root, int[] totalCount) {
        if (root == null) {
            return true;
        }
        boolean isLeftUnival = dfs(root.left, totalCount);
        boolean isRightUnival = dfs(root.right, totalCount);
        if (!isLeftUnival || !isRightUnival) {
            return false;
        }
        if (root.left != null && root.val != root.left.val) {
            return false;
        }
        if (root.right != null && root.val != root.right.val) {
            return false;
        }
        totalCount[0]++;
        return true;
    }

}
