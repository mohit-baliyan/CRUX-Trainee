package trees;

public class GenericClient {
	public static void main(String[] args) {
// 10 3 20 2 50 0 60 0 30 3 70 2 110 0 120 0 80 0 90 2 140 0 150 0 40 1 100 0 
// 100 3 200 2 500 0 600 0 300 3 700 2 1100 0 1200 0 800 0 900 2 1400 0 1500 0 400 1 1000 0
		GenericTree gt1 = new GenericTree();
		GenericTree gt2 = new GenericTree();
		

		System.out.println(gt1.iso(gt2));
		
	}
}
