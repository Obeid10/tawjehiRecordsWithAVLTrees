package application;

public class CircularDoubleList<D extends Comparable<D>> {
	DNode<D> head;

	public CircularDoubleList() {
		super();
	}

	public void insertEnd(D data) {
		if (head == null) {
			head = new DNode<>(data);
			head.setNext(head);
			head.setPrev(head);
		} else {
			DNode<D> nono = new DNode<>(data);
			DNode<D> curr = head.prev;
			curr.setNext(nono);
			nono.setPrev(curr);
			nono.setNext(head);
			head.setPrev(nono);
		}
	}

	public DNode get(int index) {
		if (index < 0)
			throw new IllegalArgumentException("Index cannot be negative");
		if (head == null)
			throw new IllegalStateException("List is empty");
		DNode curr = head;
		int count = 0;
		while (count < index) {
			curr = curr.next;
			count++;
			if (curr == head) {
				throw new IllegalArgumentException("Index out of bounds");
			}
		}
		return curr;
	}

	public void travers() {
		DNode<D> curr = head;
		if (head != null) {
			while (curr != null) {
				System.out.print(curr.getData() + "<-->");
				curr = curr.getNext();
				if (curr == head)
					break;
			}
		} else
			System.out.println("Emptyyy");
	}

	public String print() {
		DNode<D> curr = head;
		StringBuilder sb = new StringBuilder();
		if (head != null) {
			while (curr != null) {
				sb.append(curr.getData() + "<-->");
				curr = curr.getNext();
				if (curr == head)
					break;
			}
		} else {
			return "Empty";
		}
		return sb.toString();
	}

	public void deleteD(D data) {
		if (head != null) {
			if (head.getData().equals(data)) {
				DNode curr = head;
				while (curr.getNext() != head) {
					curr = curr.getNext();
				}
				curr.setNext(head.getNext());
				head = head.getNext();
				head.setPrev(curr);
			} else {
				DNode roro = head;
				while ((roro.getNext() != head) && (!roro.getData().equals(data))) {
					roro = roro.getNext();
				}
				if (roro.getData().equals(data)) {
					roro.getPrev().setNext(roro.getNext());
					roro.getNext().setPrev(roro.getPrev());
				} else
					System.out.println("Not exists");
			}
		}
	}

	public void insertHead(D data) {
		DNode nono = new DNode<>(data);
		DNode curr = head;

		nono.setNext(head);
		head.setPrev(nono);
		if (head != null) {
			while (curr.getNext() != head) {
				curr = curr.getNext();
			}
			curr.setNext(nono);
			nono.setPrev(curr);
		}
		head = nono;
	}

	public boolean searched(D data) {

		DNode<D> curr = head;
		if (head != null) {
			if (((Student) head.getData()).getSeatNumber() == (((Student) data).getSeatNumber()))
				return true;
			while (curr.getNext() != head) {
				curr = curr.getNext();
				if (((Student) curr.getData()).getSeatNumber() == (((Student) data).getSeatNumber()))
					return true;
				if (curr == head)
					break;

			}
		}
		return false;
	}

	public boolean findID(int data) {

		DNode<D> curr = head;
		if (head != null) {
			if (((Student) head.getData()).getSeatNumber() == data)
				return true;
			while (curr.getNext() != head) {
				curr = curr.getNext();
				if (((Student) curr.getData()).getSeatNumber() == data)
					return true;
				if (curr == head)
					break;

			}
		}
		return false;
	}

	public DNode searchedID(int data) {

		DNode<D> curr = head;
		if (head != null) {
			if (((Student) head.getData()).getSeatNumber() == data)
				return head;
			while (curr.getNext() != head) {
				curr = curr.getNext();
				if (((Student) curr.getData()).getSeatNumber() == data)
					return curr;
				if (curr == head)
					break;

			}
		}
		return null;
	}
	

//	public static void main(String[] args) {
//		CircularDoubleList<Integer> mm = new CircularDoubleList<>();
//
//		mm.insertEnd(55);
//		mm.insertEnd(60);
//		mm.insertEnd(44);
//		mm.insertEnd(88);
//		mm.insertEnd(83);
//
////		mm.insertHead(22);
//
//		System.out.println(mm.searched(55));
//
//		mm.deleteD(5);
//
//		mm.travers();
//		System.out.println("\n");
//		System.out.println(mm.print());
//	}

}
