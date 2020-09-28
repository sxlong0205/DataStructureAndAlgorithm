package SortAlgorithm;

/**
 * @author : Code Dragon
 * create at:  2020/9/25  11:30
 */
public class Heap<T extends Comparable<T>> {
    //存放堆中的元素
    private T[] items;
    //堆中元素个数
    private int N;

    public Heap(int capacity) {
        items = (T[]) new Comparable[capacity + 1];
        N = 0;
    }

    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    private void exch(int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    //插入一个新元素
    public void insert(T t) {
        items[++N] = t;
        swim(N);
    }

    //下沉操作
    private void swim(int k) {
        //如果到根节点则退出循环
        while (k > 1 && less(k / 2, k)) {//如果父节点小于根节点，两者交换
            exch(k / 2, k);
            k /= 2;
        }
    }

    //删除堆中最大元素并返回
    public T delMax() {
        T max = items[1];
        //将最后一个元素放到堆顶
        exch(1, N--);
        //将堆最后一个位置置空
        items[N + 1] = null;
        //将堆顶元素下沉到合适的位置
        sink(1);
        return max;
    }

    //上浮操作
    private void sink(int k) {
        //到堆底元素时结束循环
        while (2 * k <= N) {
            //将左孩子赋值给max
            int max = 2 * k;
            if (max < N && less(max, max + 1)) {   //如果左孩子比右孩子小，则将右孩子赋值给max
                max++;
            }
            if (!less(k, max)) {
                break;
            }
            //如果父节点小于两个孩子中较大的结点，则交换
            exch(k, max);
            k = max;
        }
    }
}
