package core;

public class QuickSort {
	public void sortIntegers(int[] A) {
		if (A == null || A.length == 0) {
			return;	
		}
		
		quickSort(A, 0, A.length - 1);
	}

	private void quickSort(int[] A, int start, int end) {
		if (start >= end) {
			return;
		}
		
		int pivotIndex = partition(A, start, end);
		quickSort(A, start, pivotIndex - 1);
		quickSort(A, pivotIndex + 1, end);
	}

	// 取(start+end)/2 为pivot
	private int partition(int[] A, int start, int end) {
		swap(A, start, start + ((end - start) >>> 1));
		int pivot = A[start];
		
		int left = start + 1, right = end;
		
		// 1 为什么 left <= right  思考~
		while (left <= right) {
			// 2 为什么 第二个条件用 < 2️而不用 <= 呢？   						（平衡，尽量都向中间靠拢）
			while (left <= right && A[left] < pivot) {				
				left++;
			}
			while (left <= right && A[right] > pivot) {				
				right--;
			}
			
			if (left <= right) {
				swap(A, left, right);
				left++;
				right--;
			}
		}
		
		swap(A, start, right);
		
		return right;
	}
	
	public void swap(int[] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
}
