import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int width = 2 * w + 1;
        int start = 1;
        int end = 1;
        int len = 0;
        for (int s : stations) {   
            end = s - w - 1;
            len = end - start + 1;
            if (len > 0) answer += (len - 1) / width + 1;
            start = s + w + 1;
        }
        end = n;
        len = end - start + 1;
        if (len > 0) answer += (len - 1) / width + 1;
        

        return answer;
    }
}