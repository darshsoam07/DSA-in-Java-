class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 1;

        for (int x : nums) {
            int up = dp.getOrDefault(x, 0) + 1;
            int stay = dp.getOrDefault(x - 1, 0) + 1;

            dp.put(x + 1, Math.max(dp.getOrDefault(x + 1, 0), up));
            dp.put(x, Math.max(dp.getOrDefault(x, 0), stay));

            ans = Math.max(ans, Math.max(up, stay));
        }
        return ans;
    }
}