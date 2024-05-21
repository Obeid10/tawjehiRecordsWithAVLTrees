package application;


public class AVL<D extends Comparable<D>> {
	TNode<D> root;
	String str ; 

	public AVL() {
		super();
	}

//	public void addEntry(D data, TNode<D> roNode) {
//		assert roNode != null;
//		if (data.compareTo((D) roNode.data) < 0) { 
//			if (!hasLeft(roNode)) {
//				TNode<D> leftChild = roNode.left;
//				addEntry(data, leftChild);
//				roNode.left = rebalance(leftChild);
//			} else
//				roNode.left = new TNode<D>(data);
//		} else {
//			if (!hasRigth(roNode)) {
//				TNode<D> rightChild = roNode.rigth;
//				addEntry(data, rightChild);
//				roNode.rigth = rebalance(rightChild);
//			} else
//				roNode.rigth = new TNode<D>(data);
//		}
//	}
	
	public void addEntry(D data, TNode<D> rootNode) {
	    if (rootNode == null) {
	        rootNode = new TNode<D>(data);
	        return;
	    }

	    if (data.compareTo(rootNode.data) < 0) {
	        if (rootNode.left != null) {
	            addEntry(data, rootNode.left);
	            rootNode.left = rebalance(rootNode.left);
	        } else {
	            rootNode.left = new TNode<D>(data);
	        }
	    } else {
	        if (rootNode.rigth != null) {
	            addEntry(data, rootNode.rigth);
	            rootNode.rigth = rebalance(rootNode.rigth);
	        } else {
	            rootNode.rigth = new TNode<D>(data);
	        }
	    }
	}
	
	public void addGread(D data, TNode<D> rootNode) {
	    if (rootNode == null) {
	        rootNode = new TNode<D>(data);
	        return;
	    }

	    if (data.compareTo(rootNode.getLinkedList()) < 0) {
	        if (rootNode.left != null) {
	        	TNode<D> leftChild = rootNode.left;
	            addGread(data, leftChild);
	            rootNode.left = rebalance(leftChild);
	        } else {
	            rootNode.left = new TNode<D>(data);
	        }
	    } else if (data.compareTo(rootNode.getLinkedList()) > 0){
	        if (rootNode.rigth != null) {
	        	TNode<D> rightChild = rootNode.rigth;
	            addGread(data, rightChild);
	            rootNode.rigth = rebalance(rightChild);
	        } else {
	            rootNode.linkedList.insertFirst((Student)data);
	        }
	    }
	}
	
	

	private TNode<D> rebalance(TNode nodeN) {
		int diff = getHeightDifference(nodeN);
		if (diff > 1) { // addition was in node's left subtree
			if (getHeightDifference(nodeN.left) > 0)
				nodeN = rotateRight(nodeN);
			else
				nodeN = rotateLeftRight(nodeN);
		} else if (diff < -1) { // addition was in node's right subtree
			if (getHeightDifference(nodeN.rigth) < 0)
				nodeN = rotateLeft(nodeN);
			else
				nodeN = rotateRightLeft(nodeN);
		}
		return nodeN;
	}

	public int getHeightDifference(TNode<D> nodeN) {

		return height(nodeN.left) - height(nodeN.rigth);
	}

	public int height() {
		return height(root);
	}

	private int height(TNode<D> node) {

		if (node == null)
			return 0;

		return 1 + Math.max(height(node.left), height(node.rigth));
	}
	
	public void insert(D data) {
		 if(isEmpty()) 
			 root = new TNode<>(data);
		 else {
		 TNode<D> rootNode = root;
		 addEntry(data, rootNode);
		 root = rebalance(rootNode);
		 }
		} 
	
	public void insertGread(D data) {
		 if(isEmpty()) 
			 root = new TNode<>(data);
		 else {
		 TNode<D> rootNode = root;
		 addGread(data, rootNode);
		 root = rebalance(rootNode);
		 }
		} 

