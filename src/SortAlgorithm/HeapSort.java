package SortAlgorithm;

/**
 * @author : Code Dragon
 * create at:  2020/9/25  11:25
 */
public class HeapSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        int N = nums.length - 1;
        //从第一个非孩子结点开始遍历，调整排序
        for (int k = N / 2; k >= 1; k--) {
            sink(nums, k, N);
        }

        while (N > 1) {
            swap(nums, 1, N--);
            sink(nums, 1, N);
        }
    }

    private void sink(T[] nums, int k, int N) {
        while (2 * k <= N) {
            int max = 2 * k;
            if (max < N && less(nums, max, max + 1)) {
                max++;
            }
            if (!less(nums, k, max)) {
                break;
            }
            swap(nums, k, max);
            k = max;
        }
    }

    private boolean less(T[] nums, int k, int max) {
        return nums[k].compareTo(nums[max]) < 0;
    }
}
