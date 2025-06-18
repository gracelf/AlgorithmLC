package snake_BST_dataStructure;

import java.util.Stack;

/**
 *
 */
public class LC653BSTTwoSum {

    public boolean findTarget(TreeNode root, int k) {
        //initialize left stack and right stack
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();

        //left stack, add all left nodes
        TreeNode cur = root;
        while (cur != null) {
            leftStack.add(cur);
            cur = cur.left;
        }
        //right stack, add all right nodes
        cur = root;
        while (cur != null) {
            rightStack.add(cur);
            cur = cur.right;
        }

        while (leftStack.peek() != rightStack.peek()) {
            int leftVal = leftStack.peek().val;
            int rightVal = rightStack.peek().val;
            if (leftVal + rightVal == k) {
                return true;
            } else if (leftVal + rightVal > k) {
                rightMinus(rightStack);
            } else {
                leftPlus(leftStack);
            }
        }
        return false;
    }

    //
    private void leftPlus(Stack<TreeNode> leftStack) {
        TreeNode cur = leftStack.pop();
        cur = cur.right;
        while (cur != null) {
            leftStack.add(cur);
            cur = cur.left;
        }
    }

    private void rightMinus(Stack<TreeNode> rightStack) {
        TreeNode cur = rightStack.pop();
        cur = cur.left;
        while (cur != null) {
            rightStack.add(cur);
            cur = cur.right;
        }
    }

}
