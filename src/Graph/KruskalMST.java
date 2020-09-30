package Graph;

import LinearTable.Queue;
import PriorityQueue.MinPriorityQueue;
import UnionFind.UF_Tree_Weighted;

/**
 * 克鲁斯卡尔算法（加边法）
 *
 * @author : Code Dragon
 * create at:  2020/9/30  10:32
 */
public class KruskalMST {
    //保存最小生成树的所有边
    private Queue<Edge> mst;
    //索引代表顶点，使用uf.connect(v,w)可以判断顶点v和顶点w是否在同一颗树中，
    // 使用uf.union(v,w)可以把顶点v所在的树和顶点w所在的树合并
    private UF_Tree_Weighted uf;
    //存储图中所有的边，使用最小优先队列，对边按照权重进行排序
    private MinPriorityQueue<Edge> pq;

    /**
     * 根据一副加权无向图，创建最小生成树计算对象
     *
     * @param G
     * @return
     * @author: Code Dragon
     * @date: 2020/9/30 10:36
     */
    public KruskalMST(EdgeWeightedGraph G) {
        //初始化mst队列
        this.mst = new Queue<>();
        //初始化并查集对象uf,容量和图的顶点数相同
        this.uf = new UF_Tree_Weighted(G.V());
        //初始化最小优先队列pq，容量比图的边的数量大1，并把图中所有的边放入pq中
        this.pq = new MinPriorityQueue<>(G.E() + 1);
        for (Edge edge : G.edges()) {
            pq.insert(edge);
        }

        //如果优先队列pq不为空，也就是还有边未处理，并且mst中的边还不到V-1条，继续遍历
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            //取出pq中权重最小的边e
            Edge e = pq.delMin();
            //获取边e的两个顶点v和w
            int v = e.either();
            int w = e.other(v);

            //通过uf.connect(v,w)判断v和w是否已经连通，
            //如果连通:
            //则证明这两个顶点在同一棵树中，那么就不能再把这条边添加到最小生成树中，因为在一棵
            //树的任意两个顶点上添加一条边，都会形成环，而最小生成树不能有环的存在;
            //如果不连通:
            // 则通过uf.connect(v,w)把顶点v所在的树和顶点w所在的树合并成一棵树,并把这条边加入到mst队列中
            if (uf.connected(v, w))
                continue;

            uf.union(v, w);
            mst.enqueue(e);

        }
    }

    /**
     * 获取最小生成树的所有边
     *
     * @param
     * @return LinearTable.Queue<Graph.Edge>
     * @author: Code Dragon
     * @date: 2020/9/30 10:36
     */
    public Queue<Edge> edges() {
        return mst;
    }
}
