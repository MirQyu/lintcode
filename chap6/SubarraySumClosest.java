package chap6;

import java.util.Arrays;
import java.util.Comparator;

public class SubarraySumClosest {
	private static class Pair {
        public int index;
        public int sum;
        public Pair(int index, int sum) {
            this.index = index;
            this.sum = sum;
        }
    } 
    
    public int[] subarraySumClosest(int[] nums) {
        // write your code here
        int[] res = new int[2];
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        if (nums.length == 1) {
            res[0] = 0;
            res[1] = 1;
            return res;
        }
        
        int length = nums.length;
        Pair pairs[] = new Pair[length + 1];
        int pre = 0;
        pairs[0] = new Pair(0, 0);
        
        for (int i = 1; i <= length; i++) {
            pairs[i] = new Pair(i, pre + nums[i-1]);
            pre = pairs[i].sum;
        }
        
        Arrays.sort(pairs,  new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                return p1.sum - p2.sum;
            }
        });
        
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= length; i++) {
            if (ans > pairs[i].sum - pairs[i-1].sum) {
                ans = pairs[i].sum - pairs[i-1].sum;
                int[] temp = {pairs[i].index - 1, pairs[i-1].index - 1};
                Arrays.sort(temp);
                res[0] = temp[0] + 1;
                res[1] = temp[1];
            }
        }

        return res;
    }
}
