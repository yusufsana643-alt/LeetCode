class Solution {

    class Fenwick {
        int[] bit;

        Fenwick(int n) {
            bit = new int[n + 2];
        }

        void update(int idx) {
            while (idx < bit.length) {
                bit[idx]++;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;
        int offset = n + 1;

        Fenwick bit = new Fenwick(2 * n + 5);

        long ans = 0;
        int prefix = 0;

        bit.update(offset);

        for (int x : nums) {

            if (x == target)
                prefix++;
            else
                prefix--;

            ans += bit.query(prefix + offset - 1);

            bit.update(prefix + offset);
        }

        return ans;
    }
}