import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int[] endTime = new int[jobs.length];
        
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]); // 요청시각이 빠른 순 정렬
        
        PriorityQueue<Work> pq = new PriorityQueue<>();
        int curTime = 0;
        int curIdx = 0;
        int finish = 0;
        
        while (finish < jobs.length) {
            while (curIdx < jobs.length && jobs[curIdx][0] <= curTime) {
                pq.add(new Work(curIdx, jobs[curIdx][0], jobs[curIdx][1]));
                curIdx++;
            }
            if (pq.isEmpty() && curIdx < jobs.length) {
                curTime++;
            } else {
                Work w = pq.poll();
                curTime += w.require;
                endTime[w.idx] = curTime - w.in;
                finish++;
            }
        }
        
        int avg = 0;
        for (int t : endTime)  avg += t;
        avg /= jobs.length;
        return avg;
    }
    
    public class Work implements Comparable<Work>{
        int idx; // 작업번호
        int in; // 요청시각
        int require; // 소요시간
        
        public Work (int idx, int in, int require) {
            this.idx = idx;
            this.in = in;
            this.require = require;
        }
        
        @Override
        public int compareTo(Work o) {
            if (this.require != o.require) return this.require - o.require;
            if (this.in != o.in) return this.in - o.in;
            return this.idx - o.idx;
        }
    }
}