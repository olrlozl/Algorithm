import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        Queue<Integer> wait = new LinkedList<>(); // 대기 트럭
        for (int i = 0; i < truck_weights.length; i++) wait.add(truck_weights[i]);

        ArrayList<Integer> cross = new ArrayList<>(); // 다리 건너는 트럭
        for (int i = 0; i < bridge_length; i++) cross.add(0);
        int cross_weight = 0; // 다리 건너는 트럭 무게 합

        // 모든 트럭이 다리를 지날 때 까지
        while (!wait.isEmpty() || cross_weight > 0) {
            // 다리 건너는 트럭이 있다면
            if (cross_weight > 0) {
                // 맨 마지막 트럭 다리 탈출
                if (cross.get(bridge_length - 1) > 0)
                    cross_weight -= cross.get(bridge_length - 1);
                // 다리 건너는 트럭 한 칸씩 전진
                for (int i = bridge_length - 2; i >= 0; i--) {
                    cross.set(i + 1, cross.get(i));
                }
                cross.set(0, 0);
            }
            // 대기 트럭이 출발 가능 하다면
            if (!wait.isEmpty() && cross_weight + wait.peek() <= weight) {
                cross_weight += wait.peek();
                cross.set(0, wait.poll());
            }
            time++;
        }
        return time;
    }
}