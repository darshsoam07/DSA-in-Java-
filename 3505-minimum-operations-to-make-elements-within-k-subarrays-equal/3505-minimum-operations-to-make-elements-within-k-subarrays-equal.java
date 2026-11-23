import java.util.TreeMap;

class Solution {
    static class MultiSet {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int size = 0;
        long sum = 0;

        void add(int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            size++;
            sum += val;
        }

        void remove(int val) {
            int count = map.get(val);
            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
            size--;
            sum -= val;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] windowCosts = getWindowCosts(nums, x);

        long INF = Long.MAX_VALUE / 2;
        // dp[i][j] = min operations to get j non-overlapping subarrays from index i..n-1
        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = INF;
            }
        }

        for (int i = n - x; i >= 0; i--) {
            for (int j = 1; j <= k; j++) {
                long skip = dp[i + 1][j];
                long pick = windowCosts[i] + dp[i + x][j - 1];
                dp[i][j] = Math.min(skip, pick);
            }
        }

        return dp[0][k];
    }

    private long[] getWindowCosts(int[] nums, int x) {
        int n = nums.length;
        long[] costs = new long[n - x + 1];
        MultiSet lower = new MultiSet(); // Max-side (holds smaller half)
        MultiSet upper = new MultiSet(); // Min-side (holds larger half)

        for (int i = 0; i < n; i++) {
            if (lower.size == 0 || nums[i] <= lower.map.lastKey()) {
                lower.add(nums[i]);
            } else {
                upper.add(nums[i]);
            }

            if (i >= x) {
                int out = nums[i - x];
                if (lower.map.containsKey(out)) {
                    lower.remove(out);
                } else {
                    upper.remove(out);
                }
            }

            while (lower.size > upper.size + 1) {
                int move = lower.map.lastKey();
                lower.remove(move);
                upper.add(move);
            }
            while (upper.size > lower.size) {
                int move = upper.map.firstKey();
                upper.remove(move);
                lower.add(move);
            }

            if (i >= x - 1) {
                long median = lower.map.lastKey();
                long leftCost = median * lower.size - lower.sum;
                long rightCost = upper.sum - median * upper.size;
                costs[i - x + 1] = leftCost + rightCost;
            }
        }

        return costs;
    }
}
