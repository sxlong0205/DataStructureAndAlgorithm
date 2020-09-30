package Graph;

import LinearTable.Stack;

/**
 * 顶点排序
 *
 * @author : Code Dragon
 * create at:  2020/9/29  19:32
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Stack<Integer> reversePost;

    /**
     * 创建一个检测环对象，检测图G中是否有环
     *
     * @param G
     * @return
     * @author: Code Dragon
     * @date: 2020/9/29 19:35
     */
    public DepthFirstOrder(Digraph G) {
        //创建一个和图的顶点数一样大小的marked数组
        marked = new boolean[G.V()];
        reversePost = new Stack<>();
        //遍历搜索图中的每一个顶点
        for (int v = 0; v < G.V(); v++) {
            //如果当前顶点没有搜索过，则搜索
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    /**
     * 基于深度优先搜索，检测图G中是否有环
     *
     * @param G
     * @param v
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 19:34
     */
    private void dfs(Digraph G, int v) {
        //把当前顶点标记为已搜索
        marked[v] = true;
        //遍历v顶点的邻接表，得到每一个顶点w
        for (Integer w : G.adj(v)) {
            //如果当前顶点w没有被搜索过，则递归搜索与w顶点相通的其他顶点
            if (!marked[w])
                dfs(G, w);
        }
        reversePost.push(v);
    }

    /**
     * 获取顶点线性序列
     *
     * @param
     * @return LinearTable.Stack<java.lang.Integer>
     * @author: Code Dragon
     * @date: 2020/9/29 19:34
     */
    public Stack<Integer> reversePost() {
        return reversePost;
    }
}
