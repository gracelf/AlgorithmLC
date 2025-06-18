package snake_graph;

import java.util.*;

/**
 *
 */
public class LC269AlienDictionary_dfs_wrong_edgeCase {

    public String alienOrder(String[] words) {
        StringBuilder path = new StringBuilder();
        List<List<Character>> graph = new ArrayList<>();
        //0 intitial; 1 visiting; 2 visited
        int[] status = new int[26];
        boolean isValid = buildGraph(graph, words);
        if (!isValid) {
            return "";
        }
        for (int i = 0; i < 26; i++) {
            List<Character> list = graph.get(i);
            if (list.isEmpty()) { //list==null, wrong
                continue;
            }
            if (dfs(graph, status, (char) ('a' + i), path)) {
                return "";
            }
        }
        return path.reverse().toString();
    }

    private boolean buildGraph(List<List<Character>> graph, String[] words) {
        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }

        // Initialize the graph and in-degree map
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            //corner case
            if (word1.length() > word2.length() && word1.startsWith(word2)) {
                return false;
            }

            int minLength = Math.min(word1.length(), word2.length());
            for (int j = 0; j < minLength; j++) {
                char char1 = word1.charAt(j);
                char char2 = word2.charAt(j);

                if (char1 != char2) {
                    int index1 = char1 - 'a';
                    // Add the directed edge from char1 to char2
                    if (!graph.get(index1).contains(char2)) {
                        graph.get(index1).add(char2);
                    }
                    break;//end the current loop
                }
            }
        }
        return true;
    }

    //check if contains cycle and build path when no cycle
    private boolean dfs(List<List<Character>> graph, int[] status, char cur, StringBuilder path) {
        int idx = cur - 'a';
        if (status[idx] == 1) { //visiting
            return true;
        }
        if (status[idx] == 2) {//visited
            return false;
        }
        status[idx] = 1;
        for (char next : graph.get(idx)) {
            if (dfs(graph, status, next, path)) {
                return true;
            }
        }
        path.append(cur);//add at the end, will reverse before final output
        status[idx] = 2;
        return false;
    }

    public static void main(String[] args) {
        LC269AlienDictionary_dfs_wrong_edgeCase solution = new LC269AlienDictionary_dfs_wrong_edgeCase();

        // Test case
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(solution.alienOrder(words));  // Expected output: "wertf"

        String[] words2 = {"z", "z"};//fail this case
        System.out.println(solution.alienOrder(words2));  // Expected output: "wertf"
    }
}
