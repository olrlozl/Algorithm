import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int result = -1;
        int cnt = 0;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long sum1 = 0;
        long sum2 = 0;

        for (int q : queue1) {
            q1.add(q);
            sum1 += q;
        }

        for (int q : queue2) {
            q2.add(q);
            sum2 += q;
        }

        while (cnt <= queue1.length * 4) {
            if (sum1 > sum2) {
                sum1 -= q1.peek();
                sum2 += q1.peek();
                q2.add(q1.poll());
            } else if (sum1 < sum2) {
                sum1 += q2.peek();
                sum2 -= q2.peek();
                q1.add(q2.poll());
            } else {
                result = cnt;
                break;
            }
            cnt++;
        }
        
        return result;
    }
}