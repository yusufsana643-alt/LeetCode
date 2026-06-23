class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        long[] up = new long[m];
        long[] down = new long[m];

        for (int v = 0; v < m; v++) {
            up[v] = v;
            down[v] = m - 1 - v;
        }

        if (n == 2) {
            long ans = 0;
            for (int i = 0; i < m; i++) {
                ans = (ans + up[i] + down[i]) % MOD;
            }
            return (int) ans;
        }

        for (int len = 3; len <= n; len++) {

            long[] newUp = new long[m];
            long[] newDown = new long[m];

            long[] prefDown = new long[m];
            long[] prefUp = new long[m];

            prefDown[0] = down[0];
            prefUp[0] = up[0];

            for (int i = 1; i < m; i++) {
                prefDown[i] = (prefDown[i - 1] + down[i]) % MOD;
                prefUp[i] = (prefUp[i - 1] + up[i]) % MOD;
            }

            long totalDown = prefDown[m - 1];
            long totalUp = prefUp[m - 1];

            for (int v = 0; v < m; v++) {

                if (v > 0) {
                    newUp[v] = prefDown[v - 1];
                }

                newDown[v] = (totalUp - prefUp[v] + MOD) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;

        for (int i = 0; i < m; i++) {
            ans = (ans + up[i] + down[i]) % MOD;
        }

        return (int) ans;
    }
}