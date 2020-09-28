package SortAlgorithm;

import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> extends Sort<T> {
    //归并排序需要一个辅助数组
    private T[] assist;

    @Override
    public void sort(T[] nums) {
        assist = (T[]) new Comparable[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    private void sort(T[] nums, int low, int high) {
        if (high <= low) {
            return;
        }
        int middle = low + (high - low) / 2;
        //对low到middle之间的元素进行排序；
        sort(nums, low, middle);
        //对middle+1到high之间的元素进行排序；
        sort(nums, middle + 1, high);
        //对low到middle这组数据和middle到high这组数据进行归并
        merge(nums, low, middle, high);
    }

    private void merge(Comparable[] nums, int low, int middle, int high) {
        //定义一个指针，指向assist数组中开始填充数据的索引
        int i = low;
        //定义一个指针，指向第一组数据的第一个元素
        int p1 = low;
        //定义一个指针，指向第二组数据的第一个元素
        int p2 = middle + 1;

        //比较左边小组和右边小组中的元素大小，
        //哪个小，就把哪个数据填充到assist数组中
        while (p1 <= middle && p2 <= high) {
            if (less(nums[p1], nums[p2])) {
                assist[i++] = (T) nums[p1++];
            } else {
                assist[i++] = (T) nums[p2++];
            }
        }

        while (p1 <= middle) {
            assist[i++] = (T) nums[p1++];
        }
        while (p2 <= high) {
            assist[i++] = (T) nums[p2++];
        }

        for (int index = low; index <= high; index++) {
            nums[index] = assist[index];
        }
    }

    public static void main(String[] args) {
        MergeSort<Integer> sort = new MergeSort<>();
        Integer[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        sort.sort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
