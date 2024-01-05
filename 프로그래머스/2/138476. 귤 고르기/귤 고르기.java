import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Arrays.sort(tangerine);
        int[] arr = new int[tangerine.length];

        int idx = 0;
        arr[0] = 1;

        for (int i = 1; i < tangerine.length; i++) {
            if (tangerine[i-1] != tangerine[i]) idx++;
            arr[idx] = arr[idx] == 0 ? 1 : arr[idx] + 1;
        }

        Integer[] arr2 = Arrays.stream(arr).boxed().toArray(Integer[]::new);
        Arrays.sort(arr2, Collections.reverseOrder());

        int kind = 0;
        for (int i : arr2) {
            if (k <= 0) break;
            k -= i;
            kind++;
        }

        return kind;
    }
}