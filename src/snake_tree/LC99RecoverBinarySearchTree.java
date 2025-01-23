package snake_tree;

/**
 *
 */
public class LC99RecoverBinarySearchTree {

    public void recover(TreeNode root) {
        TreeNode[] prev = new TreeNode[1];
        TreeNode[] mistakes = new TreeNode[2];
        dfs(root, prev, mistakes);
        //recover fun
        int temp = mistakes[0].val;
        mistakes[0].val = mistakes[1].val;
        mistakes[1].val = temp;
    }

    public void dfs(TreeNode cur, TreeNode[] prev, TreeNode[] mistakes) {
        //base case
        if (cur == null) {
            return;
        }
        //cur left > prev
        dfs(cur.left, prev, mistakes);

        if (prev[0] != null && cur.val <= prev[0].val) {//cur > prev to be true
            if (mistakes[0] == null) {
                mistakes[0] = prev[0];
            }
            mistakes[1] = cur;
        }
        prev[0] = cur;
        // right > cur
        dfs(cur.right, prev, mistakes);
    }

}
