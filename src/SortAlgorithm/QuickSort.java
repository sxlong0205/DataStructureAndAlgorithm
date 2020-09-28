package SortAlgorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuickSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        //快排在乱序情况下效果非常好
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
    }

    private void shuffle(T[] nums) {
        List<Comparable> list = Arrays.asList(nums);
        Collections.shuffle(list);
        list.toArray(nums);
    }

    private void sort(T[] nums, int low, int high) {
        if (high <= low)
            return;
        int partition = partition(nums, low, high);
        sort(nums, low, partition - 1);
        sort(nums, partition + 1, high);
    }

    private int partition(T[] nums, int low, int high) {
        int i = low, j = high + 1;
        T v = nums[low];
        while (true){
            while (less(nums[++i], v) && i != high);
            while (less(v, nums[--j]) && j != low);
            if (i >= j)
                break;
            swap(nums, i, j);
        }
        swap(nums, low, j);
        return j;
    }

}
