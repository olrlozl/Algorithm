import java.util.*;

class Solution {
    public int solution(String s) {
        int min = s.length();
        
        for (int len = 1; len <= s.length() / 2; len++) {
             StringBuilder res = new StringBuilder();
            Queue<String> queue = new LinkedList<>();

            for (int i = 0; i < s.length(); i+=len) {
                int endIdx = i + len <= s.length() ? i + len : s.length();
                String now = s.substring(i, endIdx);
                
                if (queue.isEmpty()) {
                    queue.add(now);
                } else {
                    if (queue.peek().equals(now)) {
                        queue.add(now);
                    } else {
                        if (queue.size() == 1) {
                            res.append(queue.poll());
                        } else if (queue.size() > 1) {
                            res.append(Integer.toString(queue.size()) + queue.peek());
                            queue.clear();
                        }
                        queue.add(now);
                    }
                }
            }
            
            if (queue.size() == 1) res.append(queue.poll());
            else if (queue.size() > 1) res.append(Integer.toString(queue.size()) + queue.peek());
            
            min = Math.min(min, res.toString().length());
        }
       
        return min;
    }
}