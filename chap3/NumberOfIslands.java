package chap3;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {

	int row, col;
    public int numIslands(boolean[][] grid) {
        // Write your code here
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        row = grid.length;
        col = grid[0].length;
        
        int ans = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == true) {
                    bfs(grid, i, j);
                    ans++;
                }
            }
        }
        
        return ans;
    }
    
    public void bfs(boolean[][]grid, int x, int y) {
    	
    	// magic numbers!
    	int[] directionX = {-1, 0, 1, 0};
    	int[] directionY = {0, 1, 0, -1};
    	
        Queue<int[]> queue = new LinkedList<>();
        grid[x][y] = false;
        queue.offer(new int[] {x, y});
        
        while (!queue.isEmpty()) {
            int[] coord = queue.poll();
            for (int i = 0; i < 4; i++) {
            	x = coord[0] + directionX[i];
            	y = coord[1] + directionY[i];
            	if (x < 0 || x >= row || y < 0 || y >= col) {
            		continue;
            	}
            	
            	if (grid[x][y]) {
            		grid[x][y] = false;
            		queue.add(new int[] {x, y});
            	}
            }
        }
    }
}
