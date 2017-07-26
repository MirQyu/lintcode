package core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;

public class Test {
	public static void main(String[] args) {
//		Map<String, ArrayList<String>> map = new HashMap<>();
//		System.out.println(1 + ((10 - 2) >>> 1));
		
		PriorityQueue<Integer> set = new PriorityQueue<>();
		Random random = new Random(100);
		for (int i = 0; i < 100; i++) {
			set.add(random.nextInt(100));
			while (set.size() > 10) {
				set.poll();
			}
		}
		
		for (Integer id : set) {
			System.out.println(id);
		}
		
//		Iterator<Integer> iter = set.iterator();
//		while (iter.hasNext()) {
//			System.out.println(iter.next());
//		}
	}
	
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
        PriorityQueue<Pair> heap = new PriorityQueue<>(new Cmp());
        
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
