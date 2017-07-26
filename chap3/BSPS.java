package chap3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chap3.Test.TreeNode;

public class BSPS {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	
	public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // Write your code here
        List<List<Integer>> lists = help(root);
        List<List<Integer>> ans = new ArrayList<>();
        for (List<Integer> list : lists) {
            int sum = 0;
            for (Integer obn : list) {
                sum += obn;
            }
            
            if (sum == target) {
                Collections.reverse(list);
                ans.add(list);
            }
        }
        
        return ans;
        
    }
    
	public List<List<Integer>> help(TreeNode root) {
        if (root == null) {
            return null;
        }
        // leaf node
        if (root.left == null && root.right == null) {
        	List<List<Integer>> result =  new ArrayList<List<Integer>>();
        	List<Integer> path = new ArrayList<>();
        	path.add(root.val);
        	result.add(path);
        	return result;
        }
        
        List<List<Integer>> left = help(root.left);
        List<List<Integer>> right = help(root.right);
        
        // merge
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if (left != null) {
            for (int i = 0; i < left.size(); i++) {
                left.get(i).add(root.val);
            }
            result.addAll(left);
        }
        
        if (right != null) {
            for (int i = 0; i < right.size(); i++) {
                right.get(i).add(root.val);
            }
            result.addAll(right);
        }

        return result;
    }
	
}
