class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length, low = 0, high = m * n - 1;
        while(low <= high){
            int mid = low + (high - low) / 2;
            int val = matrix[mid / n][mid % n];
            if(val == target)
                return true;
            if(val < target) 
                low = mid + 1;
            else
                high = mid - 1;
        }
        return false;
    }
}