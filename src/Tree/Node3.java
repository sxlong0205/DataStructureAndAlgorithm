package Tree;

/**
 * @author : Code Dragon
 * create at:  2020/9/29  9:25
 */
public class Node3<Key, Value> {
    public Key key;
    Value value;
    public Node3 left;
    public Node3 right;

    public Node3(Key key, Value value, Node3 left, Node3 right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
