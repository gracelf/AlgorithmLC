package snake_bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 *
 */
public class SwapParkingToTargetPosition {

    // customized class to store state
    class ParkState {

        String state;
        int emptyIndex;

        ParkState(String status, int emptyIndex) {
            this.state = status;
            this.emptyIndex = emptyIndex;
        }
    }

    // minStep from AB#C to D#AB
    public int swapParkingSpot(char[] current, char[] target) {
        // corner case
        if (current == null || target == null || current.length != target.length) {
            throw new IllegalArgumentException("Invalid Input.");
        }
        String targetStatus = new String(target);
        // pre-process
        int len = current.length;
        int emptyIndex = 0;
        for (int i = 0; i < len; i++) {
            if (current[i] == '#') {
                emptyIndex = i;
                break;
            }
        }
        ParkState init = new ParkState(new String(current), emptyIndex);
        int numSteps = 0;
        Queue<ParkState> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(init);
        visited.add(init.state);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                ParkState cur = queue.poll();
                if (cur.state.equals(targetStatus)) {
                    return numSteps;
                }
                // generate unvisited next states and enqueue
                List<ParkState> nexts = changeStatus(cur, visited);
                queue.addAll(nexts);
            }
            numSteps++;
        }
        // Theoretically cannot reach here for valid input
        throw new RuntimeException("Impossible to change to target state.");
    }
    // helper method to generate unvisited next states

    private List<ParkState> changeStatus(ParkState current, Set<String> visited) {
        List<ParkState> nexts = new LinkedList<>();
        char[] cur = current.state.toCharArray();
        int emptyIndex = current.emptyIndex;
        for (int i = 0; i < cur.length; i++) {
            if (cur[i] != '#') {
                swap(cur, i, emptyIndex);
                String nextState = new String(cur);
                if (visited.add(nextState)) {
                    nexts.add(new ParkState(nextState, i));
                }
                swap(cur, i, emptyIndex); // revert back to cur
            }
        }
        return nexts;
    }

    // helper method to swap
    private void swap(char[] state, int i, int j) {
        char temp = state[i];
        state[i] = state[j];
        state[j] = temp;
    }

    public static void main(String[] args) {
        SwapParkingToTargetPosition parkingProblem = new SwapParkingToTargetPosition();
        char[] current = {'A', 'B', '#', 'D'};
        char[] target = {'D', '#', 'A', 'B'};
        int output = parkingProblem.swapParkingSpot(current, target);
        System.out.println("--- Test ---");
        System.out.println("Output: " + output);
        System.out.println("Expected: " + 3);
    }

}
