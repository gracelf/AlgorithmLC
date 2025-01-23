package snake_tree;

/**
 *
 */
public class LC98ValidateBinarySearchTree {

    TreeNode[] prev = new TreeNode[1];

    public boolean isValidBST(TreeNode root) {
        TreeNode[] prev = new TreeNode[1];
        return dfs(root, prev);
    }

    public boolean dfs(TreeNode cur, TreeNode[] prev) {
        //base case
        if (cur == null) {
            return true;
        }
        //cur left > prev
        if (!dfs(cur.left, prev)) {
            return false;
        }
        if (prev[0] != null && cur.val <= prev[0].val) {//cur > prev to be true
            return false;
        }
        prev[0] = cur;
        // right > cur
        if (!dfs(cur.right, prev)) { //right and prev
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LC98ValidateBinarySearchTree sol = new LC98ValidateBinarySearchTree();
        //test case 1
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        //System.out.println("test case 1: " + sol.isValidBST(root));
        //test case 2
        TreeNode root2 = new TreeNode(5);
        //left
        TreeNode node1 = new TreeNode(1);
        TreeNode node4 = new TreeNode(4);
        root2.left = node1;
        node1.right = node4;
        //right
        TreeNode node8 = new TreeNode(8);
        TreeNode node6 = new TreeNode(6);
        TreeNode node9 = new TreeNode(9);
        TreeNode node5 = new TreeNode(5);
        node6.left = node5;
        node8.left = node6;
        node8.right = node9;
        root2.right = node8;
        System.out.println("test case 2: " + sol.isValidBST(root2));
    }

}
