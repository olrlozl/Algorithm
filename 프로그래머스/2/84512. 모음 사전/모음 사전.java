import java.util.*;

class Solution {
    public List<String> list = new ArrayList<>();
    public boolean isFind = false;
    public int cnt = 0;
    
    public void dfs(String word, String str, int len) {
        if (isFind) return;
        if (len > 5) return;
        
        list.add(str);
        if (str.equals(word)) isFind = true;
        
        for (int i = 0; i < 5; i ++) {
            dfs(word, str + "AEIOU".charAt(i), len + 1);
        }
    }
    
    public int solution(String word) {
        dfs(word, "", 0);
        return list.indexOf(word);
    }
}