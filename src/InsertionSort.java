public class InsertionSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(nums[j], nums[j - 1])) {
                    swap(nums, j, j - 1);
                }
            }
        }
    }
}
