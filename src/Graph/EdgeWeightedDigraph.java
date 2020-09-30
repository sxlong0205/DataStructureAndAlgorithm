package Graph;


import LinearTable.Queue;

/**
 * 加权有向图
 *
 * @author : Code Dragon
 * create at:  2020/9/30  10:46
 */
public class EdgeWeightedDigraph {
    //顶点总数
    private final int V;
    //边的总数
    private int E;
    //邻接表
    private Queue<DirectedEdge>[] adj;

    /**
     * 创建一个含有V个顶点的空加权有向图
     *
     * @param V
     * @return
     * @author: Code Dragon
     * @date: 2020/9/30 10:54
     */
    public EdgeWeightedDigraph(int V) {
        //初始化顶点数量
        this.V = V;
        //初始化边的数量
        this.E = 0;
        //初始化邻接表
        this.adj = new Queue[V];
        //初始化邻接表中的空队列
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<>();
        }
    }

    /**
     * 获取图中顶点的数量
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/30 10:53
     */
    public int V() {
        return V;
    }

    /**
     * 获取图中边的数量
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/30 10:53
     */
    public int E() {
        return E;
    }

    /**
     * 向加权有向图中添加一条边e
     *
     * @param e
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/30 10:53
     */
    public void addEdge(DirectedEdge e) {
        //获取有向边的起点
        int v = e.from();
        //因为是有向图，所以边e只需要出现在起点v的邻接表中
        adj[v].enqueue(e);
        //边的数量+1
        E++;
    }

    /**
     * 获取由顶点v指出的所有的边
     *
     * @param v
     * @return LinearTable.Queue<Graph.DirectedEdge>
     * @author: Code Dragon
     * @date: 2020/9/30 10:53
     */
    public Queue<DirectedEdge> adj(int v) {
        return adj[v];
    }

    /**
     * 获取加权有向图的所有边
     *
     * @param
     * @return LinearTable.Queue<Graph.DirectedEdge>
     * @author: Code Dragon
     * @date: 2020/9/30 10:52
     */
    public Queue<DirectedEdge> edges() {
        //创建一个队列，存储所有的边
        Queue<DirectedEdge> allEdge = new Queue<>();
        //遍历顶点，拿到每个顶点的邻接表
        for (int v = 0; v < this.V; v++) {
            //遍历邻接表，拿到邻接表中的每条边存储到队列中
            for (DirectedEdge e : adj(v)) {
                allEdge.enqueue(e);
            }
        }
        return allEdge;
    }
}
