package snake_trie;

import java.util.*;

/**
 *
 */
public class LC642AutocompleteSystem {

    private TrieNode root = new TrieNode('\0');
    private TrieNode curr = root;
    //input string, will need to insert after the ending char typed in
    private StringBuilder path = new StringBuilder();
    //all the sentenses entered and its count
    private HashMap<String, Integer> countBook = new HashMap<>();

    //constructor
    public LC642AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            String s = path.toString();
            //#mark the end of input, insert the word and count+1, top 3 gets updated
            //input word should be saved as a historical sentence in system.
            insert(s, countBook.getOrDefault(s, 0) + 1);
            curr = root;
            path = new StringBuilder();
            //return an empty list here when input is '#', meaning the end of input
            return new ArrayList<>();
        }
        int index = c - 'a';
        if (c == ' ') {
            index = 26;
        }
        path.append(c);
        if (curr == null) {
            return new ArrayList<>();
        }
        TrieNode next = curr.children[index];

        curr = next;

        if (next != null) {
            return getTop3(next.top3);
        } else {
            return new ArrayList<>();
        }

    }

    //from pair list to return a simple string list
    private List<String> getTop3(List<Pair> top3) {
        List<String> res = new ArrayList<>();
        for (Pair pair : top3) {
            res.add(pair.str);
        }
        return res;
    }

    //insert count for the sentence at each char
    private void insert(String word, int count) {
        curr = root;
        countBook.put(word, count);
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (c == ' ') {
                index = curr.children.length - 1; //space, put in the last index of the child
            }
            TrieNode next = curr.children[index];
            if (next == null) {
                curr.children[index] = new TrieNode(c);
            }
            //update top3 at each index/TrieNode
            curr.children[index].updateTop3(word, count);
            // for (int i = 0; i < curr.children[index].top3.size(); i++) {
            //     System.out.println(curr.children[index].top3.get(i).str + " " + curr.children[index].top3.get(i).count);
            // }
            curr = curr.children[index];
        }
        curr.ifWord = true;
        //back tracking, ready for next input
        curr = root;
    }
}

class TrieNode {

    char ch;
    TrieNode[] children;
    //isEndWord, the last letter of the word
    boolean ifWord;
    //at each index/node of the word, there's top3 result
    List<Pair> top3;

    public TrieNode(char ch) {
        this.ch = ch;
        children = new TrieNode[27]; //index 0-25, 26 letters, index 26, space " "
        ifWord = false;
        top3 = new ArrayList<>();
    }

    public void updateTop3(String word, int count) {
        Pair pair = new Pair(word, count);

        for (int i = 0; i < top3.size(); i++) {
            if (top3.get(i).str.equals(word)) {
                top3.get(i).count = count;
                //get the top3 only of current size of the top3 arraylist is greater than 3
                getTop3();
                return;
            }
        }
        top3.add(pair);
        getTop3();

    }

    public void getTop3() {
        Collections.sort(top3, (a, b) -> a.count == b.count ? a.str.compareTo(b.str) : b.count - a.count);//if count equals, sort by string
        top3 = top3.subList(0, Math.min(3, top3.size()));
    }

}

class Pair {

    String str;
    int count;

    public Pair(String str, int count) {
        this.str = str;
        this.count = count;
    }
}
