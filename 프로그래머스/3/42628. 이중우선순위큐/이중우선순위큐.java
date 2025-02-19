import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        TreeSet<Integer> set = new TreeSet<>();
        
        for (String operation: operations) {
            char order = operation.charAt(0);
            int num = Integer.parseInt(operation.split(" ")[1]);
            if (order == 'I') {
                set.add(num);
            } else {
                if (set.isEmpty()) continue;
                if (num == 1) {
                    set.pollLast();
                } else {
                    set.pollFirst();
                }
            }
        }
        
        if (set.isEmpty()) return new int[] {0, 0};
        else return new int[] {set.last(), set.first()};
    }
}