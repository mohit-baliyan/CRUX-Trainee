package july29;

public class Client {

	public static void main(String[] args) {
		// 10 3 20 2 50 0 60 0 30 3 7 2 110 0 120 0 80 0 90 2 78 0 150 0 40 1 76 0
		// 100 3 200 2 500 0 600 0 300 3 70 2 1100 0 1200 0 800 0 900 2 780 0 1500 0 400 1 760 0
		GenericTree gt = new GenericTree();
		GenericTree other = new GenericTree();
		
		System.out.println(gt.IsIsomorphic(other));
		
		gt.display();
//		gt.removeLeaves();
//		gt.display();
		
		gt.preO();
		gt.postO();
		gt.multisolver(150);
		gt.levelo();
		gt.levelolw();
		gt.levelolwzz();
	}

}
