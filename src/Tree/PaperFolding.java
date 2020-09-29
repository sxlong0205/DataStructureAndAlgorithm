package Tree;

import LinearTable.Queue;

/**
 * 对折纸折痕问题
 *
 * @author : Code Dragon
 * create at:  2020/9/29  10:41
 */
public class PaperFolding {

    /**
     * 1.定义节点类
     *
     * @author: Code Dragon
     * @date: 2020/9/29 10:43
     * @return
     */
    private static class Node {
        //存储节点元素
        String item;
        //左子结点
        Node left;
        //右子节点
        Node right;

        public Node(String item, Node left, Node right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 2.构建深度为N的折痕树
     *
     * @param N
     * @return Tree.PaperFolding.Node
     * @author: Code Dragon
     * @date: 2020/9/29 10:47
     */
    private static Node createTree(int N) {
        Node root = null;
        for (int i = 0; i < N; i++) {
            if (i == 0)
                //1.第一次对折，只有一条折痕，创建根结点
                root = new Node("down", null, null);
            else {
                //2.如果不是第一次对折，则使用队列保存根结点
                Queue<Node> queue = new Queue<>();
                queue.enqueue(root);
                //3.循环遍历队列
                while (!queue.isEmpty()) {
                    //3.1从队列中拿出一个结点
                    Node tmp = queue.dequeue();
                    //3.2如果这个结点的左子结点不为空，则把这个左子结点添加到队列中
                    if (tmp.left != null)
                        queue.enqueue(tmp.left);
                    //3.3如果这个结点的右子结点不为空，则把这个右子结点添加到队列中
                    if (tmp.right != null)
                        queue.enqueue(tmp.right);
                    //3.4判断当前结点的左子结点和右子结点都不为空，
                    // 如果是，则需要为当前结点创建一个值为down的左子结点，
                    // 一个值为up的右子结点
                    if (tmp.left == null && tmp.right == null) {
                        tmp.left = new Node("down", null, null);
                        tmp.right = new Node("up", null, null);
                    }
                }
            }
        }
        return root;
    }

    /**
     * 3.使用中序遍历，打印出树中所有结点的内容
     *
     * @param tree
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 10:50
     */
    private static void printTree(Node tree) {
        if (tree == null)
            return;

        printTree(tree.left);
        System.out.println(tree.item + ",");
        printTree(tree.right);
    }
}
