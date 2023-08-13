package aug20;

public class LinkedList<T> {
	private class Node {
		private T data;
		private Node next;
	}

	private Node head;
	private Node tail;
	private int size;

	// o1
	public void addLast(T value) {
		Node node = new Node();
		node.data = value;
		node.next = null;

		if (this.size == 0) {
			this.head = node;
			this.tail = node;
		} else {
			this.tail.next = node;
			this.tail = node;
		}

		this.size++;
	}

	// o1
	public void addFirst(T value) {
		Node node = new Node();
		node.data = value;

		if (this.size == 0) {
			this.head = node;
			this.tail = node;
		} else {
			node.next = this.head;
			this.head = node;
		}

		this.size++;
	}

	// on
	public void addAt(T value, int idx) throws Exception {
		if (idx < 0 || idx > size) {
			throw new Exception("Out of bound");
		}

		if (idx == 0) {
			addFirst(value);
		} else if (idx == size) {
			addLast(value);
		} else {
			Node node = new Node();
			node.data = value;

			Node m1 = getNodeAt(idx - 1);
			Node p1 = m1.next;

			m1.next = node;
			node.next = p1;

			this.size++;
		}
	}

	// o1
	public int size() {
		return this.size;
	}

	// o1
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		} else {
			return false;
		}
	}

	// on
	public void display() {
		System.out.println("---------------------------------------");

		Node node = this.head;
		while (node != null) {
			System.out.print(node.data + ", ");
			node = node.next;
		}
		System.out.println(".");

		System.out.println("---------------------------------------");
	}

	// o1
	public T getFirst() throws Exception {
		if (size == 0) {
			throw new Exception("List is empty");
		}
		return head.data;
	}

	// o1
	public T getLast() throws Exception {
		if (size == 0) {
			throw new Exception("List is empty");
		}
		return tail.data;
	}

	// on
	public T getAt(int idx) throws Exception {
		if (size == 0) {
			throw new Exception("List is empty");
		}

		if (idx < 0 || idx >= this.size) {
			throw new Exception("Out of bound");
		}

		Node temp = this.head;
		for (int i = 0; i < idx; i++) {
			temp = temp.next;
		}

		return temp.data;
	}

	// on
	private Node getNodeAt(int idx) throws Exception {
		if (size == 0) {
			throw new Exception("List is empty");
		}

		if (idx < 0 || idx >= this.size) {
			throw new Exception("Out of bound");
		}

		Node temp = this.head;
		for (int i = 0; i < idx; i++) {
			temp = temp.next;
		}

		return temp;
	}

	// o1
	public T removeFirst() throws Exception {
		if (size == 0) {
			throw new Exception("List is empty");
		}

		T rv = this.head.data;

		if (size == 1) {
			this.head = this.tail = null;
		} else {
			this.head = this.head.next;
		}

		this.size--;

		return rv;
	}

	// on
	public T removeLast() throws Exception {
		if (size == 0) {
			throw new Exception("List is empty");
		}

		T rv = this.tail.data;

		if (size == 1) {
			this.head = this.tail = null;
		} else {
			this.tail = getNodeAt(size - 2);
			this.tail.next = null;
		}

		this.size--;

		return rv;
	}

	// on
	public T removeAt(int idx) throws Exception {
		if (size == 0) {
			throw new Exception("List is empty");
		}

		if (idx < 0 || idx >= this.size) {
			throw new Exception("Out of bound");
		}

		if (idx == 0) {
			return removeFirst();
		} else if (idx == size - 1) {
			return removeLast();
		} else {
			Node m1 = getNodeAt(idx - 1);
			Node curr = m1.next;
			Node p1 = curr.next;

			m1.next = p1;
			this.size--;

			return curr.data;
		}

	}
}
