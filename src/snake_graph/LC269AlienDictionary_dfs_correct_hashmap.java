package snake_graph;

import java.util.*;

/**
 *
 */
public class LC269AlienDictionary_dfs_correct_hashmap {

    public String alienOrder(String[] words) {
        StringBuilder path = new StringBuilder();
        HashMap<Character, List<Character>> graph = new HashMap<>();
        //0 intitial; 1 visiting; 2 visited
        int[] status = new int[26];
        boolean isValid = buildGraph(graph, words);
        if (!isValid) {
            return "";
        }
        for (Character start : graph.keySet()) {
            if (dfs(graph, status, start, path)) {
                return "";
            }
        }
        return path.reverse().toString();
    }

    private boolean buildGraph(HashMap<Character, List<Character>> graph, String[] words) {
        //change this, only create new list when there is the char
        for (String word : words) {
            for (char c : word.toCharArray()) {
                if (!graph.containsKey(c)) {
                    graph.put(c, new ArrayList<>());
                }
            }
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
                    // Add the directed edge from char1 to char2
                    if (!graph.get(char1).contains(char2)) {
                        graph.get(char1).add(char2);
                    }
                    break;//end the current loop
                }
            }
        }
        return true;
    }

    //check if contains cycle and build path when no cycle
    private boolean dfs(HashMap<Character, List<Character>> graph, int[] status, char cur, StringBuilder path) {
        int idx = cur - 'a';
        if (status[idx] == 1) { //visiting
            return true;
        }
        if (status[idx] == 2) {//visited
            return false;
        }
        status[idx] = 1;
        for (char next : graph.get(cur)) {
            if (dfs(graph, status, next, path)) {
                return true;
            }
        }
        path.append(cur);//add at the end, will reverse before final output
        status[idx] = 2;
        return false;
    }

    public static void main(String[] args) {
        LC269AlienDictionary_dfs_correct_hashmap solution = new LC269AlienDictionary_dfs_correct_hashmap();

        // Test case
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(solution.alienOrder(words));  // Expected output: "wertf"

        String[] words2 = {"z", "z"};//fail this case
        System.out.println(solution.alienOrder(words2));  // Expected output: "wertf"
    }
}
