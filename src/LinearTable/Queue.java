package LinearTable;

import java.util.Iterator;

public class Queue<T> implements Iterable<T> {
    private Node head;
    private Node last;
    private int N;

    public Queue() {
        head = new Node(null, null);
        last = null;
        N = 0;
    }

    /**
     * 判空操作
     *
     * @param
     * @return boolean
     * @author: Code Dragon
     * @date: 2020/9/29 10:56
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 获取队列大小
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 10:56
     */
    public int size() {
        return N;
    }

    /**
     * 入队操作
     *
     * @param t
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 10:56
     */
    public void enqueue(T t) {
        if (last == null) {
            last = new Node(t, null);
            head.next = last;
        } else {
            Node oldLast = last;
            last = new Node(t, null);
            oldLast.next = last;
        }
        N++;
    }

    /**
     * 出队操作
     *
     * @param
     * @return T
     * @author: Code Dragon
     * @date: 2020/9/29 10:56
     */
    public T dequeue() {
        if (isEmpty()) {
            return null;
        }

        Node oldFirst = head.next;
        head.next = oldFirst.next;
        N--;
        if (isEmpty()) {
            last = null;
        }
        return (T) oldFirst.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new QIterator();
    }

    private class QIterator implements Iterator<T> {
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
