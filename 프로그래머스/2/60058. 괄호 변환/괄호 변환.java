import java.util.*;

class Solution {
        public Boolean isRight(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(') {
                stack.add(c);
            }
            else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
    public String[] seperate(String str) {
        String[] uv = {"", ""};
        int balance = 0;
        int i = 0;
        while (uv[0].isEmpty() || balance != 0) {
            uv[0] += str.charAt(i);
            if (str.charAt(i) == '(') balance += 1;
            else balance -= 1;
            i++;
        }
        uv[1] = str.substring(i);
        return uv;
    }
    public String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < str.length() - 1; i++) {
            if (str.charAt(i) == '(') sb.append(")");
            else sb.append("(");
        }
        return sb.toString();
    }
    public String solution(String p) {
        if (p.isEmpty()) return p; // 1. 빈 문자열인 경우, 빈 문자열을 반환

        String[] uv = seperate(p); // 2. p를 두 "균형잡힌 괄호 문자열" u, v로 분리

        // 3. u가 "올바른 괄호 문자열" 이라면
        if (isRight(uv[0])) return uv[0] + solution(uv[1]);
        // 4. u가 "올바른 괄호 문자열"이 아니라면
        else return "(" + solution(uv[1]) + ")" + reverse(uv[0]);
    }
}