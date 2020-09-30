package Graph;

import LinearTable.Queue;

/**
 * 加权无向图
 *
 * @author : Code Dragon
 * create at:  2020/9/29  19:50
 */
public class EdgeWeightedGraph {
    //顶点总数
    private final int V;
    //边的总数
    private int E;
    //邻接表
    private Queue<Edge>[] adj;

    /**
     * 创建一个含有V个顶点的空加权无向图
     *
     * @param V
     * @return
     * @author: Code Dragon
     * @date: 2020/9/29 19:57
     */
    public EdgeWeightedGraph(int V) {
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
     * @date: 2020/9/29 19:57
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
     * @date: 2020/9/29 19:57
     */
    public int E() {
        return E;
    }

    /**
     * 向加权无向图中添加一条边e
     *
     * @param e
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 19:57
     */
    public void addEdge(Edge e) {
        //获取边中的一个顶点v
        int v = e.either();
        //获取边中的另一个顶点w
        int w = e.other(v);
        //因为是无向图，所以边e需要同时出现在两个顶点的邻接表中
        adj[v].enqueue(e);
        adj[w].enqueue(e);
        //边的数量+1
        E++;
    }

    /**
     * 获取和顶点v关联的所有边
     *
     * @param v
     * @return LinearTable.Queue<Graph.Edge>
     * @author: Code Dragon
     * @date: 2020/9/29 19:56
     */
    public Queue<Edge> adj(int v) {
        return adj[v];
    }

    /**
     * 获取加权无向图的所有边
     *
     * @param
     * @return LinearTable.Queue<Graph.Edge>
     * @author: Code Dragon
     * @date: 2020/9/29 19:56
     */
    public Queue<Edge> edges() {
        //创建一个队列，存储所有的边
        Queue<Edge> allEdge = new Queue<>();
        //遍历顶点，拿到每个顶点的邻接表
        for (int v = 0; v < this.V; v++) {
            //遍历邻接表，拿到邻接表中的每条边
            for (Edge e : adj(v)) {
                //因为无向图中，每条边对象Edge都会在两个顶点的邻接表中各出现一次，
                // 为了不重复获取，暂定一条规则：
                //除了当前顶点v，再获取边e中的另外一个顶点w，
                // 如果v<w则添加，这样可以保证同一条边只会被统计一次
                if (e.other(v) < v) {
                    allEdge.enqueue(e);
                }
            }
        }
        return allEdge;
    }
}
