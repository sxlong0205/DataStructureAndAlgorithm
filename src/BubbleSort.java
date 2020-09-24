public class BubbleSort<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        boolean flags = false;
        for (int i = nums.length - 1; i > 0 && !flags; i--) {
            flags = true;
            for (int j = 0; j < i; j++) {
                if (less(nums[j + 1], nums[j])) {
                    flags = false;
                    swap(nums, j, j + 1);
                }
            }
        }
    }
}
