import java.util.*;

class Solution {
    
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int round = 0;

        // 무적권 스킬 있는대로 일단 다 쓰기
        for (int i = 0; i < Math.min(k, enemy.length); i++) {
            pq.add(enemy[i]);
            round++;
        }

        for (int i = k; i < enemy.length; i++) {
            if (Math.min(pq.peek(), enemy[i]) <= n) { // 다음 라운드로 넘어갈 수 있다면
                round++;
                if (pq.peek() < enemy[i]) { // 무적권 스킬을 사용했던 라운드의 적 < 이번 라운드의 적
                    n -= pq.poll();
                    pq.add(enemy[i]);
                }
                else n -= enemy[i]; // 무적권 스킬을 사용했던 라운드의 적 >= 이번 라운드의 적
            } 
            else break; // 다음 라운드로 넘어갈 수 없다면
        }
        
        return round;
    }
}