package snake_BST_dataStructure;

/**
 *
 */
public class LC285InorderSuccessorInBST {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val > p.val) {
                res = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return res;
    }
}
