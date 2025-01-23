package snake_tree;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class LC222CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {
        return dfs(root);
    }

    public int getHeight(TreeNode root) {
        int h = 0;
        while (root != null) {
            root = root.left;
            h++;
        }
        return h;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if (leftHeight > rightHeight) {
            int rightN = 1 << rightHeight;
            return rightN + dfs(root.left);
        } else if (leftHeight == rightHeight) {
            int leftN = 1 << leftHeight;
            return leftN + dfs(root.right);
        } else {
            return -1;
        }
    }
}
