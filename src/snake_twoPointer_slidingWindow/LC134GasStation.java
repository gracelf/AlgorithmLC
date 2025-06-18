package snake_twoPointer_slidingWindow;

/**
 *
 */
public class LC134GasStation {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int res = 0; //debug, case {5}, {4}, intial value is 0
        int totalTank = 0;
        int currTank = 0;
        for (int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            totalTank += diff;
            currTank += diff;
            if (currTank < 0) {
                currTank = 0;
                res = (i == gas.length - 1 ? 0 : i + 1); //when i is the last element, the next element's start from begining of the circuit, index is 0
            }
        }
        return totalTank < 0 ? -1 : res;
    }

    public static void main(String[] args) {
        LC134GasStation sol = new LC134GasStation();
        //test case 1
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println(sol.canCompleteCircuit(gas, cost)); //expected result is 3
        //test case 2
        int[] gas2 = {2, 3, 1, 10};
        int[] cost2 = {3, 2, 10, 1};
        System.out.println(sol.canCompleteCircuit(gas2, cost2)); //expect res is 3
    }

}
