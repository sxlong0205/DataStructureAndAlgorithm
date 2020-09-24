public class SelectionSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int min = i;
            //循环遍历，每次从未排序数组中找出一个最小值，然后插入已排序数组
            for (int j = i + 1; j < nums.length; j++) {
                if (less(nums[j], nums[min])) {
                    min = j;
                }
            }
            swap(nums, i, min);
        }
    }
}
