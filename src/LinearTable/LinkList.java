package LinearTable;

import java.util.Iterator;

public class LinkList<T> implements Iterable<T> {

    private Node head;
    private int N;

    //初始化操作
    public LinkList() {
        head = new Node(null, null);
        N = 0;
    }

    //清空链表
    public void clear() {
        head.next = null;
        head.item = null;
        N = 0;
    }

    //获取链表长度
    public int length() {
        return N;
    }

    //判空操作
    public boolean isEmpty() {
        return N == 0;
    }

    //获取指定位置元素
    public T get(int i) {
        if (i < 0 || i >= N) {
            throw new RuntimeException("位置不合法");
        }
        Node n = head.next;
        for (int index = 0; index < 1; index++) {
            n = n.next;
        }
        return (T) n.item;
    }

    //在尾部插入元素
    public void insert(T t) {
        Node n = head;
        while (n.next != null) {
            n = n.next;
        }
        Node newNode = new Node(t, null);
        n.next = newNode;
        N++;
    }

    //指定位置插入元素
    public void insert(int i, T t) {
        if (i < 0 || i >= N) {
            throw new RuntimeException("位置不合法");
        }

        Node pre = head;
        for (int index = 0; index <= i - 1; index++) {
            pre = pre.next;
        }

        //newNode-->curr, pre.next-->newNode
        Node curr = pre.next;
        Node newNode = new Node(t, curr);
        pre.next = newNode;
        N++;
    }

    //删除指定位置元素
    public T remove(int i) {
        if (i < 0 || i >= N) {
            throw new RuntimeException("位置不合法");
        }

        Node pre = head;
        for (int index = 0; index <= i - 1; index++) {
            pre = pre.next;
        }

        Node curr = pre.next;
        pre.next = curr.next;
        N--;
        return (T) curr.item;
    }

    //查找指定元素第一次出现位置
    public int indexOf(T t) {
        Node n = head;
        for (int i = 0; n.next != null; i++) {
            n = n.next;
            if (n.item.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Literator();
    }

    private class Literator implements Iterator<T> {
        private Node n;

        public Literator() {
            this.n = head;
        }

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public T next() {
            n = n.next;
            return (T) n.item;
        }
    }
}
