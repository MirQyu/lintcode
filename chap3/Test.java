package chap3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Test {
	int maxSize = Integer.MIN_VALUE;

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

    public int longestConsecutive(TreeNode root) {
        // Write your code here
        help(root);
        
        return maxSize;
    }
    
    // 返回以该root为根 的Consecutive Sequence 长度
    public int help(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        // divide & conquer
        int leftSize = help(root.left);
        int rightSize = help(root.right);
        
        // merge
        int size = 1;
        if (leftSize != 0 && root.val+1 == (root.left).val) {
            size += leftSize;
        }
        
        if (rightSize != 0 && root.val + 1 == (root.right).val) {
            size = Math.max(1 + rightSize, size);
        }
        
        if (maxSize < size) {
            maxSize = size;
        }
        
        return size;
    }
    
    
    // 遍历的做法 Binary Tree Path Sum
    List<List<Integer>> list;
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // Write your code here
        
        list = new ArrayList<List<Integer>>();
        traverse(root, new ArrayList<Integer>(), target);
        
        return list;
    }
    
    public void traverse(TreeNode root, List<Integer> path, int target) {
        if (root == null) {
            return;
        }
        
        path.add(root.val);
        
        if (root.left == null && root.right == null) {
            int sum = 0;
            for (int i = 0; i < path.size(); i++) {
            	sum += path.get(i);
            }
            
            if (sum == target) {
                list.add(path);
            }
            
            return;
        }
        
        
        traverse(root.left, new ArrayList<Integer>(path), target);
        traverse(root.right, new ArrayList<Integer>(path), target);
    }
    
    public static void main(String[] args) {
//		Queue<int[]> queue = new LinkedList<int[]>();
//		
//		List<Integer> list = new ArrayList<>();
//		int[] directionX =  {1, 1, -1, -1, 2, 2, -2, -2};
//		
//		int i = 2, j = 3;
//		int[] arr = {i, j};
//		
//		for (int k : list) {
//			System.out.println(k);
//		}
        
//		Set<Integer> set = new HashSet<>();
//		set.add(null);
//		System.out.println(set.contains(null));
//		int i = 1;
//    	Map<Integer, Integer> map = new HashMap<>();
//    	Set<Integer> set = map.keySet();
//    	String s = "3!9!20!#!#!15!7!";
//    	String ss = "1!";
//    	String[] arr = ss.split("!");
//    	System.out.println(arr[0]);
//    	for (int i = 0; i < arr.length; i++) {
//    		System.out.println("["+ arr[i] + "],");
//    	}
//    	Integer a = 100;
//    	Integer b = 100;
//    	System.out.println(-3 % 10);
//    	int[] a = new int[] {1, 2};
//    	int[] b = new int[] {1, 2};
//    	
//    	System.out.println(a.equals(b));
//    	Integer[][] ins = new Integer[10][5];
//    	for (int i = 0; i < ins.length; i++) {
//    		for (int j = 0; j < ins[i].length; j++) {
//    			System.out.println(ins[i][j]);
//    		}
//    	}
    	
//    	List<StringBuilder> list = new ArrayList<>();
//    	List<StringBuilder> list2 = new ArrayList<>();
//    	list2.add(new StringBuilder("1234"));
//    	list2.add(new StringBuilder("5678"));
//    	list2.add(new StringBuilder("abcd"));
//    	list.addAll(list2);
//    	
//    	StringBuilder j = list2.get(0);
//    	j.append("qyu");
//    	
//    	
//    	for (StringBuilder sb : list) {
//    		System.out.println(sb);
//    	}
//    	
//    	System.out.println(list2.get(0));
    	
    	System.out.println("123".substring(0, 0).length());
    	
    
	}
    private static class Coordinate {
        int x, y;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public boolean equals(Object obj) {
            if (obj instanceof Coordinate) {
                Coordinate c = (Coordinate)obj;
                return x == c.x && y == c.y;
            }
            
            return false;
        }
        @Override
        public int hashCode() {
        	// TODO Auto-generated method stub
        	return super.hashCode();
        }
    }
    
}
