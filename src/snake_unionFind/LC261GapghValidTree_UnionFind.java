package snake_unionFind;

/**
 *
 */
public class LC261GapghValidTree_UnionFind {

    public boolean validTree(int n, int[][] edges) {
        //c.c
        if (n != edges.length + 1) {
            return false;
        }
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < edges.length; i++) {
            if (uf.find(edges[i][0], edges[i][1])) {
                return false;
            } else {
                uf.union(edges[i][0], edges[i][1]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LC261GapghValidTree_UnionFind sol = new LC261GapghValidTree_UnionFind();
        //test case 1
        int n = 5;
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
        System.out.println(sol.validTree(n, edges));//expected res: true

    }

    class UnionFind {

        int[] parent;
        int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int findRoot(int p) {
            int cur = p;
            while (parent[cur] != cur) {
                parent[cur] = parent[parent[cur]];
                cur = parent[cur];
            }
            parent[p] = cur;
            return cur;
        }

        public boolean find(int p, int q) {
            return findRoot(p) == findRoot(q);
        }

        public void union(int p, int q) {
            int rootP = findRoot(p);
            int rootQ = findRoot(q);
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
        }

    }
}
