import java.util.*;

class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> set = new HashSet<>();

        for (int len = 1; len <= elements.length; len++) {
            for (int i = 0; i < elements.length; i++) {
                int sum = 0;
                for (int j = i; j < i + len; j++) {
                    sum += elements[j % elements.length];
                }
                set.add(sum);
            }
        }
        return set.size();
    }
}