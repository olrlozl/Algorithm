import java.util.*;

class Solution {
    public int cnt = 0;
    public HashSet<Integer> set = new HashSet<>();
    
    public int solution(String numbers) {
        char[] src = numbers.toCharArray();
        
        for (int i = 1; i <= numbers.length(); i++) {
            char[] tgt = new char[i];
            boolean[] visit = new boolean[numbers.length()];
            perm(src, tgt, visit, 0);
        }
        
        for (int n : set) {
            if (isPrime(n)) cnt++;
        }
            
        return cnt;
    }
    
    public void perm(char[] src, char[] tgt, boolean[] visit, int tgtIdx) {
        if (tgtIdx == tgt.length) {
            StringBuilder sb = new StringBuilder();
            for (char c : tgt) sb.append(c);
            set.add(Integer.parseInt(sb.toString()));
            return;
        }
        
        boolean[] select = new boolean[10];
        
        for (int i = 0; i < src.length; i++) {
            if (!visit[i] && !select[src[i] - '0']) {
                tgt[tgtIdx] = src[i];
                visit[i] = true;
                select[src[i] - '0'] = true;
                perm(src, tgt, visit, tgtIdx + 1);
                visit[i] = false;
            }
        }
    }
    
    public boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}