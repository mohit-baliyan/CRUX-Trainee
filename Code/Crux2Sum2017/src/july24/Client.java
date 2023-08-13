package july24;

public class Client {

	public static void main(String[] args) {
		// 10 3 20 2 50 0 60 0 30 3 70 2 110 0 120 0 80 0 90 2 140 0 150 0 40 1 100 0
		GenericTree gt = new GenericTree();
		gt.display();
		
		System.out.println(gt.size2());
		System.out.println(gt.find(120));
		System.out.println(gt.find(200));
		System.out.println(gt.max());
		System.out.println(gt.height());
//		
//		gt.mirror();
//		gt.display();
		
		gt.preOrder();
		gt.postOrder();
		gt.levelOrderLW();
		
	}

}
