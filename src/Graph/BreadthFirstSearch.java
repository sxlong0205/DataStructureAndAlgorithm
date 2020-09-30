package Graph;

import LinearTable.Queue;

/**
 * 广度优先搜索遍历
 *
 * @author : Code Dragon
 * create at:  2020/9/29  18:38
 */
public class BreadthFirstSearch {
    //索引代表顶点，值表示当前顶点是否已经被搜索
    private boolean[] marked;
    //记录有多少个顶点与s顶点相通
    private int count;
    //用来存储待搜索邻接表的点
    private Queue<Integer> waitSearch;

    /**
     * 构造广度优先搜索对象，使用广度优先搜索找出G图中s顶点的所有相邻顶点
     *
     * @param G
     * @param s
     * @return
     * @author: Code Dragon
     * @date: 2020/9/29 18:41
     */
    public BreadthFirstSearch(Graph G, int s) {
        //创建一个和图的顶点数一样大小的布尔数组
        marked = new boolean[G.V()];
        //初始化待搜索顶点的队列
        waitSearch = new Queue<>();
        //搜索G图中与顶点s相同的所有顶点
        dfs(G, s);
    }

    /**
     * 使用广度优先搜索找出G图中v顶点的所有相邻顶点
     *
     * @param G
     * @param v
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 18:43
     */
    private void dfs(Graph G, int v) {
        //把当前顶点v标记为已搜索
        marked[v] = true;
        //把当前顶点v放入到队列中，等待搜索它的邻接表
        waitSearch.enqueue(v);
        //使用while循环从队列中拿出待搜索的顶点wait，进行搜索邻接表
        while (!waitSearch.isEmpty()) {
            Integer wait = waitSearch.dequeue();
            //遍历wait顶点的邻接表，得到每一个顶点w
            for (Integer w : G.adj(wait)) {
                //如果当前顶点w没有被搜索过，则递归搜索与w顶点相通的其他顶点
                if (!marked[w])
                    dfs(G, w);
            }
        }
        //相通的顶点数量+1
        count++;
    }

    /**
     * 判断w顶点与s顶点是否相通
     *
     * @param w
     * @return boolean
     * @author: Code Dragon
     * @date: 2020/9/29 18:44
     */
    public boolean marked(int w) {
        return marked[w];
    }

    /**
     * 获取与顶点s相通的所有顶点的总数
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 18:44
     */
    public int count() {
        return count;
    }
}
