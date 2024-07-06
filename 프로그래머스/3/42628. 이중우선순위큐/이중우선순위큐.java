import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        
        for (String o : operations) {
            int x = Integer.parseInt(o.split(" ")[1]);
            if (o.charAt(0) == 'I') {
                min.add(x);
                max.add(x);
            } else if (o.charAt(0) == 'D') {
                if (min.isEmpty()) continue;
                if (x == -1) {
                    max.remove(min.poll());
                } else if (x == 1) {
                    min.remove(max.poll());
                }
            }
        }
        
        int[] answer = new int[2];
        if (!min.isEmpty()) {
            answer[0] = max.peek();
            answer[1] = min.peek();
        }
        return answer;
    }
}