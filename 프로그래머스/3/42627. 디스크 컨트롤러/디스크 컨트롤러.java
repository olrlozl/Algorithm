import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int count = 0; // 완료된 작업 수
        int jobIdx = 0; // 작업 번호 (요청시각 빠른 순)
        int end = 0; // 작업 완료 시각
        
        
        // 요청시각 오름차순
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        // 소요시간 오름차순
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        while (count < jobs.length) {
            while (jobIdx < jobs.length && jobs[jobIdx][0] <= end) {
                pq.add(jobs[jobIdx++]);
            }
            
            if (pq.isEmpty()) {
                end = jobs[jobIdx][0];
            } else {
                int[] job = pq.poll();
                end += job[1];
                answer += end - job[0];
                count++;
            }
        }
        
        return answer / jobs.length;
    }
}