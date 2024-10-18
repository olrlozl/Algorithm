import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int i = 0;
        for (int[] cmd : commands) {
            int[] arr = new int[cmd[1] - cmd[0] + 1];
            for (int j = cmd[0] - 1, idx = 0; j < cmd[1]; j++) {
                arr[idx++] = array[j];
            }
            Arrays.sort(arr);
            answer[i++] = arr[cmd[2] - 1];
        }
        
        return answer;
    }
}