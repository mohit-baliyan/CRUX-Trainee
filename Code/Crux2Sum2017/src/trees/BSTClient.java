package trees;

public class BSTClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] sa = {12, 25, 30, 37, 40, 50, 60, 63, 70, 75, 88};
		BST bst = new BST(sa);
		bst.printTargetSum(100);
	}

}
