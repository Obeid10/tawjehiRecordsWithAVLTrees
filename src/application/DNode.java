package application;

public class DNode<D extends Comparable<D>> {
	private D data;
	public DNode<D> prev;
	public DNode<D> next;
	public TNode<Integer> curr ;
	public TNode<Double> curr1 ;
	

	public DNode(D data) {
		super();
		this.data = data;
	}

	public D getData() {
		return data;
	}

	public void setData(D data) {
		this.data = data;
	}

	public DNode<D> getPrev() {
		return prev;
	}

	public void setPrev(DNode<D> prev) {
		this.prev = prev;
	}

	public DNode<D> getNext() {
		return next;
	}

	public void setNext(DNode<D> next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", prev=" + prev + ", next=" + next + "]";
	}

}
