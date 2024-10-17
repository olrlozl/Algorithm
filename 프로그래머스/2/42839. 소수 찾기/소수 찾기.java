import java.util.*;

class Solution {
    public HashSet<Integer> set = new HashSet<>();
    
    public int solution(String numbers) {
        char[] src = numbers.toCharArray();
        
        for (int i = 1; i <= src.length; i++) {
            boolean[] visit = new boolean[src.length];
            char[] tgt = new char[i];
            perm(src, visit, tgt, 0);
        }
        
        return set.size();
    }
    
    // 중복 순열
    public void perm(char[] src, boolean[] visit, char[] tgt, int tgtIdx) {
        if (tgtIdx == tgt.length) {
            int num = 0;
            for (char c : tgt) num += (c - '0') * Math.pow(10, --tgtIdx);
            if (isPrime(num)) set.add(num);
            return;
        }
        
        boolean[] select = new boolean[10];
        for (int i = 0; i < src.length; i++) {
            if (!visit[i] && !select[src[i] - '0']) {
                tgt[tgtIdx] = src[i];
                select[src[i] - '0'] = true;
                visit[i] = true;
                perm(src, visit, tgt, tgtIdx + 1);
                visit[i] = false;
            }
        }
    }
    
    // 소수 판별
    public boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= (int)Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}