package LinearTable;

import java.util.Iterator;

public class BiLinkList<T> implements Iterable<T> {
    private Node head;
    private Node last;
    private int N;

    public BiLinkList() {
        last = null;
        head = new Node(null, null, null);
    }

    //清空当前链表
    public void clear() {
        last = null;
        head.next = last;
        head.pre = null;
        head.item = null;
        N = 0;
    }

    //获取链表长度
    public int length() {
        return N;
    }

    //判空函数
    public boolean isEmpty() {
        return N == 0;
    }

    //在尾部插入元素
    public void insert(T t) {
        if (last == null) {
            last = new Node(t, head, null);
            head.next = last;
        } else {
            Node oldLast = last;
            Node node = new Node(t, oldLast, null);
            oldLast.next = node;
            last = node;
        }
        N++;
    }

    //在指定位置插入元素
    public void insert(int i, T t) {
        if (i < 0 || i >= N) {
            throw new RuntimeException("位置不合法");
        }
        Node pre = head;
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }
        Node curr = pre.next;
        Node newNode = new Node(t, pre, curr);
        curr.pre = newNode;
        pre.next = newNode;
        N++;
    }

    //获取链表中指定位置元素
    public T get(int i) {
        if (i < 0 || i >= N) {
            throw new RuntimeException("位置不合法");
        }

        Node curr = head.next;
        for (int index = 0; index < i; index++) {
            curr = curr.next;
        }
        return (T) curr.item;
    }

    //获取链表指定元素
    public int indexOf(T t) {
        Node n = head;
        for (int i = 0; n.next != null; i++) {
            n = n.next;
            if (n.next.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    //删除指定位置元素
    public T remove(int i) {
        if (i < 0 || i >= N) {
            throw new RuntimeException("位置不合法");
        }
        Node pre = head;
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }
        Node curr = pre.next;
        Node curr_next = curr.next;

        pre.next = curr_next;
        curr_next.pre = pre;
        N--;
        return (T) curr.item;
    }

    //获取链表第一个元素
    public T getFirst() {
        if (isEmpty()) {
            return null;
        }
        return (T) head.next.item;
    }

    //获取链表最后一个元素
    public T getLast() {
        if (isEmpty()) {
            return null;
        }
        return (T) last.item;
    }

    //迭代器
    @Override
    public Iterator<T> iterator() {
        return new TIterator();
    }

    private class TIterator implements Iterator<T> {
        private Node n = head;

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

    public void reverse() {
        if (N == 0) {
            return;
        }
        reverse(head.next);
    }

    //反转链表
    public Node reverse(Node curr) {
        if (curr.next == null) {
            head.next = curr;
            return curr;
        }

        Node pre = reverse(curr.next);
        pre.next = curr;
        curr.next = null;
        return curr;
    }

    //利用快慢指针获取链表中间值
    public static String getMid(Node<String> first) {
        Node<String> slow = first;
        Node<String> fast = first;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.item;
    }

    //判断链表中是否有环
    public static boolean isCircle(Node<String> first) {
        Node<String> slow = first;
        Node<String> fast = first;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast.equals(slow)) {
                return true;
            }
        }
        return false;
    }

    //获取环入口,当快慢指针相遇时，让temp指针指向head，步长为slow步长
    //当temp和slow相遇时，就是环的入口
    public static Node getEntrance(Node<String> first) {
        Node<String> slow = first;
        Node<String> fast = first;
        Node<String> temp = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast.equals(slow)) {
                temp = first;
                continue;
            }
            if (temp != null) {
                temp = temp.next;
                if (temp.equals(slow)) {
                    return temp;
                }
            }
        }
        return null;
    }
}
