class Solution {
    public int splitArray(int[] nums, int k) {

        long ans = -1;
        long low = 0;
        long high = 0;

        for (int val : nums) {
            low = Math.max(low, val); // largest element
            high += val;              // total sum
        }

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (isPossible(nums, k, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return (int) ans;
    }

    private boolean isPossible(int[] nums, int k, long mid) {
        int count = 1;
        long sum = 0;

        for (int val : nums) {
            if (sum + val > mid) {
                count++;
                sum = val;
            } else {
                sum += val;
            }
        }

        return count <= k;
    }
}