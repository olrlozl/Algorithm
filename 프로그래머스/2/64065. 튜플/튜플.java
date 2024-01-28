import java.util.*;

class Solution {
    public int[] solution(String s) {
        String[] arr = s.replace('{' , ' ').replace('}', ' ').trim().split(" , ");
        Arrays.sort(arr, (s1, s2) -> s1.length() - s2.length());
        
        Set<String> set = new HashSet<>();
        int[] answer = new int[arr.length];
        int idx = 0;
        
        for (String a : arr) {
            for (String b : a.split(",")) {
                if (set.add(b)) answer[idx++] = Integer.parseInt(b);
            }
        }

        return answer;
    }
}