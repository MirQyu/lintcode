package chap1;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


public class Solution {
	
	public int strStr(String source, String target) {
        // write your code here
		if (source == null || target == null) {
			return -1;
		}
		
		for (int i = 0; i <= source.length() - target.length(); i++) {
			int j = 0;
			for (; j < target.length(); j++) {
				if (source.charAt(i + j) != target.charAt(j)) {
					break;
				}
			}
			
			if (j == target.length()) {
				return i;
			}
			
		}
		
		return -1;
    }
	
	public List<List<Integer>> result;
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // write your code here
		result = new ArrayList<>();
		Arrays.sort(nums);
		findSubsets(nums, 0, new ArrayList<Integer>());
		return result;
    }
	
	public void findSubsets(int[] nums, int start, ArrayList<Integer> list) {
		result.add(new ArrayList<Integer>(list));
		
		for (int i = start; i < nums.length; i++) {
			// 这里需要注意
			if (i != start && nums[i] == nums[i-1]) {
				continue;
			}
			list.add(nums[i]);
			findSubsets(nums, i+1, list);
			list.remove(list.size()-1);
		}
	}
	
	int remaining;
	int divis;
	public int divide(int dividend, int divisor) {
        // Write your code here
		
		int flag = dividend ^ divisor;
		if (flag == 0) {
			return 1;
		}
		dividend = -Math.abs(dividend);
		divisor = -Math.abs(divisor);
		remaining = 0;
		divis = divisor;
		int ans = (dividend <= divisor) ? rec(dividend) : 0;
        
		// 同符号
		if (flag > 0 && ans < 0) {
			return 2147483647;
		}
		if (flag < 0) {
			ans = -ans;
		}
		return ans;
		
    }
	/**
	 * 
	 * @param dividend
	 * @return dividend / divis 的结果
	 */
	public int rec(int dividend) {
		
		int halfDividend = (dividend + 1) >> 1;
		
		if (halfDividend > divis) {
			remaining = dividend - divis;
			return 1;
		}
		else {
			// 正的 ?
			// 递归
			int quotient = rec(halfDividend);
			int margin = dividend - (halfDividend << 1);
			
			quotient = quotient << 1;
			margin += (remaining << 1);
			while (margin <= divis) {
				quotient += 1;
				margin -= divis;
			}
			remaining = margin;
			return quotient;
		}
		
	}
	
	public int totalOccurrence(int[] A, int target) {
        // Write your code here
		return findRange(A, 0, A.length-1, target);
    }
	
	// [start, end]
	public int findRange(int[] A, int start, int end, int target) {
		int ans = 0;
		while (start <= end) {
			int mid = start + (end-start)/2;
			if (A[mid] == target) {
				ans++;
				for (int i = 1; mid - i >= start; i++) {
					if (A[mid - i] == target) {
						ans++;
					}
					else {
						break;
					}
				}
				
				for (int i = 1; mid + i <= end; i++) {
					if (A[mid + i] == target) {
						ans++;
					}
					else {
						break;
					}
				}
				
				return ans;
			}
			else if (A[mid] < target) {
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		
		return ans;
	}
	
//	public int mountainSequence(int[] nums) {
//        // Write your code here
//		findTopValue(nums, 0, nums.length-1);
//    }
//	
//	public int findTopValue(int[] nums, int start, int end) {
//		while ((end - start) > 1) {
//			int mid = start + (end-start)/2;
//			
//			if (nums[mid] > nums[start]) {
//				if (nums[mid] > nums[mid+1]) {
//					start = mid;
//				}
//				else {
//					
//				}
//			}
//			else if (nums[mid] < nums[start]) {
//				end = mid - 1;
//			}
//		}
//	}
	
	public int closestNumber(int[] A, int target) {
        // Write your code here
        if (A == null || A.length == 0) {
        	return -1;
        }

        return findIndex(A, 0, A.length-1, target);
		
    }
	
	public int findIndex(int[] A, int start, int end, int target) {
		
		int equalIndex = -1;
		
		while (start + 1 < end) {
			int mid = start + (end-start)/2;
			// start ... mid .. target... end
			if (A[mid] < target) {
				start = mid;
			}
			// start ... target ... mid ..end
			else if(A[mid] > target) {
				end = mid;
			}
			else {
				equalIndex = mid;
				break;
			}
		}
		
		if (equalIndex != -1) {
			return -1;
		}
		
		int ans1 = Math.abs(A[start] - target);
		int ans2 = Math.abs(A[end] - target);
		
		return (ans1 < ans2) ? start : end;
	}
	
	
	public int lastPosition(int[] nums, int target) {
        // Write your code here
        if (nums == null || nums.length == 0) {
        	return -1;
        }
        
        return findLastPosition(nums, 0, nums.length-1, target);
    }
	
	public int findLastPosition(int[] nums, int start, int end, int target) {
		while (start + 1 < end) {
			int mid = start + (end-start)/2;
			
			if (nums[mid] < target) {// start .. mid .. targ.. end
				start = mid;
			}
			else if (nums[mid] > target) {// start .. targ .. mid .. end
				end = mid;
			}
			else {
				start = mid;
			}
		}
		
		if (nums[end] == target) {
			return end;
		}
		
		if (nums[start] == target) {
			return start;
		}
		
		return -1;
	}
	
	
	public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return false;
		}
		
		return searchTarget(matrix, target);
    }
	
	public boolean searchTarget(int[][] matrix, int target) {
		int row = matrix.length;
		int col = matrix[0].length;
		
		int size = row * col;
		
		int start = 0, end = size-1;
		
		while (start + 1 < end) {
			int mid = start + (end-start)/2;
			int midRow = mid / col, midCol = mid % col;
			if (matrix[midRow][midCol] < target) {
				start = mid;
			}
			else if (matrix[midRow][midCol] > target) {
				end = mid;
			}
			else {
				return true;
			}
		}
		
		int sRow = start / col, sCol = start % col;
		if (matrix[sRow][sCol] == target) {
			return true;
		}
		
		int eRow = end / col, eCol = end % col;
		if (matrix[eRow][eCol] == target) {
			return true;
		}
		
		return false;
		
	}
	
	public int mountainSequence(int[] nums) {
        // Write your code here
		if (nums == null || nums.length == 0) {
			return -1;
		}
		
		return findMax(nums, 0, nums.length-1);
    }
	
	public int findMax(int [] nums, int start, int end) {
		int max = -1;
		
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			
			if (nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) {
				max = nums[mid];
				break;
			}
			else if (nums[mid] > nums[mid-1]) {	// mid 在上升区间
				start = mid;
			}
			else { // mid 在下降区间
				end = mid;
			}
		}
		
		if (max != -1) {
			return max;
		}
		
		return (nums[start] > nums[end]) ? nums[start] : nums[end];
	}
	
//	public int searchBigSortedArray(ArrayReader reader, int target) {
//        // write your code here
//		int index = 1;
//		
//		while (true) {
//			int value = reader.get(index);
//			if (value != 2147483647) {
//				if (value < target) {
//					index <<= 1;
//				}
//				else {
//					break;
//				}
//			}
//			else {
//				index -= index/3;
//			}
//			
//		}
//		
//		int start = 0, end = index;
//		
//		while (start + 1 < end) {
//			int mid = start + (end-start)/2;
//			// start ... mid ... target .. end
//			if (reader.get(mid) < target) {
//				start = mid;
//			}
//			else if (reader.get(mid) > target) {
//				end = mid;
//			}
//			else {
//				end = mid;
//			}
//		}
//		
//		if (reader.get(start) == target) {
//			return start;
//		}
//		
//		if (reader.get(end) == target) {
//			return end;
//		}
//		
//		return -1;
//		
//    }
//	
	
	
	public static void main(String[] args) {
		int i = 1;
		i >>= 1;
		System.out.println(i);
	}
	
	
	
}
