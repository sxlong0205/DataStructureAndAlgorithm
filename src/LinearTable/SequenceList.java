package LinearTable;

import java.util.Iterator;
import java.util.Objects;

public class SequenceList<T> implements Iterable<T> {
    private T[] elements;
    private int N;

    public SequenceList(int capacity) {
        elements = (T[]) new Objects[capacity];
        N = 0;
    }

    public void clear() {
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int length() {
        return N;
    }

    /**
     * 获取指定元素
     *
     * @param i
     * @return T
     * @author: Code Dragon
     * @date: 2020/9/29 10:57
     */
    public T get(int i) {
        if (i < 0 || i >= N) {
            throw new RuntimeException("当前元素不存在");
        }
        return elements[i];
    }

    /**
     * 插入元素
     *
     * @param t
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 10:57
     */
    public void insert(T t) {
        if (N == elements.length) {
            throw new RuntimeException("容量已满！");
        }
        elements[N++] = t;
    }

    /**
     * 在指定位置插入元素
     *
     * @param i
     * @param t
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 10:57
     */
    public void insert(int i, T t) {
        if (i == elements.length) {
            throw new RuntimeException("容量已满");
        }

        if (i < 0 || i > N) {
            throw new RuntimeException("插入位置不合法");
        }

        for (int index = N; index > i; index--) {
            elements[index] = elements[index - 1];
        }

        elements[i] = t;
        N++;
    }

    /**
     * 删除指定位置元素
     *
     * @param i
     * @return T
     * @author: Code Dragon
     * @date: 2020/9/29 10:57
     */
    public T remove(int i) {
        if (i < 0 || i > N - 1) {
            throw new RuntimeException("删除元素不存在");
        }
        //记录i位置元素
        T result = elements[i];
        //后移i位置后的元素
        for (int index = i; index < N - 1; index++) {
            elements[index] = elements[index + 1];
        }

        N--;
        return result;
    }

    /**
     * 查找元素第一次出现位置
     *
     * @param t
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 10:57
     */
    public int indexOf(T t) {
        if (t == null) {
            throw new RuntimeException("查找元素不存在");
        }

        for (int i = 0; i < N; i++) {
            if (elements[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 打印输出元素
     *
     * @param
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 10:57
     */
    public void showElements() {
        for (int i = 0; i < N; i++) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }


    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator<T> {
        private int cur;

        public SIterator() {
            this.cur = 0;
        }

        @Override
        public boolean hasNext() {
            return cur < N;
        }

        @Override
        public T next() {
            return elements[cur++];
        }
    }

    /**
     * 扩容
     *
     * @param newSize
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 10:57
     */
    private void resize(int newSize) {
        T[] temp = elements;
        elements = (T[]) new Objects[newSize];
        for (int i = 0; i < N; i++) {
            elements[i] = temp[i];
        }
    }

    /**
     * 获取当前队列大小
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 10:58
     */
    public int capacity() {
        return elements.length;
    }
}
