import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int cnt = 0;
        
        HashSet<Integer> set = new HashSet<>(); // 앞부분
        HashMap<Integer, Integer> map = new HashMap<>(); // 뒷부분
        
        for (int i = 0; i < topping.length; i++) {
            map.put(topping[i], map.getOrDefault(topping[i], 0) + 1);
        }
        
        for (int i = 0; i < topping.length - 1; i++) {
            set.add(topping[i]);
            
            if (map.getOrDefault(topping[i], 0) < 2) map.remove(topping[i]);
            else map.put(topping[i], map.get(topping[i]) - 1);
            
            if (set.size() == map.size()) cnt++;
        }
        
        return cnt;
    }
}