	public TNode<D> rotateRight(TNode<D> node) {
		TNode<D> c = node.left;
		node.left = c.rigth;
		c.rigth = node;
		return c;
	}

	public TNode<D> rotateLeft(TNode<D> node) {
		TNode<D> c = node.rigth;
		node.rigth = c.left;
		c.left = node;
		return c;
	}

	public TNode<D> rotateRightLeft(TNode<D> node) {
		TNode<D> c = node.rigth;
		node.rigth = rotateRight(c);
		return rotateLeft(node);
	}

	public TNode rotateLeftRight(TNode node) {
		TNode c = node.left;
		node.left = rotateLeft(c);
		return rotateRight(node);
	}

	public boolean hasRigth(TNode<D> node) {
		return node.rigth == null;
	}

	public boolean hasLeft(TNode<D> node) {
		return node.left == null;
	}

	public boolean isLeaf(TNode<D> node) {
		return (node.left == null && node.rigth == null);
	}

	public boolean isEmpty() {
		return root == null;
	}

	public int parentCount() {
		return parentCount(root);
	}

	private int parentCount(TNode<D> node) {
		if (node == null || (node.left == null && node.rigth == null))
			return 0;
		return 1 + parentCount(node.left) + parentCount(node.rigth);
	}

	public int leavesCount() {
		return leavesCount(root);
	}

	private int leavesCount(TNode<D> node) {
		if (node == null)
			return 0;
		if (node.left == null && node.rigth == null)
			return 1;
		return leavesCount(node.left) + leavesCount(node.rigth);
	}

	public int nodesCont() {
		return nodesCont(root);
	}

	private int nodesCont(TNode<D> node) {
		if (node == null)
			return 0;
		return 1 + nodesCont(node.left) + nodesCont(node.rigth);
	}

	public void traversalInOrder() {
		traversalInOrder(root);
	}
	
	public TNode Balance(TNode node) {
		int heightDiff = getHeightDifference(node);
		if (heightDiff > 1) {
			if (getHeightDifference(node.left) > 0) {
				node = rotateRight(node);
			} else {
				node = rotateLeftRight(node);
			}
		} else if (heightDiff < -1) {
			if (getHeightDifference(node.rigth) < 0) {
				node = rotateLeft(node);
			} else {
				node = rotateRightLeft(node);
			}
		}
		return node;
	}

	
	public void printLevelOrder()
    {
        int h = height(root);
        int i;
        for (i = 1; i <= h; i++)
        	
            printCurrentLevel(root, i);
    }
	
