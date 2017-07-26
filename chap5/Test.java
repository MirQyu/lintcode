package chap5;

public class Test {
	private static boolean isNeighbor(String node, String str) {
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
	
	public static void main(String[] args) {
		System.out.println(isNeighbor("hog", "lgo"));
	}
}
