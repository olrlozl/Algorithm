import java.util.*;

class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> set = new HashSet<>();
        int N = elements.length;
        
        for (int i = 1; i <= N; i++) { // 길이
            for (int j = 0; j < N; j++) { // 시작 인덱스
                int sum = 0;
                for (int k = 0; k < i; k++) {
                    sum += elements[(j + k) % N];
                }
                set.add(sum);
            }
        }
        return set.size();
    }
}