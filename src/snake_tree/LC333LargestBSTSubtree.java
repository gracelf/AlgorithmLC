package snake_tree;

/**
 *
 */
public class LC333LargestBSTSubtree {

    class Result {

        int min, max, size;

        public Result() {
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            size = 0;
        }
    }

    //will have concurrence issue, but in this case, passing Result subject to dfs cost too much, let's try global variable 
    int globalMax = 0;

    public int largestBSTSubtree(TreeNode root) {
        dfs(root);
        return globalMax;
    }

    private Result dfs(TreeNode root) {
        Result res = new Result();
        if (root == null) {
            return res;
        }
        Result leftRes = dfs(root.left);
        Result rightRes = dfs(root.right);
        //if current tree not bst, return result
        if (root.left != null && (leftRes.size == 0 || leftRes.max >= root.val)) {
            return res;
        }
        if (root.right != null && (rightRes.size == 0 || rightRes.min <= root.val)) {
            return res;
        }

        res.min = root.left == null ? root.val : leftRes.min;
        res.max = root.right == null ? root.val : rightRes.max;
        res.size = leftRes.size + rightRes.size + 1;
        globalMax = Math.max(res.size, globalMax);
        return res;
    }

}
