import java.util.*;
class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if ((m+n-1) % 2 != 0){
            return false;
        }
        if(grid[0][0] == ')'){
            return false;
        }
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0,0,1});
        boolean[][][] visited = new boolean[m][n][m+n];
        visited[0][0][1] = true;

        int[][] directions = {
            {1, 0},
            {0, 1}
        };
        while (!queue.isEmpty()){
            int[] current = queue.poll();

            int row = current[0];
            int col = current[1];
            int balance = current[2];

            if(row == m-1 && col == n-1){
                if(balance == 0){
                    return true;
                }
            }
            for(int[] dir : directions){
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if(newRow >= m || newCol >= n){
                    continue;
                }
                int newBalance = balance;
                if(grid[newRow][newCol] == '('){
                    newBalance++;
                } else {
                    newBalance--;
                }

                if(newBalance < 0) {
                    continue;
                }
                if(visited[newRow][newCol][newBalance]){
                    continue;
                }
                visited[newRow][newCol][newBalance] = true;

                queue.offer(new int[]{newRow, newCol, newBalance});
            }
        }
        return false;
    }
}