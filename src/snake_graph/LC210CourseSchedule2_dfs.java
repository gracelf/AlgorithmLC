package snake_graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class LC210CourseSchedule2_dfs {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        Boolean[] visited = new Boolean[numCourses];
        List<Integer> res = new LinkedList<>();
        for (int cur = 0; cur < numCourses; cur++) {
            if (dfs(cur, graph, visited, res)) {
                return new int[0]; //!!!return empty list, array length 0
            }
        }
        return convertListToArray(res);

    }

    private int[] convertListToArray(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return new int[0]; // Return an empty array for null or empty list
        }

        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i); // Unboxing Integer to int
        }
        return array;
    }

    private List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prep : prerequisites) {
            int from = prep[1];
            int to = prep[0];
            graph.get(from).add(to);
        }
        return graph;
    }

    //has cycle check
    private boolean dfs(int cur, List<List<Integer>> graph, Boolean[] visited, List<Integer> res) {
        if (visited[cur] != null) {
            return visited[cur];
        }
        visited[cur] = true;
        for (int next : graph.get(cur)) {
            if (dfs(next, graph, visited, res)) {
                return true;
            }
        }
        visited[cur] = false;
        res.add(0, cur); //add cur at the begining
        return false;
    }

    public static void main(String[] args) {
        LC210CourseSchedule2_dfs sol = new LC210CourseSchedule2_dfs();
        int[][] prerequisites = {{0, 1}, {1, 0}};
        int[] res = sol.findOrder(2, prerequisites);
        PrintMethod.printArray(res);
    }
}
