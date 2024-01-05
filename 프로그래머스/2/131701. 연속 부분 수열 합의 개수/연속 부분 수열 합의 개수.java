import java.util.*;

class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> set = new HashSet<>();

        int[] arr = new int[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            arr[i] = elements[i];
            arr[i + elements.length] = elements[i];
        }

        for (int len = 1; len <= elements.length; len++) {
            for (int start = 0; start < elements.length; start++) {
                int sum = 0;
                for (int i = start; i < start + len; i++) {
                    sum += arr[i];
                }
                set.add(sum);
            }
        }
        return set.size();
    }
}