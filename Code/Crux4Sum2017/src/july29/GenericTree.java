package july29;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class GenericTree {
	private class Node {
		int data;
		ArrayList<Node> children = new ArrayList<>();
	}

	private Node root;
	private int size;

	public GenericTree() {
		Scanner scn = new Scanner(System.in);
		root = takeInput(null, 0, scn);
	}

	private Node takeInput(Node parent, int childidx, Scanner scn) {
		// ask the user
		if (parent == null) {
			System.out.println("Enter the root");
		} else {
			System.out.println("Enter the data for " + childidx + " of " + parent.data);
		}

		// take input
		int data = scn.nextInt();

		// create node, set data, and increase size
		Node child = new Node();
		child.data = data;
		this.size++;

		// handle the children for the node - ask num gc
		System.out.println("Enter the number of children for " + child.data);
		int numgc = scn.nextInt();

		// handle the children for the node - create and attach gc
		for (int i = 0; i < numgc; i++) {
			Node gc = this.takeInput(child, i, scn);
			child.children.add(gc);
		}

		// return
		return child;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void display() {
		System.out.println("--------------------------------------------");
		display(root);
		System.out.println("--------------------------------------------");
	}

	private void display(Node node) {
		System.out.println(getStringForNode(node));
		for (Node child : node.children) {
			display(child);
		}
	}

	private String getStringForNode(Node node) {
		String rv = node.data + " => ";
		for (Node child : node.children) {
			rv += child.data + ", ";
		}
		rv += ".";
		return rv;
	}

	public int size2() {
		return this.size2(root);
	}

	private int size2(Node node) {
		int size = 0;

		for (Node child : node.children) {
			int csize = size2(child);
			size += csize;
		}

		size = size + 1;
		return size;
	}

	public int max() {
		return max(root);
	}

	private int max(Node node) {
		int max = node.data;
		for (Node child : node.children) {
			int cmax = max(child);
			if (cmax > max) {
				max = cmax;
			}
		}

		return max;
	}

	public int height() {
		return height(root);
	}

	private int height(Node node) {
		int height = -1;

		for (Node child : node.children) {
			int cheight = height(child);
			if (cheight > height) {
				height = cheight;
			}
		}

		return height + 1;
	}

	public boolean find(int data) {
		return find(root, data);
	}

	private boolean find(Node node, int data) {
		if (node.data == data) {
			return true;
		}

		for (Node child : node.children) {
			boolean fic = find(child, data);
			if (fic == true) {
				return true;
			}
		}

		return false;
	}

	public void mirror() {
		mirror(root);
	}

	private void mirror(Node node) {
		for (Node child : node.children) {
			mirror(child);
		}

		int li = 0;
		int ri = node.children.size() - 1;
		while (li < ri) {
			Node left = node.children.get(li);
			Node right = node.children.get(ri);

			node.children.set(li, right);
			node.children.set(ri, left);

			li++;
			ri--;
		}
	}

	public void printAtDepth(int targetDepth) {
		printAtDepth(root, targetDepth, 0);
	}

	private void printAtDepth(Node node, int targetDepth, int currentDepth) {
		if (currentDepth == targetDepth) {
			System.out.println(node.data);
			return;
		}

		for (Node child : node.children) {
			printAtDepth(child, targetDepth, currentDepth + 1);
		}
	}

	public void linear() {
		linear(root);
	}

	private void linear(Node node) {
		for (Node child : node.children) {
			linear(child);
		}

		while (node.children.size() > 1) {
			Node removed = node.children.remove(1);

			Node zerotail = node.children.get(0);
			while (zerotail.children.size() != 0) {
				zerotail = zerotail.children.get(0);
			}

			zerotail.children.add(removed);
		}

	}

	public void flatten() {
		flatten(root);
	}

	private void flatten(Node node) {
		for (Node child : node.children) {
			flatten(child);
		}

		ArrayList<Node> nloc = new ArrayList<>();
		for (Node child : node.children) {
			nloc.add(child);
			nloc.addAll(child.children);
			child.children = new ArrayList<>();
		}

		node.children = nloc;
	}

	public void removeLeaves() {
		removeLeaves(root);
	}

	private void removeLeaves(Node node) {
		for (int i = 0; i < node.children.size(); i++) {
			Node child = node.children.get(i);

			if (child.children.size() == 0) {
				node.children.remove(i);
				i--;
			}
		}

		for (Node child : node.children) {
			removeLeaves(child);
		}
	}

	public void preO() {
		preO(root);
		System.out.println(".");
	}

	private void preO(Node node) {
		System.out.print(node.data + ", ");
		for (Node child : node.children) {
			preO(child);
		}
	}

	public void postO() {
		postO(root);
		System.out.println(".");
	}

	private void postO(Node node) {
		for (Node child : node.children) {
			postO(child);
		}
		System.out.print(node.data + ", ");
	}

	public void multisolver(int datatofind) {
		HeapMover rb = new HeapMover();
		multisolver(root, rb, datatofind, 0);
		System.out.println("Size = " + rb.size);
		System.out.println("Max = " + rb.max);
		System.out.println("Min = " + rb.min);
		System.out.println("Found = " + rb.find);
		System.out.println("Height = " + rb.height);

		if (rb.pred != null) {
			System.out.println("Pred = " + rb.pred.data);
		} else {
			System.out.println("Pred = Not found");
		}

		if (rb.succ != null) {
			System.out.println("Succ = " + rb.succ.data);
		} else {
			System.out.println("Succ = Not found");
		}

		if (rb.jl != null) {
			System.out.println("JL = " + rb.jl.data);
		} else {
			System.out.println("JL = Not found");
		}
	}

	private void multisolver(Node node, HeapMover rb, int datatofind, int currentDepth) {
		rb.prev = rb.curr;
		rb.curr = node;

		if (rb.prev != null && rb.prev.data == datatofind) {
			rb.succ = rb.curr;
		}

		if (rb.curr.data == datatofind) {
			rb.pred = rb.prev;
		}

		if (node.data > datatofind) {
			if (rb.jl == null) {
				rb.jl = node;
			} else if (node.data < rb.jl.data) {
				rb.jl = node;
			}
		}

		rb.size++;

		if (node.data == datatofind) {
			rb.find = true;
		}

		if (node.data > rb.max) {
			rb.max = node.data;
		}

		if (node.data < rb.min) {
			rb.min = node.data;
		}

		if (currentDepth > rb.height) {
			rb.height = currentDepth;
		}

		for (Node child : node.children) {
			multisolver(child, rb, datatofind, currentDepth + 1);
		}
	}

	private class HeapMover {
		int size = 0;
		int height = 0;
		boolean find = false;
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		Node pred = null;
		Node succ = null;
		Node jl = null;
		Node js = null;

		Node prev = null;
		Node curr = null;
	}

	private class Pair {
		Node node;
		boolean selfDone;
		int numberOfChildrenServiced = 0;
	}

	public void preoI() {
		Stack<Pair> stack = new Stack<>();

		Pair pfr = new Pair();
		pfr.node = root;
		stack.push(pfr);

		while (stack.size() != 0) {
			Pair toppair = stack.peek();

			if (toppair.selfDone == false) {
				toppair.selfDone = true;
				System.out.print(toppair.node.data + ", ");
			} else if (toppair.numberOfChildrenServiced < toppair.node.children.size()) {
				Pair childpair = new Pair();
				childpair.node = toppair.node.children.get(toppair.numberOfChildrenServiced);
				stack.push(childpair);

				toppair.numberOfChildrenServiced++;
			} else {
				stack.pop();
			}
		}

		System.out.println(".");
	}

	public void levelo() {
		LinkedList<Node> queue = new LinkedList<>();

		queue.addLast(root);

		while (queue.size() != 0) {
			// 1.
			Node temp = queue.removeFirst();

			// 2.
			System.out.print(temp.data + ", ");

			// 3.
			for (Node child : temp.children) {
				queue.addLast(child);
			}
		}

		System.out.println(".");
	}

	public void levelolw() {
		LinkedList<Node> cl = new LinkedList<>();
		LinkedList<Node> nl = new LinkedList<>();

		cl.addLast(root);

		while (cl.size() != 0) {
			// 1.
			Node temp = cl.removeFirst();

			// 2.
			System.out.print(temp.data + ", ");

			// 3.
			for (Node child : temp.children) {
				nl.addLast(child);
			}

			// 4.
			if (cl.size() == 0) {
				cl = nl;
				nl = new LinkedList<>();
				System.out.println(".");
			}
		}

		System.out.println(".");
	}

	public void levelolwzz() {
		LinkedList<Node> cl = new LinkedList<>();
		LinkedList<Node> nl = new LinkedList<>();

		cl.addLast(root);
		int level = 0;

		while (cl.size() != 0) {
			// 1.
			Node temp = cl.removeFirst();

			// 2.
			System.out.print(temp.data + ", ");

			// 3.
			if (level % 2 == 0) {
				for (Node child : temp.children) {
					nl.addFirst(child);
				}
			} else {
				for (int i = temp.children.size() - 1; i >= 0; i--) {
					Node child = temp.children.get(i);
					nl.addFirst(child);
				}
			}

			// 4.
			if (cl.size() == 0) {
				level++;
				cl = nl;
				nl = new LinkedList<>();
				System.out.println(".");
			}
		}

		System.out.println(".");
	}

	public int justSmaller(int data) {
		HeapMover mover = new HeapMover();
		justSmaller(root, data, mover);
		return mover.js.data;
	}

	private void justSmaller(Node node, int data, HeapMover mover) {
		if (node.data < data) {
			if (mover.js == null || node.data > mover.js.data) {
				mover.js = node;
			}
		}

		for (Node child : node.children) {
			justSmaller(child, data, mover);
		}
	}

	public int kthLargest(int k) {
		int data = Integer.MAX_VALUE;
		for (int i = 0; i < k; i++) {
			data = justSmaller(data);
		}
		return data;
	}

	public boolean IsIsomorphic(GenericTree other) {
		return IsIsomorphic(this.root, other.root);
	}

	private boolean IsIsomorphic(Node thnode, Node othnode) {
		if (thnode.children.size() != othnode.children.size()) {
			return false;
		}

		for (int i = 0; i < thnode.children.size(); i++) {
			Node thchild = thnode.children.get(i);
			Node othchild = othnode.children.get(i);

			boolean areiso = this.IsIsomorphic(thchild, othchild);
			if (areiso == false) {
				return false;
			}
		}

		return true;
	}

	public boolean IsmirrorIso() {
		return IsmirrorIso(root, root);
	}

	private boolean IsmirrorIso(Node fleft, Node fright) {
		if (fleft.children.size() != fright.children.size()) {
			return false;
		}

		for (int i = 0; i < fleft.children.size(); i++) {
			Node flchild = fleft.children.get(i);
			Node frchild = fright.children.get(fright.children.size() - 1 - i);

			boolean aremirroriso = this.IsmirrorIso(flchild, frchild);
			if (aremirroriso == false) {
				return false;
			}
		}

		return true;
	}
}
