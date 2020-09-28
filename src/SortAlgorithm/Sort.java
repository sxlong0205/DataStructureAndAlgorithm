package SortAlgorithm;

public abstract class Sort<T extends Comparable<T>> {
    public abstract void sort(T[] nums);

    //判断v是否小于w
    protected boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    //交换两个元素
    protected void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;

    }
}
