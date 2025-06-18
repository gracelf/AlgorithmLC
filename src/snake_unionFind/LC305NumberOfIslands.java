package snake_unionFind;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LC305NumberOfIslands {

    public static int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();

        UnionFind uf = new UnionFind(m, n);
        for (int[] pos : positions) {
            int i = pos[0];
            int j = pos[1];
            int p = uf.index(i, j);
            //!!!!should check if there is duplicate
            if (!uf.isIsland(p)) {
                uf.addIsland(p);
            }

            for (int[] dir : DIRECTIONS) {
                int ii = i + dir[0];
                int jj = j + dir[1];
                int q = uf.index(ii, jj);
                if (ii < 0 || ii >= m || jj < 0 || jj >= n) {
                    continue;
                }
                if (uf.isIsland(p) && uf.isIsland(q) && !uf.find(p, q)) {
                    uf.union(p, q); //combine connected islands and numOfIslands -- after combine
                }
            }
            res.add(uf.getNumOfIsland());
        }
        return res;
    }

    public static void main(String[] args) {
        LC305NumberOfIslands sol = new LC305NumberOfIslands();
        //test case 1
        int m = 3;
        int n = 3;
        int[][] positions = {{0, 0}, {0, 1}, {1, 2}, {2, 1}};
        System.out.println(sol.numIslands2(m, n, positions)); //expected result: [1,1,2,3]

        //test case 2, debug, [[0,0],[0,1],[1,2],[1,2]], adding duplicate island
        int[][] positions2 = {{0, 0}, {0, 1}, {1, 2}, {1, 2}};
        System.out.println(sol.numIslands2(m, n, positions2));//expected result: [1,1,2,2]
    }

    public class UnionFind {

        private int[] parent;
        private int[] size;
        private int numOfIslands;
        public int row, col;

        public UnionFind(int m, int n) {
            this.parent = new int[m * n];
            this.size = new int[m * n];
            this.numOfIslands = 0;
            this.row = m;
            this.col = n;
        }

        //find the root parent
        private int root(int p) {
            int cur = p;
            while (parent[cur] != cur) {
                parent[cur] = parent[parent[cur]];
                //make cur the root parent
                cur = parent[cur];
            }
            parent[p] = cur;
            return cur;
        }

        public boolean find(int p, int q) {
            return root(p) == root(q);
        }

        public void union(int p, int q) {
            int pRoot = root(p);
            int qRoot = root(q);
            if (size[pRoot] < size[qRoot]) {//merge p to q
                parent[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
            } else { //merge q to p
                parent[qRoot] = pRoot;
                size[pRoot] += size[qRoot];
            }
            this.numOfIslands--;
        }

        public int index(int i, int j) {
            return i * col + j;
        }

        public void addIsland(int p) {
            this.numOfIslands++;
            parent[p] = p;
            size[p] = 1;
        }

        public boolean isIsland(int p) {
            return size[p] >= 1;
        }

        public int getNumOfIsland() {
            return this.numOfIslands;
        }
    }

}
