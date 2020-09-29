package Tree;

/**
 * 红黑树
 *
 * @author : Code Dragon
 * create at:  2020/9/29  15:09
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> {
    private class Node<Key, Value> {
        //存储键
        public Key key;
        //存储值
        private Value value;
        //记录左子节点
        public Node left;
        //记录右子节点
        public Node right;
        //由其父节点指向它的链接的颜色
        public boolean color;

        public Node(Key key, Value value, Node left, Node right, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }

    //根节点
    private Node root;
    //记录树中元素的个数
    private int N;
    //红色链接
    private static final boolean RED = true;
    //黑色链接
    private static final boolean BLACK = false;

    /**
     * 判断当前节点的父指向链接是否为红色
     *
     * @param x
     * @return boolean
     * @author: Code Dragon
     * @date: 2020/9/29 15:31
     */
    private boolean isRed(Node x) {
        //空结点默认是黑色链接
        if (x == null)
            return false;
        //非空结点需要判断结点color属性的值
        return x.color == RED;
    }

    /**
     * 左旋操作
     *
     * @param h
     * @return Tree.RedBlackTree<Key, Value>.Node
     * @author: Code Dragon
     * @date: 2020/9/29 15:33
     */
    private Node rotateLeft(Node h) {
        //找出当前节点h的右子节点
        Node hRight = h.right;

        //找出右子节点的左子节点
        Node lhRight = hRight.left;

        //让h的右子节点的左子节点h的右子节点
        h.right = lhRight;

        //让h成为右子节点的左子节点
        hRight.left = h;

        //让h的color成为右子节点的color
        hRight.color = h.color;

        //让h的color变为RED
        h.color = RED;

        //返回右子节点
        return hRight;
    }

    /**
     * 右旋操作
     *
     * @param h
     * @return Tree.RedBlackTree<Key, Value>.Node
     * @author: Code Dragon
     * @date: 2020/9/29 15:37
     */
    private Node rotateRight(Node h) {
        //找出当前节点h的左子节点
        Node hLeft = h.left;

        //找出当前节点h的左子节点的右子节点
        Node rHleft = hLeft.right;

        //让当前节点h的左子节点的右子节点称为当前节点的左子节点
        h.left = rHleft;

        //让当前节点称为左子节点的右子节点
        hLeft.right = h;

        //让当前节点h的color值称为左子节点的color值
        hLeft.color = h.color;

        //让当前节点h的color变为RED
        h.color = RED;

        //返回当前节点的左子节点
        return hLeft;
    }

    /**
     * 颜色反转,相当于完成拆分4-节点
     *
     * @param h
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 15:38
     */
    private void flipColors(Node h) {
        //当前节点的color属性值变为RED
        h.color = RED;

        //当前节点的左右子节点的color属性值都变为黑色
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    /**
     * 在整个树上完成插入操作
     *
     * @param key
     * @param value
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 15:43
     */
    public void put(Key key, Value value) {
        //在root整个树上插入key-val
        root = put(root, key, value);
        //让根节点的颜色变为BLACK
        root.color = BLACK;
    }

    /**
     * 在指定树中，完成插入操作,并返回添加元素后新的树
     *
     * @param h
     * @param key
     * @param value
     * @return Tree.RedBlackTree<Key, Value>.Node
     * @author: Code Dragon
     * @date: 2020/9/29 15:44
     */
    private Node put(Node h, Key key, Value value) {
        if (h == null) {
            N++;
            //标准的插入操作，和父节点用红链接相连
            return new Node(key, value, null, null, RED);
        }

        //比较要插入的键和当前节点的键
        int cmp = key.compareTo((Key) h.key);
        if (cmp < 0) {
            //继续寻找左子树插入
            h.left = put(h.left, key, value);
        } else if (cmp > 0) {
            //继续寻找右子树插入
            h.right = put(h.right, key, value);
        } else {
            //已经有相同的结点存在，修改节点的值
            h.value = value;
        }

        //如果当前节点的右链接是红色，左链接是黑色，需要左旋
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);

        //如果当前节点的左子节点和左子节点的左子节点都是红色链接，则需要右旋
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }

        //如果当前节点的左链接和右链接都是红色，需要颜色变换
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        //返回当前节点
        return h;
    }

    /**
     * 根据key，从树中找出对应的值
     *
     * @param key
     * @return Value
     * @author: Code Dragon
     * @date: 2020/9/29 15:54
     */
    public Value get(Key key) {
        return get(root, key);
    }

    /**
     * 从指定的树x中，查找key对应的值
     *
     * @param x
     * @param key
     * @return Value
     * @author: Code Dragon
     * @date: 2020/9/29 15:57
     */
    private Value get(Node x, Key key) {
        //如果当前结点为空，则没有找到,返回null
        if (x == null)
            return null;

        //比较当前结点的键和key
        int cmp = key.compareTo((Key) x.key);
        if (cmp < 0) {
            //如果要查询的key小于当前节点的key，则继续找当前节点的左子节点
            return (Value) get(x.left, key);
        } else if (cmp > 0) {
            //如果要查询的key大于当前结点的key，则继续找当前结点的右子结点
            return (Value) get(x.right, key);
        } else {
            //如果要查询的key等于当前结点的key，则树中返回当前结点的value
            return (Value) x.value;
        }
    }

    /**
     * 获取树中元素的个数
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 15:59
     */
    public int size() {
        return N;
    }
}
