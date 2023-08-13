package july31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class GenericHeapClient {

	public static void main(String[] args) {
		// GenericHeap<Student> heap = new
		// GenericHeap<>(Student.NameComparator);
		//
		// Student[] students = new Student[7];
		// students[0] = new Student("Modi", 100, 1);
		// students[1] = new Student("Rahul", 0, Integer.MAX_VALUE);
		// students[2] = new Student("Kejri", 10, 10);
		// students[3] = new Student("ABC", 50, 50);
		// students[4] = new Student("DEF", 60, 40);
		// students[5] = new Student("GHI", 70, 30);
		// students[6] = new Student("JKL", 80, 20);
		//
		// heap.add(students[0]);
		// heap.add(students[1]);
		// heap.add(students[2]);
		// heap.add(students[3]);
		// heap.add(students[4]);
		// heap.add(students[5]);
		// heap.add(students[6]);
		//
		// heap.display();
		// while(!heap.isEmpty()){
		// System.out.println(heap.removeHP());
		// }
		//

		ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
		ArrayList<Integer> zeroth = new ArrayList<>(Arrays.asList(10, 15, 20, 25));
		ArrayList<Integer> oneth = new ArrayList<>(Arrays.asList(8, 9, 14, 28));
		ArrayList<Integer> twoth = new ArrayList<>(Arrays.asList(7, 11, 26, 30));
		ArrayList<Integer> threeth = new ArrayList<>(Arrays.asList(19, 21, 23, 27));

		lists.add(zeroth);
		lists.add(oneth);
		lists.add(twoth);
		lists.add(threeth);

		System.out.println(mergeKSortedArrayLists(lists));
		
		ArrayList<Integer> random = new ArrayList<>(Arrays.asList(10, 5, 18, 22, 98, 3, -5, 62));
		System.out.println(klargestElements(random, 3));

	}

	public static ArrayList<Integer> mergeKSortedArrayLists(ArrayList<ArrayList<Integer>> lists) {
		ArrayList<Integer> rv = new ArrayList<>();
		GenericHeap<Pair> heap = new GenericHeap<>(Pair.DataComparator);

		// fill the heap
		for (int i = 0; i < lists.size(); i++) {
			Pair p = new Pair();
			p.ino = 0;
			p.lno = i;
			p.data = lists.get(i).get(0);

			heap.add(p);
		}

		// traverse the heap
		while (!heap.isEmpty()) {
			Pair top = heap.removeHP();
			rv.add(top.data);

			top.ino++;
			if (top.ino < lists.get(top.lno).size()) {
				top.data = lists.get(top.lno).get(top.ino);
				heap.add(top);
			}
		}

		return rv;
	}

	private static class Pair {
		int data;
		int lno;
		int ino;
		private static final PairComparator DataComparator = new PairComparator();

		private static class PairComparator implements Comparator<Pair> {

			@Override
			public int compare(Pair o1, Pair o2) {
				// TODO Auto-generated method stub
				return o2.data - o1.data;
			}

		}
	}

	public static ArrayList<Integer> klargestElements(ArrayList<Integer> list, int k){
		GenericHeap<Pair> heap = new GenericHeap<>(Pair.DataComparator);
		
		for(int i = 0; i < k; i++){
			Pair p = new Pair();
			p.data = list.get(i);
			heap.add(p);
		}
		
		for(int i = k; i < list.size(); i++){
			Pair top = heap.getHP();
			if(list.get(i) > top.data){
				heap.removeHP();
				top.data = list.get(i);
				heap.add(top);
			}
		}
		
		ArrayList<Integer> rv = new ArrayList<>();
		while(heap.size() != 0){
			rv.add(heap.removeHP().data);
		}
		
		return rv;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
