import java.util.*;

class Solution {
    public int answer = 0;
    
    public int solution(int[] number) {
        int[] tgt = new int[3];
        comb(number, tgt, 0, 0);
        return answer;
    }
    public void comb(int[] src, int[] tgt, int startIdx, int tgtIdx) {
        if (tgtIdx == tgt.length) {
            int sum = 0;
            for (int t: tgt) sum += t;
            if (sum == 0) answer++;
            return;
        }
        for (int i = startIdx; i < src.length; i++) {
            tgt[tgtIdx] = src[i];
            comb(src, tgt, i + 1, tgtIdx + 1);
        }
    }
}