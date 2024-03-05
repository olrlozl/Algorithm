import java.util.*;

class Solution {
    
    public int solution(String word) {
        char[] src = {'A', 'E', 'I', 'O', 'U'};
        List<String> list = new ArrayList<>();
        
        for (int a = 0; a < 5; a++) {
            list.add(String.valueOf(src[a]));
            for (int b = 0; b < 5; b++) {
                list.add(String.valueOf(src[a]) + String.valueOf(src[b]));
                for (int c = 0; c < 5; c++) {
                    list.add(String.valueOf(String.valueOf(src[a]) + String.valueOf(src[b]) + String.valueOf(src[c])));
                    for (int d = 0; d < 5; d++) {
                        list.add(String.valueOf(String.valueOf(src[a]) + String.valueOf(src[b]) + String.valueOf(src[c]) + String.valueOf(src[d])));
                        for (int e = 0; e < 5; e++) {
                            list.add(String.valueOf(String.valueOf(src[a]) + String.valueOf(src[b]) + String.valueOf(src[c]) + String.valueOf(src[d]) + String.valueOf(src[e])));
                        }
                    }
                }
            }
        }
        
        Collections.sort(list);
        
        return list.indexOf(word) + 1;
    }
}