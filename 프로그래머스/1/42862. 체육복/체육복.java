import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n;
        
        int[] arr = new int[n];
        Arrays.fill(arr, 1);
        for (int r : reserve) arr[r - 1]++;
        for (int l : lost) {
            if (--arr[l - 1] == 0) answer--;
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                if (0 <= i - 1 && arr[i - 1] == 2) {
                    arr[i - 1]--;
                    arr[i]++;
                    answer++;
                } else if (i + 1 < arr.length && arr[i + 1] == 2) {
                    arr[i + 1]--;
                    arr[i]++;
                    answer++;
                }
            }
        }
        return answer;
    }
}