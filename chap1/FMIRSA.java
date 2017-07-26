package chap1;

public class FMIRSA {
	public int findMin(int[] nums) {
        // write your code here
		if (nums == null || nums.length == 0) {
			return -1;
		}
		
		int start = 0, end = nums.length-1;
		
		while (start + 1 < end) {
			int mid = start + (end-start)/2;
			if (nums[mid] > nums[end]) {
				start = mid;
			}
			else if (nums[mid] < nums[end]) {
				end = mid;
			}
		}
		
		return Math.min(nums[start], nums[end]);
    }
	
	public int findMin2(int[] nums) {
        // write your code here
		if (nums == null || nums.length == 0) {
			return -1;
		}
		
		int start = 0, end = nums.length-1;
		
		while (start + 1 < end) {
			int mid = start + (end-start)/2;
			if (nums[mid] > nums[start]) {
				start = mid;
			}
			else if (nums[mid] < nums[start]) {
				end = mid;
			}
		}
		
		return Math.min(nums[start], nums[end]);
    }
	
	// 先找出 最小值， 再二分
	public int search2(int[] A, int target) {
        // write your code here
        if (A == null || A.length == 0) {
            return -1;
        }
        
        if (A.length == 1) {
            return (A[0] == target) ? 0 : -1;
        }
        
        int start = 0, end = A.length-1;
        
        while (start + 1 < end) {
            int mid = start + (end-start)/2;
            if (A[mid] < A[end]) {
              end = mid;  
            }
            else if (A[mid] > A[end]) {
                start = mid;
            }
        }
        
        int minIndex = (A[start] < A[end]) ? start : end;
        int maxIndex = (minIndex != 0) ? minIndex-1 : A.length-1;
        
        if (target < A[minIndex] || target > A[maxIndex]) {
            return -1;
        }
        
        if (minIndex != 0) {
            if (target >= A[0]) {
                start = 0;
                end = maxIndex;
            }
            else {
                start = minIndex;
                end = A.length-1;
            }
        }
        else {
            start = 0;
            end = A.length-1;
        }
        
        while (start + 1 < end) {
            int mid = start + (end-start)/2;
            if (A[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        if (A[start] == target) {
            return start;
        }
        if (A[end] == target) {
            return end;
        }
        
        return -1;
        
    }
	
	// 直接二分，根据 A[mid] 在 哪一段上面
	public int search(int[] A, int target) {
        // write your code here
         if (A == null || A.length == 0) {
            return -1;
        }
        
        int start = 0, end = A.length-1;
        
        while (start + 1 < end) {
            int mid = start + (end-start)/2;
            if (A[mid] < A[end]) {
                if (target < A[mid] || target > A[end]) {
                    end = mid;
                }
                else { // target <= A[end]
                    start = mid;
                }
            }
            else { // A[mid] > A[end]
                if (target > A[mid] || target < A[start]) {
                    start = mid;
                }
                else { // target >= A[start]
                    end = mid;
                }
            }
        }
        
        if (target == A[start]) {
            return start;
        }
        
        if (target == A[end]) {
            return end;
        }
        
        return -1;
    }
	
	public int minArea(char[][] image, int x, int y) {
        // Write your code here
        
        int n = image.length;
        int m = image[0].length;
        
        int leftCol = findLeft(image, 0, y), rightCol = findRight(image, y, m - 1);
        int upRow = findUp(image, 0, x), downRow = findDown(image, x, n- 1);
        
        
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
            return start;
        }
        
        return end;
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
            return end;
        }
        
        return start;
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
            return start;
        }
        
        return end;
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
            return end;
        }
        
        return start;
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
        for (int col = 0; col < image[row].length; col++) {
            if (image[row][col] == '1') {
                return false;
            }
        }
        
        return true;
    }
}
