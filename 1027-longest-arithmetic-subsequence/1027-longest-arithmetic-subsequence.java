class Solution {
    public int longestArithSeqLength(int[] nums) {
        int[][] dp = new int[nums.length][1001];
        for(int i = 0; i < nums.length; i++) Arrays.fill(dp[i],1);
        int offset = 500;
        int maxAns = 0;
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                int diff = nums[j] - nums[i];
                int offseted = diff + offset;
                dp[i][offseted] = Math.max(dp[i][offseted], dp[j][offseted] + 1);
                maxAns = Math.max(maxAns, dp[i][offseted]);
            }
        }
        return maxAns;
    }
}