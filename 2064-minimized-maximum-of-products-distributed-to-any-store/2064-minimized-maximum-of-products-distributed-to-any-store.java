class Solution {
    public int minimizedMaximum(int n, int[] quantities) {

        int left = 1;
        int right = 0;

        for (int q : quantities) {
            right = Math.max(right, q);
        }

        int ans = right;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            int stores = 0;

            for (int q : quantities) {
                stores += (q + mid - 1) / mid;
            }

            if (stores <= n) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }
}