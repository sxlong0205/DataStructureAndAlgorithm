package UnionFind;

/**
 * 并查集
 *
 * @author : Code Dragon
 * create at:  2020/9/29  16:18
 */
public class UF {
    //记录节点元素和该元素所在分组的标识
    private int[] eleAndGroup;
    //记录并查集中数据的分组个数
    private int count;

    //初始化并查集
    public UF(int N) {
        //初始情况下，每个元素都在一个独立的分组中，
        //初始情况下，并查集中的数据默认分为N个组
        this.count = N;
        //初始化数组
        eleAndGroup = new int[N];

        //把eleAndGroup数组的索引看做是每个结点存储的元素，
        //把eleAndGroup数组每个索引处的值看做是该节点所在的分组，
        //那么初始化情况下，i索引处存储的值就是i
        for (int i = 0; i < N; i++) {
            eleAndGroup[i] = i;
        }
    }

    /**
     * 获取当前并查集中的分组数
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 16:20
     */
    public int count() {
        return count;
    }

    /**
     * 元素p所在分组的标识符
     *
     * @param p
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 16:21
     */
    public int find(int p) {
        return eleAndGroup[p];
    }

    /**
     * 判断两元素是否在同一分组当中
     *
     * @param p
     * @param q
     * @return boolean
     * @author: Code Dragon
     * @date: 2020/9/29 16:22
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 把p元素所在分组和q元素所在分组合并
     *
     * @param p
     * @param q
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 16:22
     */
    public void union(int p, int q) {
        //如果p和q已经在同一个分组中，则无需合并
        if (connected(p, q))
            return;

        //如果p和q不在同一个分组，
        // 则只需要将p元素所在组的所有的元素的组标识符修改为q元素所在组的标识符即可
        int pGroup = find(p);
        int qGroup = find(q);
        for (int i = 0; i < eleAndGroup.length; i++) {
            if (eleAndGroup[i] == pGroup)
                eleAndGroup[i] = qGroup;
        }
        //分组数量-1
        count--;
    }
}
