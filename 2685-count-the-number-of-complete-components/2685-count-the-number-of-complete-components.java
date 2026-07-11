class Solution {
    public int countCompleteComponents(int n, int[][] edges) {

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        boolean[] vis = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                int[] res = dfs(i, graph, vis);
                int nodes = res[0];
                int degreeSum = res[1];

                if (degreeSum == nodes * (nodes - 1)) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private int[] dfs(int u, List<Integer>[] graph, boolean[] vis) {

        vis[u] = true;

        int nodes = 1;
        int degreeSum = graph[u].size();

        for (int v : graph[u]) {
            if (!vis[v]) {
                int[] res = dfs(v, graph, vis);
                nodes += res[0];
                degreeSum += res[1];
            }
        }

        return new int[]{nodes, degreeSum};
    }
}