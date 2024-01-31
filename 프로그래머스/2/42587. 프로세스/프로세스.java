import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int cnt = 0;
        
        List<Integer> list = new ArrayList<>();
        for (int p : priorities) list.add(p);

        while (true) {
            if (list.get(0) == Collections.max(list)) {
                list.remove(0);
                cnt++;
                if (location == 0) return cnt;
                location--;
            } 
            else {
                list.add(list.get(0));
                list.remove(0);
                location--;
                if (location == -1) location += list.size();
            }
        }
    }
}