package aug19;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Client {

	public static void main(String[] args) {
		// Car[] cars = new Car[7];
		// cars[0] = new Car(100, 1000, "Merc");
		// cars[1] = new Car(200, 1500, "ABC");
		// cars[2] = new Car(250, 2000, "DEF");
		// cars[3] = new Car(120, 1900, "GHI");
		// cars[4] = new Car(180, 1600, "JKL");
		// cars[5] = new Car(220, 2200, "MNO");
		// cars[6] = new Car(80, 1400, "PQR");
		//
		// PriorityQueue<Car> queue = new PriorityQueue<>();
		// for (Car car : cars) {
		// queue.add(car);
		// }
		//
		// int counter = 0;
		// while (queue.size() != 0) {
		// cars[counter] = queue.remove();
		// counter++;
		// }
		//
		// for (Car car : cars) {
		// System.out.println(car.name + ", Speed = " + car.speed + ", Price = "
		// + car.price);
		// }

//		ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
//		lists.add(new ArrayList<>());
//		lists.add(new ArrayList<>());
//		lists.add(new ArrayList<>());
//		lists.add(new ArrayList<>());
//
//		lists.get(0).add(10);
//		lists.get(0).add(20);
//		lists.get(0).add(30);
//		lists.get(0).add(40);
//
//		lists.get(1).add(12);
//		lists.get(1).add(14);
//		lists.get(1).add(18);
//		lists.get(1).add(42);
//
//		lists.get(2).add(5);
//		lists.get(2).add(7);
//		lists.get(2).add(15);
//		lists.get(2).add(16);
//
//		lists.get(3).add(11);
//		lists.get(3).add(17);
//		lists.get(3).add(21);
//		lists.get(3).add(50);
//
//		System.out.println(mergeksortedlists(lists));
		
		int[] arr = {20, 5, 17, 8, 16, 22, 42, 9, 34, 50};
		System.out.println(getKLargestElements(arr, 4));
	}

	private static class Car implements Comparable<Car> {
		int speed;
		int price;
		String name;

		public Car(int speed, int price, String name) {
			this.speed = speed;
			this.price = price;
			this.name = name;
		}

		@Override
		public int compareTo(Car o) {
			return o.price - this.price;
		}
	}

	public static ArrayList<Integer> mergeksortedlists(ArrayList<ArrayList<Integer>> lists) {
		ArrayList<Integer> merged = new ArrayList<>();
		PriorityQueue<Pair> pq = new PriorityQueue<>();

		for (int i = 0; i < lists.size(); i++) {
			Pair p = new Pair(lists.get(i).get(0), i, 0);
			pq.add(p);
		}

		while (pq.size() != 0) {
			Pair rp = pq.remove();
			merged.add(rp.data);

			rp.itemno++;
			if (rp.itemno < lists.get(rp.listno).size()) {
				rp.data = lists.get(rp.listno).get(rp.itemno);
				pq.add(rp);
			}
		}

		return merged;
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

	public static ArrayList<Integer> getKLargestElements(int[] arr, int k){
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		
		for(int i = 0; i < k; i++){
			Pair p = new Pair(arr[i], 0, 0);
			pq.add(p);
		}
		
		for(int i = k; i < arr.length; i++){
			Pair rp = pq.remove();
			
			if(arr[i] > rp.data){
				rp.data = arr[i];
			}
			
			pq.add(rp);
		}
		
		ArrayList<Integer> list = new ArrayList<>();
		while(pq.size() != 0){
			Pair rp = pq.remove();
			list.add(rp.data);
		}
		
		return list;
	}
	
	
	
}
