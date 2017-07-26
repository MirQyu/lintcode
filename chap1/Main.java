package chap1;

public class Main {
	public static void main(String[] args) {
		Main m = new Main();
		char [][] image = new char[][] {{'0','0','1','0'},
										{'0','1','1','0'},
										{'0','1','0','0'}};
		System.out.println(m.minArea(image, 0, 2));
	}
	public int minArea(char[][] image, int x, int y) {
        // Write your code here
        if (image == null || image.length == 0 || image[0].length == 0) {
            return 0;
        }
        
        int n = image.length;
        int m = image[0].length;
        
        int leftCol = findLeft(image, 0, y);
        int rightCol = findRight(image, y, m-1);
        int upRow = findUp(image, 0, x);
        int downRow = findDown(image, x, n-1);
        
        int width = rightCol - leftCol + 1;
        int height = downRow - upRow + 1;
        
        return width * height;
    }
    
    // end 列一定含有1
    public int findLeft(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end-start)/2;
            if (isEmptyCol(image, mid)) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        if (isEmptyCol(image, start)) {
            return end;
        }
        
        return start;
    }
    
    // start 列一定含有1
    public int findRight(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end-start)/2;
            if (isEmptyCol(image, mid)) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        
        if (isEmptyCol(image, end)) {
            return start;
        }
        
        return end;
    }
    
    // end 行一定含有1
    public int findUp(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end-start)/2;
            if (isEmptyRow(image, mid)) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        if (isEmptyRow(image, start)) {
            return end;
        }
        
        return start;
    }
    
    public int findDown(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end-start)/2;
            if (isEmptyRow(image, mid)) {
                end = mid;
            }
            else {
                start = mid;
            }
        }
        
        if (isEmptyRow(image, end)) {
            return start;
        }
        
        return end;
    }
    
    
    
    public boolean isEmptyCol(char[][] image, int col) {
        for (int row = 0; row < image.length; row++) {
            if (image[row][col] == '1') {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean isEmptyRow(char[][] image, int row) {
        for (int col = 0; col < image[0].length; col++) {
            if (image[row][col] == '1') {
                return false;
            }
        }
        
        return true;
    }
}
