package Graph;

/**
 * 加权有向图
 *
 * @author : Code Dragon
 * create at:  2020/9/30  10:42
 */
public class DirectedEdge {
    //起点
    private final int v;
    //终点
    private final int w;
    //权重
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * 获取有向边权重
     *
     * @param
     * @return double
     * @author: Code Dragon
     * @date: 2020/9/30 10:43
     */
    public double weight() {
        return weight;
    }

    /**
     * 获取有向边起点
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/30 10:43
     */
    public int from() {
        return v;
    }

    /**
     * 获取有向边终点
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/30 10:43
     */
    public int to() {
        return w;
    }
}
