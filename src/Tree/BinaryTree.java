package Tree;

import LinearTable.Queue;

/**
 * @author : Code Dragon
 * create at:  2020/9/29  9:34
 */
public class BinaryTree<Key extends Comparable<Key>, Value> {
    //记录根结点
    private Node3 root;
    //记录树中元素的个数
    private int N;

    /**
     * 获取树中元素的个数
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 10:36
     */
    public int size() {
        return N;
    }

    /**
     * 向树中添加元素key-value
     *
     * @param key
     * @param value
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 10:36
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    /**
     * 向指定的树x中添加key-value,并返回添加元素后新的树
     *
     * @param x
     * @param key
     * @param value
     * @return Tree.Node3
     * @author: Code Dragon
     * @date: 2020/9/29 10:36
     */
    private Node3 put(Node3 x, Key key, Value value) {
        //如果树为空，那么插入的节点就是根节点
        if (x == null) {
            N++;
            return new Node3(key, value, null, null);
        }

        int cmp = key.compareTo((Key) x.key);
        if (cmp > 0) {
            //新结点的key大于当前结点的key，继续找当前结点的右子结点
            x.right = put(x.right, key, value);
        } else if (cmp < 0) {
            //新结点的key小于当前结点的key，继续找当前结点的左子结点
            x.left = put(x.left, key, value);
        } else {
            //新结点的key等于当前结点的key，把当前结点的value进行替换
            x.value = value;
        }
        return x;
    }

    /**
     * 查询树中指定key对应的value
     *
     * @param key
     * @return Value
     * @author: Code Dragon
     * @date: 2020/9/29 10:36
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
     * @date: 2020/9/29 10:35
     */
    private Value get(Node3 x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo((Key) x.key);
        if (cmp > 0) {
            //如果要查询的key大于当前结点的key，则继续找当前结点的右子结点；
            return get(x.right, key);
        } else if (cmp < 0) {
            //如果要查询的key小于当前结点的key，则继续找当前结点的左子结点；
            return get(x.left, key);
        } else
            //如果要查询的key等于当前结点的key，则树中返回当前结点的value。
            return (Value) x.value;
    }

    /**
     * 删除树中key对应的value
     *
     * @param key
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 10:35
     */
    public void delete(Key key) {
        root = delete(root, key);
    }

    /**
     * 删除指定树x中的key对应的value，并返回删除后的新树
     *
     * @param x
     * @param key
     * @return Tree.Node3
     * @author: Code Dragon
     * @date: 2020/9/29 10:35
     */
    private Node3 delete(Node3 x, Key key) {
        if (x == null)
            return null;

        int cmp = key.compareTo((Key) x.key);
        if (cmp > 0) {
            //新结点的key大于当前结点的key，继续找当前结点的右子结点
            x.right = delete(x.right, key);
        } else if (cmp < 0) {
            //新结点的key小于当前结点的key，继续找当前结点的左子结点
            x.left = delete(x.left, key);
        } else {
            //新结点的key等于当前结点的key,当前x就是要删除的结点
            //1.如果当前结点的右子树不存在，则直接返回当前结点的左子结点
            if (x.right == null)
                return x.left;

            //2.如果当前结点的左子树不存在，则直接返回当前结点的右子结点
            if (x.left == null)
                return x.right;

            //3.当前结点的左右子树都存在
            //3.1找到右子树中最小的结点
            Node3 minNode = x.right;
            while (minNode.left != null) {
                minNode = minNode.left;
            }

            //3.2删除右子树中最小的结点
            Node3 n = x.right;

            while (n.left != null) {
                if (n.left.left == null)
                    n.left = null;
                else
                    n = n.left;
            }

            //3.3让被删除结点的左子树称为最小结点minNode的左子树，
            // 让被删除结点的右子树称为最小结点minNode的右子树
            minNode.left = x.left;
            minNode.right = x.right;
            //3.4让被删除结点的父节点指向最小结点minNode
            x = minNode;
            //个数-1
            N--;
        }
        return x;
    }

    /**
     * 找出整个树种中最小的键
     *
     * @param
     * @return Key
     * @author: Code Dragon
     * @date: 2020/9/29 10:35
     */
    public Key min() {
        return (Key) min(root).key;
    }

    /**
     * 找出指定树x中最小的键所在的节点
     *
     * @param x
     * @return Tree.Node3
     * @author: Code Dragon
     * @date: 2020/9/29 10:35
     */
    private Node3 min(Node3 x) {
        if (x.left != null)
            return min(x.left);
        else
            return x;
    }

    /**
     * 找出整个树中最大的键
     *
     * @param
     * @return Key
     * @author: Code Dragon
     * @date: 2020/9/29 10:35
     */
    public Key max() {
        return (Key) max(root).key;
    }

