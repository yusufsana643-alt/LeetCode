class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();

        // Store positions of all 1s
        int[] ones = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones[count++] = i;
            }
        }

        // Not enough 1s
        if (count < k) {
            return "";
        }

        String answer = "";

        // Consider every group of k consecutive 1s
        for (int i = 0; i + k - 1 < count; i++) {

            int left = ones[i];
            int right = ones[i + k - 1];

            String candidate = s.substring(left, right + 1);

            // Choose:
            // 1. Shorter length
            // 2. If same length, lexicographically smaller
            if (answer.isEmpty()
                    || candidate.length() < answer.length()
                    || (candidate.length() == answer.length()
                        && candidate.compareTo(answer) < 0)) {

                answer = candidate;
            }
        }

        return answer;
    }
}