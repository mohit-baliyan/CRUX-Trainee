package july24;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class GenericTree {
	private class Node {
		int data;
		ArrayList<Node> children = new ArrayList<>();
	}

	private Node root;
	int size;

	public GenericTree() {
		Scanner scn = new Scanner(System.in);
		this.root = this.takeInput(null, -1, scn);
	}

	private Node takeInput(Node parent, int childIdx, Scanner scn) {
		// prompt the user
		if (parent == null) {
			System.out.println("Enter the data for root");
		} else {
			System.out.println("Enter the data for " + childIdx + "th child of " + parent.data);
		}

		// get data
		int data = scn.nextInt();

		// create node && increase size
		Node child = new Node();
		this.size++;

		// set node's data
		child.data = data;

		// prompt for number of children for this node
		System.out.println("Enter the number of children for " + child.data);
		int numGC = scn.nextInt();

		// solve the same problem for children
		for (int i = 0; i < numGC; i++) {
			Node grandChild = this.takeInput(child, i, scn);
			child.children.add(grandChild);
		}

		// return
		return child;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void display() {
		display(root);
	}

	private void display(Node node) {
		String str = node.data + " => ";
		for (Node child : node.children) {
			str += child.data + ", ";
		}
		System.out.println(str + ".");

		for (Node child : node.children) {
			display(child);
		}
	}

	public int size2() {
		return size2(root);
	}

	private int size2(Node node) {
		int s = 0;
		for (Node child : node.children) {
			int cs = size2(child);
			s += cs;
		}

		s += 1; // for the node itself
		return s;
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
		int h = -1;

		for (Node child : node.children) {
			int ch = height(child);
			if (ch > h) {
				h = ch;
			}
		}

		return h + 1; // for the distance between node and children
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

		int li = 0, ri = node.children.size() - 1;
		while (li < ri) {
			Node left = node.children.get(li);
			Node right = node.children.get(ri);

			node.children.set(li, right);
			node.children.set(ri, left);

			li++;
			ri--;
		}
	}

	public void preOrder() {
		preOrder(root);
		System.out.println(".");
	}

	private void preOrder(Node node) {
		System.out.print(node.data + ", ");
		for (Node child : node.children) {
			preOrder(child);
		}
	}

	public void postOrder() {
		postOrder(root);
		System.out.println(".");
	}

	private void postOrder(Node node) {
		for (Node child : node.children) {
			postOrder(child);
		}
		System.out.print(node.data + ", ");
	}

	public void levelOrder() {
		LinkedList<Node> queue = new LinkedList<>();
		queue.addLast(root);

		while (queue.size() != 0) {
			Node rem = queue.removeFirst();
			System.out.print(rem.data + ", ");
			
			for(Node child: rem.children){
				queue.addLast(child);
			}
		}
		System.out.println(".");
	}
	
	public void levelOrderLW() {
		LinkedList<Node> queue = new LinkedList<>();
		LinkedList<Node> nlqueue = new LinkedList<>();
		queue.addLast(root);
		
		while (queue.size() != 0) {
			Node rem = queue.removeFirst();
			System.out.print(rem.data + ", ");
			
			for(Node child: rem.children){
				nlqueue.addLast(child);
			}
			
			if(queue.size() == 0){
				queue = nlqueue;
				nlqueue = new LinkedList<>();
				System.out.println();
			}
		}
		System.out.println(".");
	}
}
