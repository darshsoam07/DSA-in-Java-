class Solution {
    public int minimumSumSubarray(java.util.List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;
        boolean found = false;

        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        for (int len = l; len <= r; len++) {
            for (int i = 0; i <= n - len; i++) {
                int currentSum = prefix[i + len] - prefix[i];
                if (currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                    found = true;
                }
            }
        }

        return found ? minSum : -1;
    }
}