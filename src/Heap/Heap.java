package Heap;

/**
 * 堆
 *
 * @author : Code Dragon
 * create at:  2020/9/29  11:21
 */
public class Heap<T extends Comparable<T>> {
    //存储堆中的元素
    private T[] items;
    //记录堆中元素个数
    private int N;

    public Heap(int capacity) {
        items = (T[]) new Comparable[capacity + 1];
        N = 0;
    }

    /**
     * 判断堆中索引i处的元素是否小于索引j处的元素
     *
     * @param i
     * @param j
     * @return boolean
     * @author: Code Dragon
     * @date: 2020/9/29 11:54
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
     * @date: 2020/9/29 11:54
     */
    private void exch(int i, int j) {
        T tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    /**
     * 插入一个元素
     *
     * @param t
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 11:56
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
     * @date: 2020/9/29 11:57
     */
    private void swim(int k) {
        //访问到根节点退出循环
        while (k > 1) {
            //比较当前节点和父节点的大小
            if (less(k / 2, k)) {
                //父节点小于当前节点，则需要交换
                exch(k / 2, k);
            }
            k = k / 2;
        }
    }

    /**
     * 删除堆中最大的元素，并返回这个最大元素
     *
     * @param
     * @return T
     * @author: Code Dragon
     * @date: 2020/9/29 11:56
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
     * @date: 2020/9/29 12:02
     */
    private void sink(int k) {
        while (2 * k <= N) {
            int max;
            //如果存在右子节点判断两节点大小，并将较大值赋值给max
            if (2 * k + 1 <= N) {
                if (less(2 * k, 2 * k + 1))
                    max = 2 * k + 1;
                else
                    max = 2 * k;
            } else { //如果不存在右子节点，直接将左子结点赋值给最大值
                max = 2 * k;
            }
            //如果父节点大于两个孩子节点，则直接跳出循环
            if (!less(k, max)) {
                break;
            }
            //当前节点小，则交换
            exch(k, max);
            k = max;
        }
    }

}
