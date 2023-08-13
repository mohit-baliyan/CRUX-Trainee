package sep1;

import java.util.Stack;

public class Client {

	public static void main(String[] args) {
//		Stack<Character> st = new Stack<>();
//		
//		String exp = "((a + b) + ((c + d)))";
//		for(int i = 0; i < exp.length(); i++){
//			Character ch = exp.charAt(i);
//			
//			if(ch == ')'){
//				if(st.peek() == '('){
//					System.out.println(true);
//					break;
//				} else {
//					while(st.peek() != '('){
//						st.pop();
//					}
//					st.pop();
//				}
//			} else {
//				st.push(ch);
//			}
//		}
		
		int[] prices = {25, 30, 35, 40, 38, 39, 42, 35, 37, 39};
		stockspans(prices);
	}
	
	public static int[] stockspans(int[] prices){
		Stack<Integer> st = new Stack<>();
		int[] spans = new int[prices.length];
		
		st.push(0);
		spans[0] = 1;
		
		for(int i = 1; i < prices.length; i++){
			// pops the smaller items
			while(prices[st.peek()] < prices[i]){
				st.pop();
				if(st.size() == 0){
					break;
				}
			}
			
			// calculate span
			if(st.size() == 0){
				spans[i] = i + 1;
			} else {
				spans[i] = i - st.peek();
			}
			
			// push
			st.push(i);
		}
		
		return spans;
	}

}
