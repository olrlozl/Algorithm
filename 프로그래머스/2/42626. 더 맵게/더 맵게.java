import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int cnt = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) pq.add(s);
        
        while (pq.size() >= 2 && pq.peek() < K) {
            cnt++;
            pq.add(pq.poll() + pq.poll() * 2);
        }
        
        if (pq.peek() < K) return -1;
        
        return cnt;
    }
}