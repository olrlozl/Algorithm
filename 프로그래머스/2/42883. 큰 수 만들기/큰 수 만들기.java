import java.util.*;

class Solution {
    public String solution(String number, int k) {
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < number.length(); i++) {
            list.add(number.charAt(i) - '0');
        }
        
        int idx = 0;
        
        while (k > 0) {
            if (idx == list.size() - 1) {
                list.remove(idx);
                k--;
                idx--;
            } else if (list.get(idx) < list.get(idx + 1)) {
                list.remove(idx);
                k--;
                if (idx > 0) idx--;
            } else {
                idx++;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int n : list) sb.append(n);
        
        return sb.toString();
    }
}