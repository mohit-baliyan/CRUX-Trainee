package aug19;

public class TrieClient {

	public static void main(String[] args) {
		Trie trie = new Trie();
		
		trie.addWord("art");
		trie.addWord("arc");
		trie.addWord("and");
		trie.addWord("ant");
		trie.addWord("be");
		trie.addWord("bet");
		trie.addWord("bee");
		trie.addWord("sea");
		trie.addWord("see");
		trie.addWord("seen");
		
		System.out.println(trie.searchWord("art"));
		System.out.println(trie.searchWord("bell"));
		
		trie.removeWord("sea");
		
		trie.displayAllWords();
		
		
	}

}
