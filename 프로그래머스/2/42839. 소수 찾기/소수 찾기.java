import java.util.*;
import java.util.stream.*;

class Solution {
    public HashSet<Integer> set = new HashSet<>();
    
    public boolean check(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) if (n % i == 0) return false;
        return true;
    }
    
    public void perm(String[] src, String[] tgt, boolean[] select, int tgtIdx) {
        if (tgt.length == tgtIdx) {
            String num = "";
            for (int i = 0; i < tgtIdx; i++) num += tgt[i];
            if (check(Integer.parseInt(num))) set.add(Integer.parseInt(num));
            return;
        }
        
        boolean[] visit = new boolean[10];
        for (int i = 0; i < src.length; i++) {
            if (!select[i] && !visit[Integer.parseInt(src[i])]) {
                tgt[tgtIdx] = src[i];
                visit[Integer.parseInt(src[i])] = true;
                select[i] = true;
                perm(src, tgt, select, tgtIdx + 1);
                select[i] = false;
            }
        }
    }
    
    public int solution(String numbers) {
        String[] src = numbers.split("");
        
        for (int i = 1; i <= src.length; i++) {
            String[] tgt = new String[i];
            boolean[] select = new boolean[src.length];
            perm(src, tgt, select, 0);
        }
        return set.size();
    }
}