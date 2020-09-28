package LinearTable;

public class Node<T> {
    public T item;
    public Node pre;
    public Node next;

    public Node(T item, Node next) {
        this.item = item;
        this.next = next;
    }

    public Node(T item, Node pre, Node next) {
        this.item = item;
        this.pre = pre;
        this.next = next;
    }
}
