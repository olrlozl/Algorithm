import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int cnt = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) pq.add(s);
        
        while (pq.peek() < K) {
            if (pq.size() == 1) return -1;
            pq.add(pq.poll() + pq.poll() * 2);
            cnt++;
        }
        
        return cnt;
    }
}