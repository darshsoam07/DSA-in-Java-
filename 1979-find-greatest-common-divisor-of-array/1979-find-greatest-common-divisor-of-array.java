class Solution {
    public int findGCD(int[] nums) {
        int min = nums[0], max = nums[0];
        for (int x : nums) {
            if (x < min) min = x;
            if (x > max) max = x;
        }
        while (min != 0) {
            int t = max % min;
        max = min;
        min = t;
        }
        return max;
    }
}