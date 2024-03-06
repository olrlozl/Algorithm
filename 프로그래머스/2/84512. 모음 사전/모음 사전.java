import java.util.*;

class Solution {
    public List<String> list = new ArrayList<>();
    
    public void dfs(String word, String str, int len) {
        if (len > 5) return;
        
        list.add(str);
        
        for (int i = 0; i < 5; i ++) {
            dfs(word, str + "AEIOU".charAt(i), len + 1);
        }
    }
    
    public int solution(String word) {
        dfs(word, "", 0);
        return list.indexOf(word);
    }
}