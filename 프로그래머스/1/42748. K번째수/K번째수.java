import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int idx = 0;
        for (int[] c: commands) {
            int[] arr = new int[c[1] - c[0] + 1];
            for (int i = c[0] - 1, j = 0; i < c[1]; i++) {
                arr[j++] = array[i];
            }
            Arrays.sort(arr);
            answer[idx++] = arr[c[2] - 1];
        }
        return answer;
    }
}