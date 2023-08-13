package aug5;

public class BinClient {

	public static void main(String[] args) {
		// 10 true 20 true 40 true 60 false false true 70 false true 100 false
		// false false true 30 false true 50 true 80 true 110 false false false
		// true 90 false false
		// BinaryTree bt = new BinaryTree();
		// bt.display();

		// bt.preo();
		// bt.preoIterative();
		//
		// bt.ino();
		// bt.inoIterative();
		//
		// bt.posto();
		// bt.postoIterative();
		//
		// bt.levelo();
		// bt.levelolw();
		//
		// System.out.println(bt.diameter());
		// System.out.println(bt.diameter2());

		// bt.pws();
		// System.out.println(bt.levelolwLL());

		int[] posto = { 12, 30, 40, 37, 25, 60, 70, 62, 87, 75, 50 };
		int[] ino = { 12, 25, 30, 37, 40, 50, 60, 62, 70, 75, 87 };

		BinaryTree bt = new BinaryTree(posto, ino);
		bt.display();

//		bt.roottoleafpaths();
//		bt.levelolwzz();
		
		System.out.println(bt.rootToDataPath(40));
		System.out.println(bt.rootToDataPath(70));
		
		bt.lca(12, 40);
	}

}
