import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        Queue<Integer> wait = new LinkedList<>(); // 대기 트럭
        for (int i = 0; i < truck_weights.length; i++) wait.add(truck_weights[i]);

        ArrayList<Integer> cross = new ArrayList<>(); // 다리 건너는 트럭
        for (int i = 0; i < bridge_length; i++) cross.add(0);
        int cross_weight = 0; // 다리 건너는 트럭 무게 합

        // 대기 트럭이 없을 때까지
        while (!wait.isEmpty()) {
            // 다리 끝 지점에 있던 트럭 탈출
            cross_weight -= cross.get(bridge_length - 1);
            cross.remove(bridge_length - 1);

            // 대기 트럭이 출발 가능 하다면
            if (cross_weight + wait.peek() <= weight) {
                cross_weight += wait.peek();
                cross.add(0, wait.poll());
            }
            // 대기 트럭이 출발 불가능 하다면
            else cross.add(0, 0);

            time++;
        }
        // 현재, 마지막 대기 트럭이 다리 시작 지점에 있는 상황.
        return time + bridge_length;
    }
}