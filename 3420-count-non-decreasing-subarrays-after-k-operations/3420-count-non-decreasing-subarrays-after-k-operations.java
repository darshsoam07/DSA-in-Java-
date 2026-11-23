import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long cost = 0;
        
        Deque<Integer> dq = new ArrayDeque<>();
        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[left]) {
                int idx = dq.pollLast();
                int nextBoundary = dq.isEmpty() ? right + 1 : dq.peekLast();
                cost += (long) (nextBoundary - idx) * (nums[left] - nums[idx]);
            }
            dq.addLast(left);
            while (cost > k) {
                cost -= nums[dq.peekFirst()] - nums[right];
                if (dq.peekFirst() == right) {
                    dq.pollFirst();
                }
                right--;
            }
            ans += (right - left + 1);
        }

        return ans;
    }
}
