public class ShellSort<T extends Comparable<T>> extends Sort<T> {
    @Override
    public void sort(T[] nums) {
        int h = 1;

        while (h < nums.length / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < nums.length; i++) {
                for (int j = i; j >= h && less(nums[j], nums[j - 1]); j -= h) {
                    swap(nums, j, j - h);
                }
            }
            h = h / 3;
        }
    }
}
