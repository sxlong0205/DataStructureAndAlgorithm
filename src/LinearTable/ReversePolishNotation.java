package LinearTable;

//逆波兰表达式问题
public class ReversePolishNotation {
    public static void main(String[] args) {
        String[] notation = {"3", "17", "15", "-", "*", "18", "6", "/", "+"};
        int result = caculate(notation);
        System.out.println("逆波兰表达式的结果为：" + result);
    }

    private static int caculate(String[] notation) {
        Stack<Integer> oprands = new Stack<>();
        for (int i = 0; i < notation.length; i++) {
            String curr = notation[i];
            Integer o1;
            Integer o2;
            Integer result;
            switch (curr) {
                case "+":
                    o1 = oprands.pop();
                    o2 = oprands.pop();
                    result = o2 + o1;
                    oprands.push(result);
                    break;
                case "-":
                    o1 = oprands.pop();
                    o2 = oprands.pop();
                    result = o2 - o1;
                    oprands.push(result);
                    break;
                case "*":
                    o1 = oprands.pop();
                    o2 = oprands.pop();
                    result = o2 * o1;
                    oprands.push(result);
                    break;
                case "/":
                    o1 = oprands.pop();
                    o2 = oprands.pop();
                    result = o2 / o1;
                    oprands.push(result);
                    break;
                default:
                    oprands.push(Integer.parseInt(curr));
                    break;
            }
        }
        return oprands.pop();
    }
}
