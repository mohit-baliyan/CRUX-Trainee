package aug20;

import java.util.ArrayList;

public class GenericClient {

	public static void main(String[] args) throws Exception {
		// LinkedList<String> list = new LinkedList<>();
		//
		// list.addLast("Hi");
		// list.addLast("Bye");
		// list.addLast("Hello");
		// list.addLast("Shello");
		//
		// list.display();
		//
		// int[] arr = { 20, 5, 17, 8, 16, 22, 42, 9, 34, 50 };
		// System.out.println(getKLargestElements(arr, 3));

//		HashMap<String, Integer> map = new HashMap<>();
//
//		map.put("India", 120);
//		map.put("China", 200);
//		map.put("Pak", 80);
//		map.put("US", 25);
//		map.put("UK", 20);
//		map.put("Nigeria", 50);
//
//		map.display();
//
//		map.put("India", 125);
//		map.put("Sweden", 15);
//
//		map.display();
//
//		System.out.println(map.get("US"));
//		System.out.println(map.get("Norway"));
//
//		System.out.println(map.containsKey("US"));
//		System.out.println(map.containsKey("Norway"));
//
//		System.out.println(map.remove("Pak"));
//		System.out.println(map.remove("Norway"));
//
//		map.display();
//		System.out.println(map.keyset());
//
//		map.put("France", 40);
//		map.put("Norway", 20);
//
//		map.display();
//
//		map.put("Denmark", 18);
//		map.display();
		
		HEncoder enc = new HEncoder("andsbmaslkadjfalksdsjflaksdujntff asdlkqwioptuqeujsdfdnvq98revnnz,xmcxcnkyfjalksdijfa alskdsdjflkasjdf aslkddjflaksdsjsfmkmkdndvo;qqeuefnkm ,zmxcxnvoqwqewruefo ");
		System.out.println(enc.compress("my name is sumeet malik"));
		System.out.println(enc.decompress("1011011111110011011000110010110101010110111111100000110100010100101101010110101111110001101101101100011111111101110"));
	}

	private static class Pair implements Comparable<Pair> {
		int data;
		int listno;
		int itemno;

		public Pair(int data, int listno, int itemno) {
			this.data = data;
			this.listno = listno;
			this.itemno = itemno;
		}

		@Override
		public int compareTo(Pair o) {
			return this.data - o.data;
		}

	}

	public static ArrayList<Integer> getKLargestElements(int[] arr, int k) {
		Heap<Pair> pq = new Heap<>();

		for (int i = 0; i < k; i++) {
			Pair p = new Pair(arr[i], 0, 0);
			pq.addHP(p);
		}

		for (int i = k; i < arr.length; i++) {
			Pair rp = pq.removeHP();

			if (arr[i] > rp.data) {
				rp.data = arr[i];
			}

			pq.addHP(rp);
		}

		ArrayList<Integer> list = new ArrayList<>();
		while (pq.size() != 0) {
			Pair rp = pq.removeHP();
			list.add(rp.data);
		}

		return list;
	}

}
