class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int low = 0;
        int high = 0;

        for(int w : weights){
            low = Math.max(low, w);
            high += w;
        }

        while (low < high){
            int mid = low + (high - low) / 2;
            int d = 1;
            int cur = 0;

            for(int w : weights) {
                if(cur + w > mid) {
                    d++;
                    cur = 0;
                }
                cur += w;
            }
            if (d <= days) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}