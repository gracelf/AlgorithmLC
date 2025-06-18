package snake_graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 */
public class LC210CourseSchedule2_bfs {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //in degree
        int[] indegree = new int[numCourses];
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites, indegree);
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res.add(cur);//build result here
            for (int next : graph.get(cur)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        if (res.size() != numCourses) {
            return new int[0];
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

    private List<List<Integer>> buildGraph(int numCourses, int[][] prerequisites, int[] indegree) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] prep : prerequisites) {
            int from = prep[1];
            int to = prep[0];
            graph.get(from).add(to);
            indegree[to]++;
        }
        return graph;
    }

    public static void main(String[] args) {
        LC210CourseSchedule2_bfs sol = new LC210CourseSchedule2_bfs();
        int[][] prerequisites = {{0, 1}, {1, 0}};
        int[] res = sol.findOrder(2, prerequisites);
        PrintMethod.printArray(res);
    }
}
