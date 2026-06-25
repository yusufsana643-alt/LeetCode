class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] == target ? 1 : -1);
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (prefix[j] - prefix[i] > 0) {
                    ans++;
                }
            }
        }

        return ans;
    }
}