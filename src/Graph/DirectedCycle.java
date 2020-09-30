package Graph;

/**
 * 检测有向图中是否有环
 *
 * @author : Code Dragon
 * create at:  2020/9/29  19:21
 */
public class DirectedCycle {
    //索引代表顶点，值表示当前顶点是否已经被搜索
    private boolean[] marked;
    private boolean hasCycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];

        this.hasCycle = false;

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v])
                dfs(G, v);
        }
    }

    /**
     * 基于深度优先搜索，检测图G中是否有环
     *
     * @param G
     * @param v
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 19:26
     */
    private void dfs(Digraph G, int v) {
        //把当前顶点标记为已搜索
        marked[v] = true;
        //让当前顶点进栈
        onStack[v] = true;
        //遍历v顶点的邻接表，得到每一个顶点w
        for (Integer w : G.adj(v)) {
            //如果当前顶点w没有被搜索过，则递归搜索与w顶点相通的其他顶点
            if (!marked[w])
                dfs(G, w);

            //如果顶点w已经被搜索过，则查看顶点w是否在栈中，
            // 如果在，则证明图中有环，修改hasCycle标记，结束循环
            if (onStack[w]) {
                hasCycle = true;
                return;
            }
        }
        //当前顶点已经搜索完毕，让当前顶点出栈
        onStack[v] = false;
    }

    /**
     * 判断w顶点与s顶点是否相通
     *
     * @param
     * @return boolean
     * @author: Code Dragon
     * @date: 2020/9/29 19:25
     */
    public boolean hasCycle() {
        return hasCycle;
    }


}
