package core;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {
    /**
     * @param points a list of points
     * @param origin a point
     * @param k an integer
     * @return the k closest points
     */
     
	static class Point {
	      int x;
	      int y;
	      Point() { x = 0; y = 0; }
	      Point(int a, int b) { x = a; y = b; }
	  }
	
    static class Pair {
        Point p, o;
        public Pair(Point _p, Point _o) {
            p = _p;
            o = _o;
        }
        
        public int getDis() {
            int res = (p.x-o.x)*(p.x-o.x) + (p.y-o.y)*(p.y-o.y);
            return res;
        }
    }
    
    public Point[] kClosest(Point[] points, Point origin, int k) {
        // Write your code here
        PriorityQueue<Pair> heap = new PriorityQueue<Pair>(new Cmp<Pair>());
        
        for (int i = 0; i < points.length; i++) {
            Pair pair = new Pair(points[i], origin);
            heap.offer(pair);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        
        Point[] res = new Point[k];
        for (int i = k-1; i >= 0; i--) {
            res[i] = heap.poll().p;
        }
        
        return res;
    }
    
    private static class Cmp<T extends Pair> implements Comparator<Pair>{

		@Override
		public int compare(Pair p1, Pair p2) {
            int d1 = p1.getDis(), d2 = p2.getDis();
            if (d1 == d2) {
                if (p1.p.x == p2.p.x) {
                    return p2.p.y - p1.p.y;
                }
                else {
                    return p2.p.x - p1.p.x;
                }
            }
            else {
                return d2 - d1;
            }
        }
    	
    }
    
}
