package snake_tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LC894AllPossibleFullBinaryTree {

    public List<TreeNode> allPossibleFBT(int n) {
        if (n % 2 == 0) {
            return new ArrayList<>();
        }

        return buildTree(n);
    }

    private List<TreeNode> buildTree(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }

        for (int i = 1; i < n; i++) {
            List<TreeNode> lefts = buildTree(i);
            List<TreeNode> rights = buildTree(n - i - 1);

            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }

        return res;
    }

}
