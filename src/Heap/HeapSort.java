package Heap;

/**
 * 堆排序
 *
 * @author : Code Dragon
 * create at:  2020/9/25  11:25
 */
public class HeapSort {
    /**
     * 对source数组中的数据从小到大排序
     *
     * @param source
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 12:12
     */
    public static void sort(Comparable[] source) {
        //1.创建一个比原数组大1的数
        Comparable[] heap = new Comparable[source.length + 1];
        //2.构造堆
        createHeap(source, heap);

        //3.堆排序
        //3.1定义一个变量，记录heap中未排序的所有元素中最大的索引
        int N = heap.length - 1;
        while (N != 1) {
            //3.2交换heap中索引1处的元素和N处的元素
            exch(heap, 1, N);
            N--;
            //3.3对索引1处的元素在0~N范围内做下沉操作
            sink(heap, 1, N);
        }

        //4.heap中的数据已经有序，拷贝到source中
        System.arraycopy(heap, 1, source, 0, source.length);
    }

    /**
     * 根据原数组source，构造出堆heap
     *
     * @param source
     * @param heap
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 12:14
     */
    private static void createHeap(Comparable[] source, Comparable[] heap) {
        //1.把source中的数据拷贝到heap中，从heap的1索引处开始填充
        System.arraycopy(source, 0, heap, 1, source.length);
        //2.从heap索引的一半处开始倒叙遍历，对得到的每一个元素做下沉操作
        for (int i = (heap.length - 1) / 2; i > 0; i--) {
            sink(heap, i, heap.length - 1);
        }
    }

    /**
     * 交换heap堆中i索引和j索引处的值
     *
     * @param heap
     * @param i
     * @param j
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 12:15
     */
    private static void exch(Comparable[] heap, int i, int j) {
        Comparable tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    /**
     * 在heap堆中，对target处的元素做下沉，范围是0~range
     *
     * @param heap
     * @param target
     * @param range
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 12:16
     */
    private static void sink(Comparable[] heap, int target, int range) {
        //定义max用于存放子节点中较大值
        int max = 0;
        //如果不存在左右节点则跳出循环
        while (2 * target <= range) {
            //1.如果存在右节点，比较左右孩子节点的大小，将较大值赋值给max
            if (2 * target + 1 <= range) {
                //如果左孩子小于右孩子，则将右孩子赋值给max
                if (less(heap, 2 * target, 2 * target + 1))
                    max = 2 * target + 1;
                else
                    max = 2 * target;
            } else {
                max = 2 * target;
            }

            //2.如果当前结点的值小于子结点中的较大值，则交换
            if (less(heap, target, max)) {
                exch(heap, target, max);
            }
            //3.更新target的值
            target = max;
        }
    }

    /**
     * 判断heap堆中索引i处的元素是否小于索引j处的元素
     *
     * @param heap
     * @param i
     * @param j
     * @return boolean
     * @author: Code Dragon
     * @date: 2020/9/29 12:15
     */
    private static boolean less(Comparable[] heap, int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }
}
