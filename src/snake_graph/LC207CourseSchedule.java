package snake_graph;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LC207CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        Boolean[] visited = new Boolean[numCourses];
        for (int cur = 0; cur < numCourses; cur++) {
            if (dfs(graph, visited, cur)) {//if return true, meaning there is a cycle, thus return false as student can't finish all courses
                return false;
            }
        }
        return true;
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

    private boolean dfs(List<List<Integer>> graph, Boolean[] visited, int cur) {
        if (visited[cur] != null) {
            return visited[cur];
        }
        visited[cur] = true;
        for (int next : graph.get(cur)) {
            if (dfs(graph, visited, next)) {
                return true;
            }
        }
        visited[cur] = false;
        return false;
    }

    public static void main(String[] args) {
        LC207CourseSchedule sol = new LC207CourseSchedule();
        int[][] prerequisites = {{0, 1}, {1, 0}};
        System.out.println(sol.canFinish(2, prerequisites));
    }

}
