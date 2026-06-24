class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        int states = 2 * m;

        long[] base = new long[states];

        for (int u = 0; u < m; u++) {
            for (int v = 0; v < m; v++) {
                if (u < v) {
                    base[v] = (base[v] + 1) % MOD;
                } else if (u > v) {
                    base[m + v] = (base[m + v] + 1) % MOD;
                }
            }
        }

        if (n == 2) {
            long ans = 0;
            for (long x : base) ans = (ans + x) % MOD;
            return (int) ans;
        }

        long[][] T = new long[states][states];

        for (int u = 0; u < m; u++) {

            for (int v = u + 1; v < m; v++) {
                T[v][m + u] = 1;
            }

            for (int v = 0; v < u; v++) {
                T[m + v][u] = 1;
            }
        }

        long[][] P = matPow(T, n - 2);

        long[] res = multiply(P, base);

        long ans = 0;

        for (long x : res) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }

    private long[] multiply(long[][] A, long[] v) {
        int n = A.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long cur = 0;
            for (int j = 0; j < n; j++) {
                cur = (cur + A[i][j] * v[j]) % MOD;
            }
            res[i] = cur;
        }

        return res;
    }

    private long[][] matPow(long[][] A, long e) {
        int n = A.length;

        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        while (e > 0) {
            if ((e & 1) == 1) {
                res = multiply(res, A);
            }

            A = multiply(A, A);
            e >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;

        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {

                if (A[i][k] == 0) continue;

                long val = A[i][k];

                for (int j = 0; j < n; j++) {
                    res[i][j] =
                        (res[i][j] + val * B[k][j]) % MOD;
                }
            }
        }

        return res;
    }
}