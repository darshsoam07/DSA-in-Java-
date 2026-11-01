class Solution {
    public int mySqrt(int n) {
        int low = 1, high = n;
        while (low <= high){
            long mid = low+ ((long)high - low) / 2;
            long val = mid * mid;
            if(val <= n) {
                low = (int) mid + 1;
            } else {
                high = (int) mid - 1;
            }
        }
        return high;
    }
}