import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int N = truck_weights.length;
        int onWeight = 0;
        int onCnt = 0;
        int passCnt = 0;
        int idx = 0;
        int time = 0;
        
        Queue<Integer> bridge = new LinkedList<>();
        for (int i = 0; i < bridge_length; i++) bridge.add(0);
        
        while (passCnt < N) {
            // 다리를 빠져나올 트럭이 있다면
            if (bridge.peek() > 0) {
                onWeight -= bridge.peek();
                onCnt--;
                passCnt++;
            }
            // 한 칸씩 이동
            bridge.poll();
            
            // 새로운 트럭이 건널 수 있다면
            if (idx < N && onCnt < bridge_length && onWeight + truck_weights[idx] <= weight) {
                bridge.add(truck_weights[idx]);
                onWeight += truck_weights[idx++];
                onCnt++;
            } else {
                bridge.add(0);
            }
            time++;
        }
    
        return time;
    }
}