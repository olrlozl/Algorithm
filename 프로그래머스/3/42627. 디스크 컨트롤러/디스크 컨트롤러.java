import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]); // 요청시각이 빠른 순 정렬
        
        PriorityQueue<Work> pq = new PriorityQueue<>();
        int curTime = 0;
        int jobIdx = 0;
        int finish = 0;
        
        while (finish < jobs.length) {
            while (jobIdx < jobs.length && jobs[jobIdx][0] <= curTime) {
                pq.add(new Work(jobs[jobIdx][0], jobs[jobIdx][1]));
                jobIdx++;
            }
            if (pq.isEmpty() && jobIdx < jobs.length) {
                curTime = jobs[jobIdx][0];
            } else {
                Work w = pq.poll();
                curTime += w.require;
                answer += curTime - w.in;
                finish++;
            }
        }
        
        return answer / jobs.length;
    }
    
    public class Work implements Comparable<Work>{
        int in; // 요청시각
        int require; // 소요시간
        
        public Work (int in, int require) {
            this.in = in;
            this.require = require;
        }
        
        @Override
        public int compareTo(Work o) {
            if (this.require != o.require) return this.require - o.require;
            return this.in - o.in;
        }
    }
}