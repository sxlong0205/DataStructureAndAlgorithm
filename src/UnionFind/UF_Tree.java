package UnionFind;

/**
 * @author : Code Dragon
 * create at:  2020/9/29  16:32
 */
public class UF_Tree {
    //记录节点元素和该元素的父节点
    private int[] eleAndGroup;
    //记录并查集中数据的分组个数
    private int count;

    //初始化并查集
    public UF_Tree(int N) {
        //初始情况下，每个元素都在一个独立的分组中，
        // 所以，初始情况下，并查集中的数据默认分为N个组
        this.count = N;
        //初始化数组
        eleAndGroup = new int[N];

        //把eleAndGroup数组的索引看做是每个结点存储的元素，
        // 把eleAndGroup数组每个索引处的值看做是该结点的父结点，
        // 那么初始化情况下，i索引处存储的值就是i
        for (int i = 0; i < N; i++) {
            eleAndGroup[i] = i;
        }
    }

    /**
     * 获取当前并查集中的数据有多少个分组
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 16:35
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
     * @date: 2020/9/29 16:36
     */
    public int find(int p) {
        while (true) {
            //判断当前元素p的父节点eleAndGroup[p]是不是自己，
            // 如果是自己则证明已经是根节点了
            if (p == eleAndGroup[p]) {
                return p;
            }
            //如果当前元素p的父节点不是自己，则让p=eleAndGroup[p]，
            // 继续找父节点的父节点,直到找到根节点为止
            p = eleAndGroup[p];
        }
    }

    /**
     * 把p元素所在分组和q元素所在分组合并
     *
     * @param p
     * @param q
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 16:38
     */
    public void union(int p, int q) {
        //找到p元素所在树的根节点
        int pRoot = find(p);
        //找到q元素所在树的根节点
        int qRoot = find(q);

        //如果p和q已经在同一个树中，则无需合并
        if (pRoot == qRoot) {
            return;
        }
        //如果p和q不在同一个分组，则只需要将p元素所在树根节点的父节点设置为q元素的根节点即可
        eleAndGroup[pRoot] = qRoot;

        //分组数量-1
        count--;
    }
}
