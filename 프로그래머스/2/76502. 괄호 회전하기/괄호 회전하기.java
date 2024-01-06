import java.util.*;

class Solution {
    public boolean check (HashMap<Character, Character> map, Queue<Character> queue) {
        Stack<Character> stack = new Stack<>();
        for (Character c : queue) {
            if (c == '(' || c == '{' || c == '[') {
                stack.add(c);
                continue;
            }
            if (!stack.isEmpty() && stack.peek() == map.get(c)) stack.pop();
            else return false;
        }
        return stack.isEmpty();
    }
    
    public int solution(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '('); map.put('}', '{'); map.put(']', '[');

        int cnt = 0;
        Queue<Character> queue = new LinkedList<>();
        for (Character c : s.toCharArray()) queue.add(c);

        for (int i = 0; i < s.length(); i++) {
            if (check(map, queue)) cnt++;
            queue.add(queue.poll());
        }
        return cnt;
    }
}