class Solution {
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        int sum = 0;
        for(int num : nums) sum += num;
        ans = sum - nums[0]*nums.length;
        return ans;
    }
}