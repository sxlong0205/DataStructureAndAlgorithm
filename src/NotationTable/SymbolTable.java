package NotationTable;

public class SymbolTable<Key, Value> {
    private Node2 head;
    private int N;

    public SymbolTable() {
        head = new Node2(null, null, null);
        N = 0;
    }

    public int size() {
        return N;
    }

    public void put(Key key, Value value) {
        //先从符号表中查找键为key的键值对
        Node2 n = head;
        while (n.next != null) {
            n = n.next;
            if (n.key.equals(key)) {
                n.value = value;
                return;
            }
        }

        //符号表中没有键为key的键值对
        Node2 oldFirst = head.next;
        Node2 newFirst = new Node2(key, value, oldFirst);
        head.next = newFirst;
        N++;
    }

    /**
     * 删除符号表中键为key的键值对
     *
     * @param key
     * @return void
     * @author: Code Dragon
     * @date: 2020/9/29 10:52
     */
    public void delete(Key key) {
        Node2 n = head;
        while (n.next != null) {
            if (n.next.key.equals(key)) {
                n.next = n.next.next;
                N--;
                return;
            }
            n = n.next;
        }
    }

    /**
     * 从符号表中获取key对应的值
     *
     * @param key
     * @return Value
     * @author: Code Dragon
     * @date: 2020/9/29 10:52
     */
    public Value get(Key key) {
        Node2 n = head;
        while (n.next != null) {
            n = n.next;
            if (n.key.equals(key)) {
                return (Value) n.value;
            }
        }
        return null;
    }

}
