package core;

public class MergeSort {
	public void sortIntegers(int[] A) {
		if (A == null || A.length == 0) {
			return;
		}
		
		int[] temp = new int[A.length];
		mergeSort(A, 0, A.length - 1, temp);
	}

	//[start, end]
	private void mergeSort(int[] A, int start, int end, int[] temp) {
		if (start >= end) {
			return;
		}
		
		int middle = start + ((end - start) >>> 1);
		mergeSort(A, start, middle, temp);
		mergeSort(A, middle + 1, end, temp);
		
		merge(A, start, middle, end, temp);
	}

	private void merge(int[] A, int start, int middle, int end, int[] temp) {
	
		//int middle = start + ((end - start) >>> 1);
	
		int leftIndex = start;
		int rightIndex = middle + 1;
		int index = start;      // 下一个空闲的位置
		
		while (leftIndex <= middle && rightIndex <= end) {
			if (A[leftIndex] < A[rightIndex]) {
				temp[index++] = A[leftIndex++];
			} 
			else {
				temp[index++] = A[rightIndex++];
			}	
		}
		
		while (leftIndex <= middle) {
			temp[index++] = A[leftIndex++];
		}
		
		while (rightIndex <= end) {
			temp[index++] = A[rightIndex++];
		}
		
		for (int i = start; i <= end; ++i) {
			A[i] = temp[i];
		}
	}
}
