import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> min = new PriorityQueue<>();
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        
        for (String operation: operations) {
            char order = operation.charAt(0);
            int num = Integer.parseInt(operation.split(" ")[1]);
            if (order == 'I') {
                min.add(num);
                max.add(num);
            } else {
                if (min.isEmpty()) continue;
                if (num == 1) {
                    min.remove(max.poll());
                } else {
                    max.remove(min.poll());
                }
            }
        }
        
        if (min.isEmpty()) return new int[] {0, 0};
        else return new int[] {max.poll(), min.poll()};
    }
}