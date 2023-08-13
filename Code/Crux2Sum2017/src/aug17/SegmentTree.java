package aug17;

public class SegmentTree {
	private class Node {
		int si;
		int ei;
		int data;
		Node left;
		Node right;
	}

	private ISegmentOperator segmentOperator;

	Node root;
	int size;

	public SegmentTree(int[] arr, ISegmentOperator segmentOperator) {
		this.segmentOperator = segmentOperator;
		this.root = this.construct(arr, 0, arr.length - 1);
	}

	private Node construct(int[] arr, int si, int ei) {
		if (si == ei) {
			Node base = new Node();
			this.size++;
			base.si = si;
			base.ei = ei;
			base.data = arr[si];
			return base;
		}

		int mid = (si + ei) / 2;
		Node node = new Node();
		this.size++;

		node.si = si;
		node.ei = ei;
		node.left = this.construct(arr, si, mid);
		node.right = this.construct(arr, mid + 1, ei);
		node.data = this.segmentOperator.operation(node.left.data, node.right.data);

		return node;
	}

	public int query(int qsi, int qei) {
		return this.query(root, qsi, qei);
	}

	private int query(Node node, int qsi, int qei) {
		// node is completely in the query
		if (node.si >= qsi && node.ei <= qei) {
			return node.data;
		}
		// completely out
		else if (node.si > qei || node.ei < qsi) {
			return this.segmentOperator.defaultValue();
		}
		// overlap
		else {
			int leftData = this.query(node.left, qsi, qei);
			int rightData = this.query(node.right, qsi, qei);
			return this.segmentOperator.operation(leftData, rightData);
		}
	}

	public void update(int nd, int idx) {
		update(root, nd, idx);
	}

	private int update(Node node, int nd, int idx) {
		if (idx >= node.si && idx <= node.ei) {
			if (idx == node.si && idx == node.ei) {
				node.data = nd;
				return node.data;
			}

			int lsnv = update(node.left, nd, idx);
			int rsnv = update(node.right, nd, idx);
			node.data = this.segmentOperator.operation(lsnv, rsnv);
			return node.data;
		} else {
			// idx is out of node's segment
			return node.data;
		}
	}

}
