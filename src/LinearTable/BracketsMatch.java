package LinearTable;

//括号匹配
public class BracketsMatch {
    private static boolean isMatch(String str) {
        Stack<String> chars = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            String currChar = str.charAt(i) + "";

            if (currChar.equals("(")) {
                chars.push(currChar);
            } else if (currChar.equals(")")) {
                String t = chars.pop();
                if (t == null) {
                    return false;
                }
            }
        }
        if (chars.size() == 0)
            return true;
        else
            return false;
    }
}
