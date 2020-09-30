package Graph;

import LinearTable.Stack;

/**
 * 拓扑排序
 *
 * @author : Code Dragon
 * create at:  2020/9/29  19:36
 */
public class TopoLogical {
    //顶点的拓扑排序
    private Stack<Integer> order;

    /**
     * 构造拓扑排序对象
     *
     * @param G
     * @return
     * @author: Code Dragon
     * @date: 2020/9/29 19:38
     */
    public TopoLogical(Digraph G) {
        //创建检测环对象，检测图G中是否有环
        DirectedCycle dCycle = new DirectedCycle(G);
        if (!dCycle.hasCycle()) {
            //如果没有环，创建顶点排序对象，进行顶点排序
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
        }
    }

    /**
     * 判断图G是否有环
     *
     * @param
     * @return boolean
     * @author: Code Dragon
     * @date: 2020/9/29 19:38
     */
    private boolean isCycle() {
        return order == null;
    }

    /**
     * 获取拓扑排序的所有顶点
     *
     * @param
     * @return LinearTable.Stack<java.lang.Integer>
     * @author: Code Dragon
     * @date: 2020/9/29 19:38
     */
    public Stack<Integer> order() {
        return order;
    }
}
