package testCases;


/**
 *
 */
public class CharTest {

    public static void main(String[] args) {
        // emoji
        char[] emoji = "😀".toCharArray();
        System.out.println(emoji.length); // 2

        //月
        char[] chinese = "月".toCharArray();
        System.out.println(chinese.length); // 2

        String s = "A😀中";
        s.codePoints().forEach(System.out::println);
    }

}
