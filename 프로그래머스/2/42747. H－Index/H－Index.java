import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        int max = 0;
        for (int i = 0; i < citations.length; i++) {
            max = Math.max(max, Math.min(citations[i], citations.length - i));
        }
        return max;
    }
}