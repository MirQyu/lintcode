package chap5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Solution {
    /**
      * @param start, a string
      * @param end, a string
      * @param dict, a set of string
      * @return a list of lists of string
      */
      
    public static class UndirectedGraph {
        String node;
        ArrayList<String> neighbors;
        
        public UndirectedGraph(String node) {
            this.node = node;
            neighbors = new ArrayList<>();
        }
    }
    
    int min = Integer.MAX_VALUE;
    
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here  
        List<List<String>> result = new ArrayList<>();
        
        Map<String, UndirectedGraph> graph = new HashMap<>();
        
        for (String word : dict) {
            UndirectedGraph node = new UndirectedGraph(word);
            graph.put(word, node);
        }
        
        ArrayList<String> list = new ArrayList<>(dict);
        for (int i = 0; i < list.size(); i++) {
            String w1 = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                String w2 = list.get(j);
                int gap = distance(w1, w2);
                if (gap == 1) {
                    UndirectedGraph node1 = graph.get(w1);
                    UndirectedGraph node2 = graph.get(w2);
                    node1.neighbors.add(w2);
                    node2.neighbors.add(w1);
                }
            }
        }
        
        List<String> seq = new ArrayList<String>();
        seq.add(start);
        
        dfs(start, end, graph, new HashSet<String>(), seq, result);
        
        List<List<String>> ans = new ArrayList<>();
        
        for (int i = result.size() - 1; i > -1; i--) {
            if (result.get(i).size() == min) {
                ans.add(result.get(i));
            }
        }
        
        return ans;
        
    }
    
//    public void bfs(String start, String end, Map<String, UndirectedGraph> grap, List<List<String>> result) {
//        
//        int distance = 0;
//        
//        
//        Set<String> visited = new HashSet<>();
//        visited.add(start);
//        Map<String, List<String>> paths = new HashMap<>();
//        List<String> path = new ArrayList<>();
//        path.add(start);
//        paths.put(start, path);
//        Queue<String> queue = new LinkedList<>();
//        queue.offer(start);
//        
//        boolean arrive = false;
//        
//        while (!queue.isEmpty() && !arrive) {
//            int size = queue.size();
//            distance++;
//            for (int i = 0; i < size; i++) {
//                String word = queue.poll();
//                List<String> path = paths.get(word);
//                UndirectedGraph node = graph.get(word);
//                ArrayList<String> neighbors = node.neighbors;
//                for (int j = 0; j < neighbors.size(); j++) {
//                    String neighbor = neighbors.get(j);
//                    
//                    if (neighbor.equals(end)) {
//                        arrive = true;
//                    }
//                    
//                    if (visited.contains(neighbor)) {
//                        continue;
//                    }
//                    
//                    
//                    
//                    
//                    List<String> nextPath = new ArrayList<>(path);
//                    
//                    
//                }
//            }
//        }
//    }
    
    
    public void dfs(String start, String end, Map<String, UndirectedGraph> graph, Set<String> visited, List<String> list, List<List<String>> result) {
        
        if (start.equals(end)) {
            result.add(new ArrayList<>(list));
            if (list.size() < min) {
                min = list.size();
            }
            return;
        }
        
        UndirectedGraph node = graph.get(start);
        
        ArrayList<String> neighbors = node.neighbors;
        
        
        for (int i = 0; i < neighbors.size(); i++) {
            String neighbor = neighbors.get(i);
            if (visited.contains(neighbor)) {
                continue;
            }
            
            list.add(neighbor);
            visited.add(neighbor);
            dfs(neighbor, end, graph, visited, list, result);
            visited.remove(neighbor);
            list.remove(list.size() - 1);
        }
    }
    
    public int distance(String w1, String w2) {
        int ans = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                ans++;
            }
            
            if (ans > 1) {
                return ans;
            }
        }
        
        return ans;
    }
}