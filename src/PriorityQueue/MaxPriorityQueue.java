package PriorityQueue;

/**
 * 最大优先队列
 *
 * @author : Code Dragon
 * create at:  2020/9/29  12:30
 */
public class MaxPriorityQueue<T extends Comparable<T>> {
    //存储堆中的元素
    private T[] items;
    //记录堆中元素的个数
    private int N;

    public MaxPriorityQueue(int capacity) {
        items = (T[]) new Comparable[capacity + 1];
        N = 0;
    }

    /**
     * 获取队列中元素的个数
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 12:32
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
     * @date: 2020/9/29 12:32
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
     * @date: 2020/9/29 12:33
     */
    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

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
     * @date: 2020/9/29 12:38
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
     * @date: 2020/9/29 12:40
     */
    private void swim(int k) {
        //如果已经到了根节点，就不需要循环了
        while (k > 1) {
            //比较当前节点和其父节点
            if (less(k / 2, k)) {
                //父节点小于当前结点，需要交换
                exch(k / 2, k);
            }
            k = k / 2;
        }
    }

    /**
     * 删除堆中最大的元素,并返回这个最大元素
     *
     * @param
     * @return T
     * @author: Code Dragon
     * @date: 2020/9/29 12:39
     */
    public T delMax() {
        T max = items[1];
        //交换索引1处和索引N处的值
        exch(1, N);
        //删除最后位置上的元素
        items[N] = null;
        //个数-1
        N--;
        sink(1);
        return max;
    }

    /**
     * 使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
     *
     * @param k
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 12:41
     */
    private void sink(int k) {
        //如果当前已经是最底层了，就不需要循环了
        while (2 * k <= N) {
            //找到子结点中的较大者
            int max = 2 * k;

            if (2 * k + 1 <= N) {//存在右子结点
                if (less(2 * k, 2 * k + 1))
                    max = 2 * k + 1;
            }

            //比较当前结点和子结点中的较大者，如果当前结点不小，则结束循环
            if (!less(k, max)) {
                break;
            }

            //当前结点小，则交换，
            exch(k, max);
            k = max;
        }
    }
}
