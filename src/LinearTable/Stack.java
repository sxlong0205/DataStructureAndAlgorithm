package LinearTable;

import java.util.Iterator;

public class Stack<T> implements Iterable<T> {

    private Node head;
    private int N;

    public Stack() {
        head = new Node(null, null);
        N = 0;
    }

    /**
     * 判空操作
     *
     * @param
     * @return boolean
     * @author: Code Dragon
     * @date: 2020/9/29 10:58
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 压栈操作，这里用头插法
     *
     * @param t
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 10:58
     */
    public void push(T t) {
        Node oldNext = head.next;
        Node node = new Node(t, oldNext);
        head.next = node;
        N++;
    }

    /**
     * 弹栈操作
     *
     * @param
     * @return T
     * @author: Code Dragon
     * @date: 2020/9/29 10:59
     */
    public T pop() {
        Node oldNext = head.next;
        if (oldNext == null) {
            return null;
        }
        head.next = head.next.next;
        N--;
        return (T) oldNext.item;
    }

    /**
     * 获取Stack大小
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 10:59
     */
    public int size() {
        return N;
    }

    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator<T> {
        private Node n = head;

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public T next() {
            Node node = n.next;
            n = n.next;
            return (T) node.item;
        }
    }
}
