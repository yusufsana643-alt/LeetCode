class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {

        int m = grid.size();
        int n = grid.get(0).size();

        int[][] best = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(best[i], -1);
        }

        int startHealth = health - grid.get(0).get(0);

        if (startHealth <= 0) {
            return false;
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, startHealth});
        best[0][0] = startHealth;

        int[][] dir = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!q.isEmpty()) {

            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];
            int h = cur[2];

            if (x == m - 1 && y == n - 1) {
                return true;
            }

            for (int[] d : dir) {

                int nx = x + d[0];
                int ny = y + d[1];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
                    continue;
                }

                int nh = h - grid.get(nx).get(ny);

                if (nh <= 0) {
                    continue;
                }

                if (nh > best[nx][ny]) {
                    best[nx][ny] = nh;
                    q.offer(new int[]{nx, ny, nh});
                }
            }
        }

        return false;
    }
}
