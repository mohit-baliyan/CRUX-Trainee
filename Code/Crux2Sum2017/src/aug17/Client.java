package aug17;

public class Client {

	public static void main(String[] args) {
		int[] arr = { 10, 2, 8, 27, 7, 16, -5 };

		ISegmentOperator minOperator = new ISegmentOperator() {
			@Override
			public int operation(int lsv, int rsv) {
				return Math.min(lsv, rsv);
			}

			@Override
			public int defaultValue() {
				// TODO Auto-generated method stub
				return Integer.MAX_VALUE;
			}
		};

		SegmentTree tree = new SegmentTree(arr, minOperator);

		System.out.println(tree.query(2, 5));
		tree.update(17, 3);
		System.out.println(tree.query(2, 5));

	}

}
