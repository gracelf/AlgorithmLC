package snake_unionFind;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LC261GapghValidTree_DFS {

    public boolean validTree(int n, int[][] edges) {
        //c.c
        if (edges == null || edges.length == 0) {
            return n == 1;
        }
        if (edges.length != n - 1) {
            return false;
        }
        List<List<Integer>> graph = buildGraph(n, edges);
        Boolean[] visited = new Boolean[n];
        for (int cur = 0; cur < n; cur++) {
            if (dfs(graph, visited, cur, -1)) {
                return false;
            }
        }
        return true;
    }

    private List<List<Integer>> buildGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            //undirected, two sides
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        return graph;
    }

    private boolean dfs(List<List<Integer>> graph, Boolean[] visited, int cur, int parent) {
        if (visited[cur] != null) {
            return visited[cur];
        }
        visited[cur] = true;
        for (int next : graph.get(cur)) {
            if (next == parent) {
                continue;
            }
            if (dfs(graph, visited, next, cur)) {
                return true;
            }
        }
        visited[cur] = false;
        return false;
    }

    public static void main(String[] args) {
        LC261GapghValidTree_DFS sol = new LC261GapghValidTree_DFS();
        //test case 1
        int n = 5;
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println(sol.validTree(n, edges)); //expected result: true
    }

}
