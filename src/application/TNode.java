package application;

public class TNode <D extends Comparable<D>>{
	D data;
	SingleList<D> linkedList;
	TNode<D> rigth; 
	TNode<D> left;
	
	public TNode(D data) {
		super();
		rigth=null;
		left=null;
		linkedList = new SingleList();
		this.data=data;	
	}
	

	public D getLinkedList() {
		return linkedList.getHead().getData();
	}


	public void setLinkedList(SingleList<D> linkedList) {
		this.linkedList = linkedList;
	}


	public D getData() {
		return data;
	}

	public void setData(D data) {
		this.data = data;
	}

	public TNode<D> getRigth() {
		return rigth;
	}

	public void setRigth(TNode<D> right) {
		this.rigth = right;
	}

	public TNode<D> getLeft() {
		return left;
	}

	public void setLeft(TNode<D> left) {
		this.left = left;
	}

	@Override
	public String toString() {
		return ""+data+"";
				
	}
	
//	public boolean isLeaf() {
//		return this.left == null && this.rigth == null;
//	}
	
//	public int height() {
//		int leftC = 0, RightC = 0;
//
//		if (isLeaf()) {
//			return 1;
//		}
//		if (left != null) {
//			leftC = left.height();
//		}
//		if (rigth != null) {
//			RightC = rigth.height();
//		}
//		return (leftC > RightC) ? leftC + 1 : RightC + 1;
//	}


	
	

}
