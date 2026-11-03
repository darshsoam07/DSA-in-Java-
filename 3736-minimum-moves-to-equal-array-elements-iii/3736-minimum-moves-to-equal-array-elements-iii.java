class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        int max = 0;
        for(int ele : nums){
            max = Math.max(ele,max);
        }
        int step = 0;
        for(int ele : nums){
            step += max - ele;
        }
        return step;
    }
}