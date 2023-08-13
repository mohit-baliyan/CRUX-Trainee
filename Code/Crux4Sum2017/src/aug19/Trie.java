package aug19;

import java.util.ArrayList;
import java.util.HashMap;

public class Trie {
	private class Node {
		Character data;
		boolean eow;
		HashMap<Character, Node> children = new HashMap<>();
	}
	
	private Node root = new Node();
	private int numWords = 0;
	private int numNodes = 1;
	
	public void addWord(String word){
		addWord(root, word);
	}
	
	private void addWord(Node parent, String word){
		if(word.length() == 0){
			parent.eow = true;
			this.numWords++;
			return;
		}
		
		Character ch = word.charAt(0);
		String ros = word.substring(1);
		
		Node child = parent.children.get(ch);
		if(child == null){
			this.numNodes++;
			
			child = new Node();
			child.data = ch;
			parent.children.put(ch, child);
			
			addWord(child, ros);
		} else {
			addWord(child, ros);
		}
	}

	public boolean searchWord(String word){
		return searchWord(root, word);
	}
	
	private boolean searchWord(Node parent, String word){
		if(word.length() == 0){
			return parent.eow;
		}
		
		Character ch = word.charAt(0);
		String ros = word.substring(1);
		
		Node child = parent.children.get(ch);
		if(child == null){
			return false;
		} else {
			return searchWord(child, ros);
		}
	}

	public void removeWord(String word){
		removeWord(root, word);
	}
	
	private void removeWord(Node parent, String word){
		if(word.length() == 0){
			parent.eow = false;
			this.numWords--;
			return;
		}
		
		Character ch = word.charAt(0);
		String ros = word.substring(1);
		
		Node child = parent.children.get(ch);
		
		if(child == null){
			return;
		} else {
			removeWord(child, ros);

			// child is not a word, neither part of one
			if(child.eow == false && child.children.size() == 0){
				parent.children.remove(ch);
				this.numNodes--;
			}
		}
		
	}

	public void displayAllWords(){
		displayAllWords(root, "");
	}
	
	private void displayAllWords(Node node, String wsf){
		if(node.eow){
			System.out.println(wsf);
		}
		
		ArrayList<Character> keys = new ArrayList<>(node.children.keySet());
		for(Character key: keys){
			Node keywalachild = node.children.get(key);
			displayAllWords(keywalachild, wsf + key);
		}
		
		
		
		
		
	}
}
