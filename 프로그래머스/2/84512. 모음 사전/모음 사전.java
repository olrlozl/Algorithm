import java.util.*;

class Solution {
    ArrayList<String> list = new ArrayList<>();
    
    public int solution(String word) {
        func(word, "", 0);
        Collections.sort(list);
        return list.indexOf(word);
    }
    
    public void func(String word, String cur, int len) {
        if (len > 5) return;
        list.add(cur);
        for (int i = 0; i < 5; i++) {
            func(word, cur + "AEIOU".charAt(i), len + 1);
        }
    }
}