class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        dp[0] = 0;        
        for (int i = 1; i <= n; i++) {
            int jump1 = dp[i - 1] + costs[i - 1] + 1;
            int jump2 = (i >= 2) ? dp[i - 2] + costs[i - 1] + 4 : Integer.MAX_VALUE;
            int jump3 = (i >= 3) ? dp[i - 3] + costs[i - 1] + 9 : Integer.MAX_VALUE;
            dp[i] = Math.min(jump1, Math.min(jump2, jump3));
        }        
        return dp[n];
    }
}