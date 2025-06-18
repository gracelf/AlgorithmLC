package snake_BST_dataStructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 */
public class LC272ClosestBinarySearchTreeValueII {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new ArrayList<>();

        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();

        TreeNode cur = root;
        while (cur != null) {
            if (cur.val > target) {
                rightStack.push(cur);
                cur = cur.left;
            } else { //<=
                leftStack.push(cur);
                cur = cur.right;
            }
        }

        while (k-- > 0) {
            if (!leftStack.isEmpty() && !rightStack.isEmpty()) {
                int leftVal = leftStack.peek().val;
                int rightVal = rightStack.peek().val;
                if (Math.abs(target - leftVal) < Math.abs(target - rightVal)) {
                    res.add(leftMinus(leftStack));
                } else {
                    res.add(rightMinus(rightStack));
                }
            } else if (!leftStack.isEmpty()) {
                res.add(leftMinus(leftStack));
            } else {
                res.add(rightMinus(rightStack));
            }
        }
        return res;
    }

    private int leftMinus(Stack<TreeNode> leftStack) {
        TreeNode top = leftStack.pop();
        TreeNode cur = top.left;
        while (cur != null) {
            leftStack.push(cur);
            cur = cur.right;
        }
        return top.val;
    }

    private int rightMinus(Stack<TreeNode> rightStack) {
        TreeNode top = rightStack.pop();
        TreeNode cur = top.right;
        while (cur != null) {
            rightStack.push(cur);
            cur = cur.left;
        }
        return top.val;
    }

}
