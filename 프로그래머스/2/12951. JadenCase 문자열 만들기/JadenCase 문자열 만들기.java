import java.util.stream.*;

class Solution {
    public String solution(String s) {
        String result = "";
        boolean flag = true;

        for (char c : s.toLowerCase().toCharArray()) {
            result += flag ? Character.toUpperCase(c) : c;
            flag = c == ' ';
        }
        
        return result;
    }
}