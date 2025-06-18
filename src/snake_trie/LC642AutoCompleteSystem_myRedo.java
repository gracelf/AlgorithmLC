package snake_trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
public class LC642AutoCompleteSystem_myRedo {

    TrieNode root = new TrieNode('\0');
    TrieNode curr = root;
    StringBuilder path = new StringBuilder();
    HashMap<String, Integer> countBook = new HashMap<>();

    LC642AutoCompleteSystem_myRedo(String[] sentences, int[] count) {
        for (int i = 0; i < sentences.length; i++) {
            insert(sentences[i], count[i]);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            String s = path.toString();
            insert(s, countBook.getOrDefault(s, 0) + 1);
            curr = root;
            path = new StringBuilder();
            return res;
        }
        int index = c - 'a';
        if (c == ' ') {
            index = 26;
        }
        path.append(c);
        if (curr == null) {
            return res;
        }
        //important step, even if children[index] is null
        curr = curr.children[index];
        if (curr == null) {
            return res;
        } else {
            return getTop3(curr.top3);
        }
    }

    private void insert(String sentence, int count) {
        curr = root;
        countBook.put(sentence, count);
        for (char c : sentence.toCharArray()) {
            int index = c - 'a';
            if (c == ' ') {
                index = curr.children.length - 1;
            }
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode(c);
            }
            curr.children[index].updateTop3(sentence, count);
            curr = curr.children[index];
        }
        curr.isWordEnd = true;
        curr = root;
    }

    private List<String> getTop3(List<Pair> top3) {
        ArrayList<String> res = new ArrayList<>();
        for (Pair pair : top3) {
            res.add(pair.str);
        }
        return res;
    }

    class TrieNode {

        char c;
        TrieNode[] children;
        boolean isWordEnd;
        List<Pair> top3;

        public TrieNode(char c) {
            this.c = c;
            this.children = new TrieNode[27]; //with ' ' space
            this.top3 = new ArrayList<>();
            this.isWordEnd = false;
        }

        private void updateTop3(String sentence, int count) {
            Pair newPair = new Pair(sentence, count);
            for (Pair topPair : top3) {
                if (topPair.str.equals(sentence)) {
                    topPair.count = count;
                    resetTop3();
                    return;
                }
            }
            top3.add(newPair);
            resetTop3();
        }

        private void resetTop3() {
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

}
