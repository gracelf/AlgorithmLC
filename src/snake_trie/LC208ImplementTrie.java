package snake_trie;

/**
 *
 */
public class LC208ImplementTrie {

    class TrieNode {

        char val;
        TrieNode[] children;
        boolean isEndWord;

        public TrieNode(char c) {
            this.val = c;
            this.children = new TrieNode[26];
            this.isEndWord = false;
        }
    }
    private TrieNode root;

    public LC208ImplementTrie() {
        root = new TrieNode('\0');
    }

    public void insert(String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode(ch);
            }
            curr = curr.children[index];
        }
        curr.isEndWord = true;
    }

    public boolean startWith(String prefix) {
        TrieNode cur = searchFromRoot(prefix);
        return cur != null;
    }

    public boolean search(String word) {
        TrieNode cur = searchFromRoot(word);
        return cur != null && cur.isEndWord;
    }

    private TrieNode searchFromRoot(String prefix) {
        TrieNode cur = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (cur.children[index] == null) {
                return null;
            }
            cur = cur.children[index];
        }
        return cur;
    }

}
