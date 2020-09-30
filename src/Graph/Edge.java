package Graph;

/**
 * @author : Code Dragon
 * create at:  2020/9/29  19:43
 */
public class Edge implements Comparable<Edge> {
    private final int v;//顶点一
    private final int w;//顶点二
    private final double weight;//当前边的权重

    /**
     * 通过顶点v和w，以及权重weight值构造一个边对象
     *
     * @param v
     * @param w
     * @param weight
     * @return
     * @author: Code Dragon
     * @date: 2020/9/29 19:49
     */
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * 获取边的权重值
     *
     * @param
     * @return double
     * @author: Code Dragon
     * @date: 2020/9/29 19:49
     */
    public double weight() {
        return weight;
    }

    /**
     * 获取边上的一个点
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 19:49
     */
    public int either() {
        return v;
    }

    /**
     * 获取边上除了顶点vertex外的另外一个顶点
     *
     * @param vertex
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 19:48
     */
    public int other(int vertex) {
        if (vertex == v)
            //如果传入的顶点vertext是v，则返回另外一个顶点w
            return w;

        else
            //如果传入的顶点vertext不是v，则返回v即可
            return v;
    }

    @Override
    public int compareTo(Edge that) {
        int cmp = 0;
        //如果当前边的权重大于参数边that的权重，返回1
        if (this.weight() > that.weight())
            cmp = 1;
            //如果当前边的权重小于参数边that的权重，返回-1
        else if (this.weight() < that.weight())
            cmp--;
        return cmp;
    }
}
