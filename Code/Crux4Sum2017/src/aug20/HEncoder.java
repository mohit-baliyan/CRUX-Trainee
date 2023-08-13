package aug20;

import java.util.ArrayList;

public class HEncoder {
	private HashMap<Character, String> encoder = new HashMap<>();
	private HashMap<String, Character> decoder = new HashMap<>();
	
	private class Node implements Comparable<Node> {
		Character data;
		int freq;
		Node left;
		Node right;
		
		@Override
		public int compareTo(Node o) {
			return this.freq - o.freq;
		}
	}
	
	public HEncoder(String feeder) throws Exception{
		// 1. freq map
		HashMap<Character, Integer> fmap = new HashMap<>();
		for(int i = 0; i < feeder.length(); i++){
			Character ch = feeder.charAt(i);
			if(fmap.containsKey(ch)){
				fmap.put(ch, fmap.get(ch) + 1);
			} else {
				fmap.put(ch, 1);
			}
		}
		
		// 2. prepare heap
		Heap<Node> heap = new Heap<>();
		ArrayList<Character> keys = new ArrayList<>(fmap.keyset());
		for(Character key: keys){
			Node node = new Node();
			node.data = key;
			node.freq = fmap.get(key);
			heap.addHP(node);
		}
		
		// 3. till the size is 1, remove two, merge and add back
		while(heap.size() > 1){
			Node one = heap.removeHP();
			Node two = heap.removeHP();
			
			Node merged = new Node();
			merged.freq = one.freq + two.freq;
			merged.left = one;
			merged.right = two;
			
			heap.addHP(merged);
		}
		
		// 4. get the final node
		Node fn = heap.removeHP();
		
		// 5. traverse, to fill enc and dec
		traverse(fn, "");
	}
	
	private void traverse(Node node, String psf) throws Exception {
		if(node == null){
			return;
		}
		
		if(node.left == null && node.right == null){
			encoder.put(node.data, psf);
			decoder.put(psf, node.data);
		}
		
		traverse(node.left, psf + 0);
		traverse(node.right, psf + 1);
	}
	
	// abcdabcd => 101001000101001000
	public String compress(String str) throws Exception {
		String rv = "";
		
		for(int i = 0; i < str.length(); i++){
			Character ch = str.charAt(i);
			rv += encoder.get(ch);
		}
		
		return rv;
	}
	
	// 101001000101001000 => abcdabcd
	public String decompress(String str) throws Exception {
		String rv = "";
		
		String code = "";
		for(int i = 0; i < str.length(); i++){
			Character ch = str.charAt(i);
			code += ch;
			
			if(decoder.containsKey(code)){
				rv += decoder.get(code);
				code = "";
			}
		}
		
		return rv;
	}
}