    /**
     * 找出指定树x中最大键所在的结点
     *
     * @param x
     * @return Tree.Node3
     * @author: Code Dragon
     * @date: 2020/9/29 10:35
     */
    public Node3 max(Node3 x) {
        if (x.right != null)
            return max(x.right);
        else
            return x;
    }

    /**
     * 使用前序遍历，获取整个树中的所有键
     *
     * @param
     * @return LinearTable.Queue<Key>
     * @author: Code Dragon
     * @date: 2020/9/29 10:35
     */
    public Queue<Key> preErgodic() {
        Queue<Key> keys = new Queue<>();
        preErgodic(root, keys);
        return keys;
    }

    /**
     * 使用前序遍历，把指定树x中的所有键放入到keys队列中
     *
     * @param x
     * @param keys
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 10:34
     */
    private void preErgodic(Node3 x, Queue<Key> keys) {
        if (x == null)
            return;

        //1.把当前结点的key放入到队列中;
        keys.enqueue((Key) x.key);
        //2.找到当前结点的左子树，如果不为空，递归遍历左子树
        if (x.left != null)
            preErgodic(x.left, keys);

        //3.找到当前结点的右子树，如果不为空，递归遍历右子树
        if (x.right != null)
            preErgodic(x.right, keys);
    }

    /**
     * 使用中序遍历，获取整个树中的所有
     *
     * @param
     * @return LinearTable.Queue<Key>
     * @author: Code Dragon
     * @date: 2020/9/29 10:34
     */
    public Queue<Key> midErgodic() {
        Queue<Key> keys = new Queue<>();
        midErgodic(root, keys);
        return keys;
    }

    /**
     * 使用中序遍历，把指定树x中的所有键放入到keys队列中
     *
     * @param x
     * @param keys
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 10:34
     */
    private void midErgodic(Node3 x, Queue<Key> keys) {
        if (x == null)
            return;

        //1.找到当前结点的左子树，如果不为空，递归遍历左子树
        if (x.left != null)
            midErgodic(x.left, keys);

        //2.把当前结点的key放入到队列中
        keys.enqueue((Key) x.key);

        //3.找到当前结点的右子树，如果不为空，递归遍历右子树
        if (x.right != null)
            midErgodic(x.right, keys);
    }

    /**
     * 使用后序遍历，获取整个树中的所有键
     *
     * @param
     * @return LinearTable.Queue<Key>
     * @author: Code Dragon
     * @date: 2020/9/29 10:34
     */
    public Queue<Key> afterErgodic() {
        Queue<Key> keys = new Queue<>();
        afterErgodic(root, keys);
        return keys;
    }

    /**
     * 使用后序遍历，把指定树x中的所有键放入到keys队列中
     *
     * @param x
     * @param keys
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 10:34
     */
    private void afterErgodic(Node3 x, Queue<Key> keys) {
        if (x == null)
            return;

        //1.找到当前结点的左子树，如果不为空，递归遍历左子树
        if (x.left != null)
            afterErgodic(x.left, keys);

        //2.找到当前结点的右子树，如果不为空，递归遍历右子树
        if (x.right != null)
            afterErgodic(x.right, keys);

        //3.把当前结点的key放入到队列中;
        keys.enqueue((Key) x.key);
    }

    /**
     * 使用层序遍历得到树中所有的键
     *
     * @param
     * @return LinearTable.Queue<Key>
     * @author: Code Dragon
     * @date: 2020/9/29 10:34
     */
    public Queue<Key> layerErgodic() {
        Queue<Key> keys = new Queue<>();
        Queue<Node3> nodes = new Queue<>();
        nodes.enqueue(root);
        while (!nodes.isEmpty()) {
            Node3 x = nodes.dequeue();
            keys.enqueue((Key) x.key);
            if (x.left != null)
                nodes.enqueue(x.left);
            if (x.right != null)
                nodes.enqueue(x.right);
        }
        return keys;
    }

    /**
     * 计算整个树的最大深度
     *
     * @param
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 10:31
     */
    public int maxDepth() {
        return maxDepth(root);
    }

    /**
     * 计算指定树x的最大深度
     *
     * @param x
     * @return int
     * @author: Code Dragon
     * @date: 2020/9/29 10:32
     */
    private int maxDepth(Node3 x) {
        //1.如果根结点为空，则最大深度为0；
        if (x == null)
            return 0;

        int max = 0;
        int maxL = 0;
        int maxR = 0;

        //2.计算左子树的最大深度；
        if (x.left != null)
            maxL = maxDepth(x.left);

        //3.计算右子树的最大深度；
        if (x.right != null)
            maxR = maxDepth(x.right);

        //4.当前树的最大深度=左子树的最大深度和右子树的最大深度中的较大者+1
        max = maxL > maxR ? maxL + 1 : maxR + 1;
        return max;
    }
}
