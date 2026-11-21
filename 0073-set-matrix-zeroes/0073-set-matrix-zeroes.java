class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean col0 = false;
        for(int r = 0; r < m; r++) {
            if(matrix[r][0] == 0) col0 = true;
            for(int c = 1; c < n; c++){
                if(matrix[r][c] == 0) matrix[r][0] = matrix[0][c] = 0;  
            }
        }
        for(int r = m - 1; r >= 0; r--){
            for(int c = n - 1; c >= 1; c--){
                if(matrix[r][0] == 0 || matrix[0][c] == 0) matrix[r][c] = 0;
            }
            if(col0) matrix[r][0] = 0;
        }

    }
}