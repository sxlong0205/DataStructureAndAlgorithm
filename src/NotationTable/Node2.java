package NotationTable;

public class Node2<Key, Value> {
    public Key key;
    public Value value;
    public Node2 next;

    public Node2(Key key, Value value, Node2 next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
