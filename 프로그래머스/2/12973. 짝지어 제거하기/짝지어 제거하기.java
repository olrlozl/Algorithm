import java.util.*;

class Solution {
    public int solution(String s) {
        Stack<String> stack = new Stack<>();
        for (String w : s.split("")) {
            if (stack.isEmpty()) {
                stack.add(w);
                continue;
            }
            if (stack.peek().equals(w)) stack.pop();
            else stack.add(w);
        }
        return stack.isEmpty() ? 1 : 0;
    }
}