	private void printCurrentLevel(TNode<D> root, int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.data + "--> ");
        else if (level > 1) {
            printCurrentLevel(root.left, level - 1);
            printCurrentLevel(root.rigth, level - 1);
        }
    }

	public String printOrder() {
		String result = "";
		int h = height(root);
		int i;
		for (i = 1; i <= h; i++)
			result += printLevel(root, i);
		return result;
	}
	
	private String printLevel(TNode<D> root, int level)
	{
	    String result = "";
	    if (root == null)
	        return "";
	    if (level == 1)
	        result += root.data + "   ";
	    else if (level > 1) {
	        result += printLevel(root.left, level - 1);
	        result += printLevel(root.rigth, level - 1);
//	        result+="\n";
	    }
	    return result;
	}


	
	private TNode<D> getSuccessor(TNode node) {
		TNode parentOfSuccessor = node;
		TNode successor = node;
		TNode current = node.rigth;
		while (current != null) {
			parentOfSuccessor = successor;
			successor = current;
			current = current.left;
		}
		if (successor != node.rigth) {
			parentOfSuccessor.left = (successor.rigth);
			successor.rigth = node.rigth;
		}
		return successor;
	}
	
	public void delete(D data) {
		TNode current = root;
		TNode parent = root;
		boolean isLeftChild = false;
		if (current == null)
			return;

		while (current != null
				&& current.data.equals(data) == false) {
			parent = current;
			if (data.compareTo((D) current.data) < 0) {
				current = current.left;
				isLeftChild = true;
			} else {
				current = current.rigth;
				isLeftChild = false;
			}
		}
		if (current == null)
			return;

		if (current.left == null && current.rigth == null) {
			if (current == root) {
				root = null;
			} else {
				if (isLeftChild)
					parent.left = null;
				else
					parent.rigth = null;
			}
		} else if (current.rigth == null) {
			if (current == root) {
				root = current.left;
			} else if (isLeftChild) {
				parent.left = current.left;
			} else {
				parent.rigth = (current.left);
			}
		} else if (current.left == null) {// current has right child
			if (current == root) {
				root = current.rigth;
			} else if (isLeftChild) {
				parent.left = (current.rigth);
			} else {
				parent.rigth = (current.rigth);
			}
		} else {

			TNode successor = getSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftChild) {
				parent.left = successor;
			} else {
				parent.rigth = successor;
			}
			successor.left = current.left;

		}
		this.root = bLeft(root);
	}
	
	public TNode bLeft(TNode node) {
		if (node != null) {
			node.left = (bLeft(node.left));

			node = BRight(node);
			node = Balance(node);
			node.rigth = (bLeft(node.rigth));
		}
		return node;
	}

	public TNode BRight(TNode Node) {
		if (Node != null) {
			Node.rigth = (BRight(Node.rigth));
			TNode r = Node;
			Node = Balance(Node);
			if (r == root) {
				return Node;
			}
			Node.left = (BRight(Node.left));
		}
		return Node;
	}

	private void traversalInOrder(TNode<D> node) {
		if (node != null) {
			traversalInOrder(node.left);
			System.out.print(node + " ");
			traversalInOrder(node.rigth);
		}
	}
	
	public TNode FindNode(double x)
    {
        return FindNode( x, root );
    }

    private TNode FindNode(double x , TNode node)
    {
        while (node != null) {
            if (x < (Double)node.data)
                node = node.left;
            else if (x > (Double)node.data)
                node = node.rigth;
            else
            {
                return node;
            }
        }
        return null;
    }
    
    public TNode FindNode1(int x)
    {
        return FindNode1( x, root );
    }

    private TNode FindNode1(int x , TNode node)
    {
        int counter = 0 ;
        while (node != null) {
            if (x < (Integer)node.data)
                node = node.left;
            else if (x > (Integer)node.data)
                node = node.rigth;
            else
            {
                return node;
            }
        }
        return null;
    }

	public String traversIdAVL() {
        str = "";
        if (isEmpty())
            return "Emptyyyy";

        return traversIdAVL(root);
    }

    private String traversIdAVL(TNode temp) 
    {
        if (temp != null) {
        	traversIdAVL(temp.left);
            str += temp.data + " (" + (height(temp)) + ")" + "\n";
            traversIdAVL(temp.rigth);
        }
        return str ;
    }

    public String traversGradeAVL() {
        str = "";
        if (isEmpty())
            return "Emptyyyy";

        return traversGradeAVL(root);
    }

    private String traversGradeAVL(TNode temp) 
    {
        if (temp != null) {
        	traversGradeAVL(temp.left);
            str += temp.data + " " + temp.linkedList.toString() + " (" + (height(temp)-1) + ")" + "\n";
            traversGradeAVL(temp.rigth);
        }
        return str ;
    }

//	public static void main (String[] args) {
//		
//		AVL<Integer> A = new AVL<>();
//		A.insert(55);
//		A.insert(60);
//		A.insert(70);
//		A.insert(40);
//		A.insert(30);
//		A.insert(65);
//		A.insert(55);
//		
//		A.insert(20);
//		A.insert(10);
//		A.insert(5);
//		
////		A.delete(60);
////		A.delete(5);
////		A.delete(40);
////		A.delete(30);
//		
//		
//		
//		System.out.println(A.root);
//		System.out.println(A.getHeightDifference(A.root));
//		System.out.println(A.height());
//		A.traversalInOrder();
//		System.out.println("\n");
////		A.printLevelOrder();
//		
//		System.out.println(A.printOrder());
//	}
	


}
