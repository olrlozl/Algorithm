import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) pq.add(work);
        
        for (int i = 0; i < n; i++) {
            if (pq.isEmpty()) break;
            int work = pq.poll();
            if (work > 1) pq.add(work - 1);
        }
        
        long answer = 0;
        int size = pq.size();
        for (int i = 0; i < size; i++) {
            int work = pq.poll();
            answer += Math.pow(work, 2);
        }
        return answer;
    }
}