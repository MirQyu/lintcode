package nowcoder;

import java.util.Scanner;
import java.util.Stack;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long res = 0;
		int n = in.nextInt();
		
		String str = "Y";
		Stack<String> stack = new Stack<>();
		stack.push(str);
		while (!stack.empty() && n != 1) {
			
			str = stack.pop();
			int size = str.length() + 1;
			String s1 = str + "N", s2 = null;
			int j = (size >> 1);
			
			if ((j << 1) == size && str.charAt(j-1) == 'Y' || (j << 1) != size) {
				s2 = str + "Y";
			}
			
			if (size != n) {
				if (s2 != null) {
					stack.push(s2);
				}
				stack.push(s1);
			}
			else {
				//System.out.println(s1);
				res++;
				if (s2 != null) {
					//System.out.println(s2);
					res++;
				}
				if (res > 1000000007) {
					res %= 1000000007;
				}
			}
			
		}
		System.out.println(res);
	}
	
	
	public static void pra2() {
		Scanner in = new Scanner(System.in);
		String line = in.nextLine();
		int [] times = new int[26];
//		boolean flag = false;
		for (int i = 0; i < line.length(); i++) {
			times[line.charAt(i) - 'a']++;
		}
		
		int sum = 0;
		
		for (int i = 0; i < times.length; i++) {
//			if (times[i] != 0 && times[i] % 2 == 0) {
//				flag = true;
//			}
			times[i] %= 2;
			
			sum += times[i];
		}
		
		if (sum == 0 || sum == 1) {
			System.out.println(1);
		}
		else {
			System.out.println(sum);
		}
	}
	
	public static void pra1() {
		Scanner in = new Scanner(System.in);
		
		String line = in.nextLine();
		
		do {
			line = line.substring(0, line.length() - 2);
			if (isEeven(line)) {
				System.out.println(line.length());
				break;
			}
		} while (!line.equals(""));
	}
	
	public static boolean isEeven(String str) {
		int mid = str.length() / 2;
		
		for (int i = 0; mid < str.length(); i++, mid++) {
			if (str.charAt(i) != str.charAt(mid)) {
				return false;
			}
		}
		
		return true;
	}

}
