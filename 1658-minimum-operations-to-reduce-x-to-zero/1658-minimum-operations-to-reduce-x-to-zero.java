class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;

        int total = 0;
        for(int num: nums) {
            total += num;
        }
        
        int target = total - x;
        if(target < 0) return -1;
        if(target == 0) return n;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);

        int prefixSum = 0;
        int maxLength = -1;

        for(int i = 0; i < n; i++){
            prefixSum += nums[i];

            int required = prefixSum - target;

            if(map.containsKey(required)) {
                int length = i - map.get(required);
                maxLength = Math.max(maxLength, length);
            }
            if(!map.containsKey(prefixSum)) {
                map.put(prefixSum, i);
            }
        }
        if(maxLength == -1) {
            return -1;
        }
        return n - maxLength;
    }
}