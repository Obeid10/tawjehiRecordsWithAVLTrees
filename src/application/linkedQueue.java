package application;

public class linkedQueue<D extends Comparable<D>> {
    private Node<D> first;
    private Node<D> last;

    public boolean isEmpty() {
        return (first == null) && (last == null);
    }

    public void clear() {
        first = null;
        last = null;
    }

    public void enqueue(Node<D> node) {
        if (isEmpty())
            first = node;
        else
            last.setNext(node);

        last = node;
    }

    public Node<D> dequeue() {
        Node<D> frontNode = getFront();
        if (!isEmpty())
            first = first.getNext();
        if (first == null)
            last = null;
        return frontNode;
    }
    
    public Node<D> getFront(){
    	if(!isEmpty())
    		 return first;
    		 return null; 

    }
}

