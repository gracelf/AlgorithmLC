package snake_trie;

/**
 *
 */
public class LC211WordDictionary {

    class TrieNode {

        char val;
        TrieNode[] children;
        boolean isEndWord;

        public TrieNode(char c) {
            this.val = c;
            this.children = new TrieNode[26]; //a-c, no space
            this.isEndWord = false;
        }
    }

    private TrieNode root;

    public LC211WordDictionary() {
        root = new TrieNode('\0');
    }

    public void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            // Only create a new node if it doesn't exist
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode(c);
            }
            curr = curr.children[index];
        }
        curr.isEndWord = true;
    }

    public boolean search(String word) {
        return searchFromNode(word, root, 0);
    }

    private boolean searchFromNode(String word, TrieNode node, int index) {
        if (node == null) {
            return false;
        }
        if (index == word.length()) {
            return node.isEndWord;
        }

        char c = word.charAt(index);
        if (c == '.') {
            for (TrieNode childNode : node.children) {
                if (searchFromNode(word, childNode, index + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            int charIndex = c - 'a';
            if (node.children[charIndex] == null) {
                return false;
            } else {
                return searchFromNode(word, node.children[charIndex], index + 1);
            }

        }
    }
}
