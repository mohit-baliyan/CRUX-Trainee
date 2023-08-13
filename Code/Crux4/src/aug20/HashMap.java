package aug20;

import java.util.ArrayList;

public class HashMap<K, V> {
	private class HMNode {
		K Key;
		V value;
	}

	private LinkedList<HMNode>[] buckets; // N = buckets.length
	private int size; // n

	public HashMap() {
		this.buckets = new LinkedList[4];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<>();
		}
		this.size = 0;
	}

	private int hashFunction(K key) {
		int hc = key.hashCode();
		hc = Math.abs(hc);
		int bi = hc % buckets.length;
		return bi;
	}

	private int findWithinBucket(K key, int bi) throws Exception {
		for (int i = 0; i < buckets[bi].size(); i++) {
			HMNode node = buckets[bi].getAt(i);
			if (node.Key.equals(key)) {
				return i;
			}
		}

		return -1;
	}

	public void put(K key, V value) throws Exception {
		int bi = hashFunction(key);
		int foundinbucketat = findWithinBucket(key, bi);

		if (foundinbucketat == -1) {
			HMNode n2insert = new HMNode();
			n2insert.Key = key;
			n2insert.value = value;
			buckets[bi].addLast(n2insert);
			this.size++;
		} else {
			HMNode n2update = buckets[bi].getAt(foundinbucketat);
			n2update.value = value;
		}

		// lambda
		double lambda = (this.size * 1.0) / this.buckets.length;
		if(lambda > 2.0){
			this.rehash();
		}
	}

	private void rehash() throws Exception {
		// save old data
		LinkedList<HMNode>[] oba = this.buckets;
		
		// ctor logic
		this.buckets = new LinkedList[2 * oba.length];
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<>();
		}
		this.size = 0;
		
		// keyset logic on oba
		for (int i = 0; i < oba.length; i++) {
			for (int j = 0; j < oba[i].size(); j++) {
				HMNode node = oba[i].getAt(j);
				put(node.Key, node.value);
			}
		}
	}

	public V get(K key) throws Exception {
		int bi = hashFunction(key);
		int foundinbucketat = findWithinBucket(key, bi);

		if (foundinbucketat == -1) {
			return null;
		} else {
			return buckets[bi].getAt(foundinbucketat).value;
		}
	}

	public V remove(K key) throws Exception {
		int bi = hashFunction(key);
		int foundinbucketat = findWithinBucket(key, bi);

		if (foundinbucketat == -1) {
			return null;
		} else {
			this.size--;
			return buckets[bi].removeAt(foundinbucketat).value;
		}
	}

	public boolean containsKey(K key) throws Exception {
		int bi = hashFunction(key);
		int foundinbucketat = findWithinBucket(key, bi);

		if (foundinbucketat == -1) {
			return false;
		} else {
			return true;
		}
	}

	public ArrayList<K> keyset() throws Exception {
		ArrayList<K> list = new ArrayList<>();

		for (int i = 0; i < buckets.length; i++) {
			for (int j = 0; j < buckets[i].size(); j++) {
				HMNode node = buckets[i].getAt(j);
				list.add(node.Key);
			}
		}

		return list;
	}

	public void display() throws Exception {
		System.out.println("---------------------------------");
		for (int i = 0; i < buckets.length; i++) {
			String str = "B" + i + " => ";
			for (int j = 0; j < buckets[i].size(); j++) {
				HMNode node = buckets[i].getAt(j);
				str += "[" + node.value + "@" + node.Key + "], ";
			}
			System.out.println(str + ".");
		}
		System.out.println("---------------------------------");
	}
}
