package snake_tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LC241DifferentWaysToAddParentheses {

    public List<Integer> diffWaysToCompute(String expression) {
        return dfs(expression, 0, expression.length() - 1);
    }

    private List<Integer> dfs(String expression, int start, int end) {
        List<Integer> res = new ArrayList<>();
        boolean isSingleNum = true;
        for (int i = start; i <= end; i++) {
            if (isOpr(expression, i)) {
                isSingleNum = false;
                List<Integer> lefts = dfs(expression, start, i - 1);
                List<Integer> rights = dfs(expression, i + 1, end);
                for (int left : lefts) {
                    for (int right : rights) {
                        res.add(calculate(left, right, expression, i));
                    }
                }
//                char val = expression.charAt(i);
//                System.out.println("=============, i: " + i + ", start:" + start + ", end: " + end);
//                System.out.println("val: " + val);
//                System.out.println("left: " + lefts);
//                System.out.println("right: " + rights);
//                List<Integer> one = combine(lefts, rights, val);
//                System.out.println("one: " + one);
//                res.addAll(one);
            }
        }
        if (isSingleNum) {
            res.add(Integer.valueOf(expression.substring(start, end + 1)));
        }
        return res;
    }

    private int calculate(int left, int right, String expression, int i) {
        char ch = expression.charAt(i);
        switch (ch) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            default:
                throw new IllegalArgumentException();
        }
    }

    private boolean isOpr(String expression, int i) {
        char val = expression.charAt(i);
        return val == '+' || val == '-' || val == '*';
    }

    private List<Integer> combine(List<Integer> lefts, List<Integer> rights, char val) {
        List<Integer> res = new ArrayList<>();
        for (Integer leftInt : lefts) {
            for (Integer rightInt : rights) {
                switch (val) {
                    case '+':
                        res.add(leftInt + rightInt);
                        System.out.println(" is + ");
                        break; //break!! otherwise goes to next case no matter what val is
                    case '-':
                        res.add(leftInt - rightInt);
                        System.out.println(" is - ");
                        break;
                    case '*':
                        res.add(leftInt * rightInt);
                        System.out.println(" is * ");
                        break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LC241DifferentWaysToAddParentheses sol = new LC241DifferentWaysToAddParentheses();
        //test case 1, res [0,1]
        System.out.println("test case 1: " + sol.diffWaysToCompute("2*1-1"));
    }

}
