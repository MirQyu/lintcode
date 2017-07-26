package chap5;

import java.util.*;

public class WordLadder {

	// 只通过 83%
	Map<String, ArrayList<String>> graph = new HashMap<>();
    List<List<String>> list = new ArrayList<List<String>>();
    int depth;
    
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here  
        depth = bfs(start, end, dict, graph);
        
        
        if (depth == -1) {
            return list;
        }
        
        List<String> path = new ArrayList<>();
        path.add(start);
        dfs(start, end, path);
        
        return list;
    }
    
    private void dfs(String start, String end, List<String> path) {
        if (start.equals(end)) {
            list.add(new ArrayList<String>(path));
            return;
        }
        
        if (path.size() == depth) {
            return;
        }
        
        List<String> neighbor = graph.get(start);
        
        for (int i = 0; i < neighbor.size(); i++) {
            String next = neighbor.get(i);
            path.add(next);
            dfs(next, end, path);
            path.remove(path.size() - 1);
        }
    }
    
    
    private int bfs(String start, String end, Set<String> dict, Map<String, ArrayList<String>> graph) {
        // 过滤之前已经出现过的string--节点
        //Set<String> set = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        dict.remove(start);
        //set.add(start);
        boolean arrived = false;
        int depth = 1;
        
        while (!queue.isEmpty() && !arrived) {
            int size = queue.size();
            //当前的层数
            depth++;
            // 本层
            for (int i = 0; i < size; i++) {
                String node = queue.poll();
                ArrayList<String> neighbor = new ArrayList<>();
                
                // 查找该节点的邻接点
                for (String next : dict) {
                    if (!isNeighbor(node,next)) {
                        continue;
                    }
                    
                    if (next.equals(end)) {
                        arrived = true;
                        neighbor.clear();
                        neighbor.add(next);
                        break;
                    }
                    
                    neighbor.add(next);
                    
                    
                    // 节点加入到下一层中
                    queue.offer(next);
                }
                
                graph.put(node, neighbor);
            }
            
            dict.removeAll(queue);
            //set.addAll(queue);
        }
        
        if (!arrived) {
            depth = -1;
        }
        
        return depth;
        
    }
    
    private boolean isNeighbor(String node, String str) {
        int time = 0;
        
        for (int i = 0; i < node.length(); i++) {
            if (node.charAt(i) != str.charAt(i)) {
                time++;
                if (time > 1) {
                    return false;
                }
            }
            
        }
        
        if (time == 1) {
            return true;
        }
        
        return false;
        
    }
	
}
