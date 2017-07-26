package chap3;

public class Solution {
	
	TreeNode maxSubtree;
	int maxSum = Integer.MIN_VALUE;
	int maxSize = 1;
	
	
    
    public TreeNode findSubtree2(TreeNode root) {
        // Write your code here
        if (root == null) {
        	return null;
        }
		
        findMaxSubtree(root);
        
        return maxSubtree;
		
    }
    
    public static class ResultType {
		int childSum;
		int childSize;
		
		public ResultType(int sum, int size) {
			this.childSum = sum;
			this.childSize = size;
		}
	}
	
	public ResultType findMaxSubtree(TreeNode root) {
		if (root == null) {
			return new ResultType(0, 0);
		}
		
		if (root.left == null && root.right == null) {
			return new ResultType(root.val, 1);
		}
		
		// divide & conquer
		ResultType left = findMaxSubtree(root.left);
		ResultType right = findMaxSubtree(root.right);
		
		// merge
		if (left.childSize != 0) {
		    if (maxSum * left.childSize < left.childSum * maxSize) {
		        maxSubtree = root.left;
		        maxSum = left.childSum;
		        maxSize = left.childSize;
		    }
		}
		
		if (right.childSize != 0) {
		    if (maxSum * right.childSize < right.childSum * maxSize) {
		        maxSubtree = root.right;
		        maxSum = right.childSum;
		        maxSize = right.childSize;
		    }
		}
		
		left.childSize = left.childSize + right.childSize + 1;
		left.childSum = left.childSum + right.childSum + root.val;
		
		if (maxSum * left.childSize < left.childSum * maxSize) {
			maxSubtree = root;
			maxSum = left.childSum;
			maxSize = left.childSize;
		}
		
		return left;
	}
	
//	public static class ResultType {
//		TreeNode maxSubtree;
//		int maxSum;
//		
//		TreeNode childSubtree;
//		int childSum;
//		
//		public ResultType(TreeNode maxSubtree, int maxSum, TreeNode childSubtree, int childSum) {
//			this.maxSubtree = maxSubtree;
//			this.maxSum = maxSum;
//			this.childSubtree = childSubtree;
//			this.childSum = childSum;
//		}
//	}
	
//	public TreeNode findSubtree2(TreeNode root) {
//        // Write your code here
//        if (root == null) {
//        	return null;
//        }
//		
//        ResultType ans = findMaxSubtree(root);
//        
//        return ans.maxSubtree;
//		
//    }
//	
//	public ResultType findMaxSubtree(TreeNode root) {
//		if (root == null) {
//			return new ResultType(null, 0, null, 0);
//		}
//		
//		if (root.left == null && root.right == null) {
//			return new ResultType(root, root.val, root, root.val);
//		}
//		
//		// divide & conquer
//		ResultType left = findMaxSubtree(root.left);
//		ResultType right = findMaxSubtree(root.right);
//		
//		// merge
//		ResultType ans = left;
//		if (ans.maxSum < right.maxSum) {
//			ans.maxSubtree = right.maxSubtree;
//			ans.maxSum = right.maxSum;
//		}
//		
//		ans.childSum = left.childSum + right.childSum + root.val;
//		ans.childSubtree = root;
//		
//		if (ans.maxSum < ans.childSum) {
//			ans.maxSum = ans.childSum;
//			ans.maxSubtree = ans.childSubtree;
//		}
//		
//		return ans;
//	}
	
	
	public static class TreeNode {
		public int val;
	    public TreeNode left, right;
	    public TreeNode(int val) {
	    	this.val = val;
	        this.left = this.right = null;
	    }
	}
}
