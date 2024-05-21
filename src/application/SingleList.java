package application;

public class SingleList<D extends Comparable<D>> {
	private Node<D> head;

	public Node<D> getHead() {
		return head;
	}

	public void setHead(Node<D> head) {
		this.head = head;
	}

	public void insertLast(Student data) {
		Node<D> nono = new Node(data);
		if (head == null) {
			insertFirst(data);
			return;
		}
		nono.setNext(null);
		Node<D> curr = head;
		while (curr.getNext() != null) {
			curr = curr.getNext();

		}
		curr.setNext(nono);
	}

	public boolean search(D data) {
		Node<D> nono = head;
		while (nono != null) {
			if (nono.getData().compareTo(data) == 0) {
				return true;
			}
			nono = nono.getNext();
		}

		return false;
	}

	public void insertFirst(Student data) {
		Node<D> nono = new Node(data);
		nono.setNext(head);
		head = nono;
	}

	public void delete(D data) {

		Node<D> prev = null;
		Node<D> curr = head;

		if (head == null) {
			System.out.println("linked list is empty");
			return;
		}

		for (; curr != null && ((Student)curr.getData()).getSeatNumber() != ((Student)data).getSeatNumber() ; prev = curr, curr = curr.getNext())
			;
		if (curr == null) {
			System.out.println("not found");
			return;
		}

		if (((Student)curr.getData()).getSeatNumber() == ((Student)data).getSeatNumber()) {
			if (curr == head) {
				head = head.getNext();
			} else
				prev.setNext(curr.getNext());
		} else
			System.out.println("not found");
	}

	public D get(int i) {
		Node<D> curr = head;
		int j = 0;
		while (curr != null) {
			if (j == i) {
				return curr.getData();
			}
			j++;
			curr = curr.getNext();
		}
		return null;
	}

	public int length() {
		int i = 0;
		Node<D> curr = head;
		while (curr != null) {
			i++;
			curr = curr.getNext();
		}
		return i;
	}

	@Override
	public String toString() {

		Node<D> newNode = head;
		String s = "";

		while (newNode != null) {

			s += newNode.getData() + "\t";
			newNode = newNode.getNext();
		}
		return s;

	}

//	public static void main(String args[]) {
//		SingleList<Integer> ll = new SingleList<>();
//
//          ll.insertFirst(5);
//          ll.insertFirst(4);
//          ll.insertFirst(3);
//          ll.insertFirst(2);
//          ll.insertFirst(1);
//		ll.insertLast(1);
//		ll.insertLast(2);
//		ll.insertLast(3);
//		ll.insertLast(4);
//		ll.insertLast(55);
//
//		ll.delete(1);
//		ll.delete(11);
//		System.out.println("the lingth is :" + ll.length());
//
//		System.out.println(ll.toString() + " ");
//
//	}
}
