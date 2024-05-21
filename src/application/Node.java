package application;

public class Node<D extends Comparable<D>> {

	private D data;
	private Node<D> next;

	public Node(D data) {
		this.data = data;

	}

	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}

	public D getData() {
		return data;
	}

	public void setData(D data) {
		this.data = data;
	}

	public Node<D> getNext() {
		return next;
	}

	public void setNext(Node<D> next) {
		this.next = next;
	}

}
