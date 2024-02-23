import java.util.*;

class Solution {
    public int solution(String s) {
        int min = Integer.MAX_VALUE;
        
        for (int len = 1; len <= s.length(); len++) {
             String res = "";
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
                            res += queue.poll();
                        } else if (queue.size() > 1) {
                            res += Integer.toString(queue.size()) + queue.peek();
                            queue.clear();
                        }
                        queue.add(now);
                    }
                }
            }
            
            if (queue.size() == 1) res += queue.poll();
            else if (queue.size() > 1) res += Integer.toString(queue.size()) + queue.peek();
            
            min = Math.min(min, res.length());
        }
       
        return min;
    }
}