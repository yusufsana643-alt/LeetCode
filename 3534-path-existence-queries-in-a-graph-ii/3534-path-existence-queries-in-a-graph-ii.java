import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = nums[i];
            pairs[i][1] = i;
        }

        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);

        int LOG = 20;
        int[][] up = new int[n][LOG];

        int r = n - 1;
        for (int l = n - 1; l >= 0; l--) {
            while (pairs[r][0] - pairs[l][0] > maxDiff) {
                r--;
            }

            int from = pairs[l][1];
            int to = pairs[r][1];

            up[from][0] = to;
            for (int k = 1; k < LOG; k++) {
                up[from][k] = up[up[from][k - 1]][k - 1];
            }
        }

        int[] ans = new int[queries.length];

        for (int t = 0; t < queries.length; t++) {
            int u = queries[t][0];
            int v = queries[t][1];

            if (nums[u] > nums[v]) {
                int tmp = u;
                u = v;
                v = tmp;
            }

            if (u == v) {
                ans[t] = 0;
                continue;
            }

            if (nums[u] == nums[v]) {
                ans[t] = 1;
                continue;
            }

            int dist = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                if (nums[up[u][k]] < nums[v]) {
                    dist += 1 << k;
                    u = up[u][k];
                }
            }

            if (nums[up[u][0]] < nums[v]) {
                ans[t] = -1;
            } else {
                ans[t] = dist + 1;
            }
        }

        return ans;
    }
}