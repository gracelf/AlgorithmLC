package LaoLiuLecture;

/**
 *
 */
public class ArrayAndString {

    public static String removeSpaces(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        char[] chars = s.toCharArray();
        int slow = 0;
        for (int fast = 0; fast < chars.length; fast++) {
            if (!(chars[fast] == ' ' && (fast == chars.length - 1 || chars[fast + 1] == ' '))) {
                chars[slow++] = chars[fast];
            }
        }

        if (slow == 0) {
            return "";
        }
        ///length = slow -1!!!
        return chars[0] == ' ' ? new String(chars, 1, slow - 1) : new String(chars, 0, slow);
    }

    public static void main(String[] args) {
        String test = "     get an offer     !";
        System.out.println("result:" + removeSpaces(test));

    }

}
