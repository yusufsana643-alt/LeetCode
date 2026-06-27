class Solution {
    public int maximumLength(int[] nums) {

        HashMap<Long, Integer> freq = new HashMap<>();

        for (int x : nums) {
            freq.put((long) x, freq.getOrDefault((long) x, 0) + 1);
        }

        int ans = 1;

        if (freq.containsKey(1L)) {
            int cnt = freq.get(1L);
            ans = Math.max(ans, cnt % 2 == 1 ? cnt : cnt - 1);
        }

        for (long start : freq.keySet()) {

            if (start == 1) continue;

            long cur = start;
            int len = 0;

            while (true) {

                Integer cnt = freq.get(cur);

                if (cnt == null) break;

                long next = cur * cur;

                if (cnt >= 2 && next <= 1000000000L && freq.containsKey(next)) {
                    len += 2;
                    cur = next;
                } else {
                    len++;
                    break;
                }
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }
}