package PriorityQueue;

/**
 * 最小优先队列
 *
 * @author : Code Dragon
 * create at:  2020/9/29  12:44
 */
public class MinPriorityQueue<T extends Comparable<T>> {
    //存储堆中的元素
    private T[] items;
    //记录堆中元素的个数
    private int N;

    public MinPriorityQueue(int capacity) {
        items = (T[]) new Comparable[capacity + 1];
        N = 0;
    }

    /**
     * 获取队列中元素的个数
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 12:45
     */
    public int size() {
        return N;
    }

    /**
     * 判断队列是否为空
     *
     * @param
     * @return boolean
     * @author: Code Dragon
     * @date: 2020/9/29 12:45
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 判断堆中索引i处的元素是否小于索引j处的元素
     *
     * @param i
     * @param j
     * @return boolean
     * @author: Code Dragon
     * @date: 2020/9/29 12:46
     */
    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    /**
     * 交换堆中i索引和j索引处的值
     *
     * @param i
     * @param j
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 12:46
     */
    private void exch(int i, int j) {
        T tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    /**
     * 往堆中插入一个元素
     *
     * @param t
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 12:48
     */
    public void insert(T t) {
        items[++N] = t;
        swim(N);
    }

    /**
     * 使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
     *
     * @param k
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 12:51
     */
    private void swim(int k) {
        //如果没有父节点，则不再上浮
        while (k > 1) {
            //如果当前节点比父节点小，则交换
            if (less(k, k / 2)) {
                exch(k, k / 2);
            }
            k = k / 2;
        }
    }

    /**
     * 删除堆中最小的元素,并返回这个最小元素
     *
     * @param
     * @return T
     * @author: Code Dragon
     * @date: 2020/9/29 12:48
     */
    public T delMin() {
        //索引1处的值是最小值
        T min = items[1];
        //交换索引1处和索引N处的值
        exch(1, N);
        //删除索引N处的值
        items[N] = null;
        //数据元素-1
        N--;
        //对索引1处的值做下沉，使堆重新有序
        sink(1);
        //返回被删除的值
        return min;
    }

    /**
     * 使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
     *
     * @param k
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 12:51
     */
    private void sink(int k) {
        //如果没有子节点，则不再下沉
        while (2 * k <= N) {
            //找出子节点中的较小值的索引
            int min = 2 * k;
            if (2 * k + 1 <= N && less(2 * k + 1, 2 * k))
                min = 2 * k + 1;
            //如果当前节点小于子节点中的较小值，则结束循环
            if (less(k, min))
                break;
            //当前节点大，交换
            exch(min, k);
            k = min;
        }
    }
}
