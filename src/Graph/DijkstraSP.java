package Graph;

import LinearTable.Queue;
import PriorityQueue.IndexMinPriorityQueue;

/**
 * 迪杰斯特拉算法
 *
 * @author : Code Dragon
 * create at:  2020/9/30  11:29
 */
public class DijkstraSP {
    //索引代表顶点，值表示从顶点s到当前顶点的最短路径上的最后一条边
    private DirectedEdge[] edgeTo;
    //索引代表顶点，值从顶点s到当前顶点的最短路径的总权重
    private double[] distTo;
    //存放树中顶点与非树中顶点之间的有效横切边
    private IndexMinPriorityQueue<Double> pq;

    /**
     * 根据一副加权有向图G和顶点s，创建一个计算顶点为s的最短路径树对象
     *
     * @param G
     * @param s
     * @return
     * @author: Code Dragon
     * @date: 2020/9/30 11:44
     */
    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        //创建一个和图的顶点数一样大小的DirectedEdge数组，表示边
        this.edgeTo = new DirectedEdge[G.V()];
        //创建一个和图的顶点数一样大小的double数组，表示权重，
        //并且初始化数组中的内容为无穷大，无穷大即表示不存在这样的边
        this.distTo = new double[G.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        //创建一个和图的顶点数一样大小的索引优先队列，存储有效横切边
        this.pq = new IndexMinPriorityQueue<>(G.V());
        //默认让顶点s进入树中，但s顶点目前没有与树中其他的顶点相连接，因此初始化distTo[s]=0.0
        distTo[s] = 0.0;
        //使用顶点s和权重0.0初始化pq
        pq.insert(s, 0.0);
        //遍历有效边队列
        while (!pq.isEmpty())
            //松弛图G中的顶点
            relax(G, pq.delMin());
    }

    /**
     * 松弛图G中的顶点v
     *
     * @param G
     * @param v
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/30 11:43
     */
    private void relax(EdgeWeightedDigraph G, int v) {
        //松弛顶点v就是松弛顶点v邻接表中的每一条边，遍历邻接表
        for (DirectedEdge e : G.adj(v)) {
            //获取边e的终点
            int w = e.to();
            //起点s到顶点w的权重是否大于起点s到顶点v的权重+边e的权重,
            // 如果大于，则修改s->w的路径：edgeTo[w]=e,并修改distTo[v] = distTo[v]+e.weitht(),如果不大于，则忽略
            if (distTo(w) > distTo(v) + e.weight()) {
                distTo[w] = distTo[v] = e.weight();
                edgeTo[w] = e;

                //如果顶点w已经存在于优先队列pq中，则重置顶点w的权重
                if (pq.contains(w))
                    pq.changeItem(w, distTo(w));
                else
                    //如果顶点w没有出现在优先队列pq中，则把顶点w及其权重加入到pq中
                    pq.insert(w, distTo(w));
            }
        }
    }

    /**
     * 获取从顶点s到顶点v的最短路径的总权重
     *
     * @param v
     * @return double
     * @author: Code Dragon
     * @date: 2020/9/30 11:42
     */
    public double distTo(int v) {
        return distTo[v];
    }

    /**
     * 判断从顶点s到顶点v是否可达
     *
     * @param v
     * @return boolean
     * @author: Code Dragon
     * @date: 2020/9/30 11:42
     */
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * 查询从起点s到顶点v的最短路径中所有的边
     *
     * @param v
     * @return LinearTable.Queue<Graph.DirectedEdge>
     * @author: Code Dragon
     * @date: 2020/9/30 11:41
     */
    public Queue<DirectedEdge> pathTo(int v) {
        //如果顶点s到v不可达，则返回null
        if (!hasPathTo(v))
            return null;

        //创建队列Queue保存最短路径的边
        Queue<DirectedEdge> edges = new Queue<>();
        //从顶点v开始，逆向寻找，一直找到顶点s为止，
        //而起点s为最短路劲树的根结点，所以edgeTo[s]=null
        DirectedEdge e = null;
        while (true) {
            e = edgeTo[v];
            if (e == null)
                break;

            edges.enqueue(e);
            v = e.from();
        }
        return edges;
    }
}
