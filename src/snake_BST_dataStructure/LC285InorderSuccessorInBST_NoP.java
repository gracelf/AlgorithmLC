package snake_BST_dataStructure;

/**
 *
 */
public class LC285InorderSuccessorInBST_NoP {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        boolean containsP = false;
        TreeNode res = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val > p.val) {
                res = cur;
                cur = cur.left;
            } else if (cur.val < p.val) {
                cur = cur.right;
            } else {
                cur = cur.right;
                containsP = true;
            }
        }
        return containsP ? res : null;
    }

}
