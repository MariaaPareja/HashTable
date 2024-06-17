package hash;
//Actividad 4
//Parte a
public class ListLinked<T> implements TDAList<T> {
    protected Node<T> first;

    public ListLinked() {
        this.first = null;
    }

    @Override
    public boolean isEmptyList() {
        return first == null;
    }

    @Override
    public int length() {
        int count = 0;
        Node<T> current = first;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    @Override
    public void destroyList() {
        first = null;
    }

    @Override
    public int search(T x) {
        int index = 0;
        Node<T> current = first;
        while (current != null) {
            if (current.getData().equals(x)) {
                return index;
            }
            index++;
            current = current.getNext();
        }
        return -1;
    }

    @Override
    public void insertFirst(T x) {
        Node<T> newNode = new Node<>(x);
        newNode.setNext(first);
        first = newNode;
    }

    @Override
    public void insertLast(T x) {
        if (isEmptyList()) {
            insertFirst(x);
            return;
        }
        Node<T> newNode = new Node<>(x);
        Node<T> current = first;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(newNode);
    }

    @Override
    public void removeNode(T x) {
        if (isEmptyList()) {
            return;
        }
        if (first.getData().equals(x)) {
            first = first.getNext();
            return;
        }
        Node<T> current = first;
        while (current.getNext() != null && !current.getNext().getData().equals(x)) {
            current = current.getNext();
        }
        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = first;
        while (current != null) {
            sb.append(current.getData()).append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

	public Node<T> getFirst() {
		return first;
	}

	public void setFirst(Node<T> first) {
		this.first = first;
	}
}
