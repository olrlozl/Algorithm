import java.util.*;

class Solution {
    public static int max = 0;
    
    public void permutation(int k, int[][] dungeons, int[] tgt, boolean[] select, int tgtIdx) {
        if (tgtIdx == tgt.length) {
            int cnt = 0;
            for (int t : tgt) {
                if (k < dungeons[t][0]) break;
                cnt++;
                k -= dungeons[t][1];
            }
            max = Math.max(max, cnt);
            return;
        }
        for (int i = 0; i < dungeons.length; i++) {
            if (!select[i]) {
                tgt[tgtIdx] = i;
                
                select[i] = true;
                permutation(k, dungeons, tgt, select, tgtIdx + 1);
                select[i] = false;
            }
        }
    }
    
    public int solution(int k, int[][] dungeons) {
        
        int[] tgt = new int[dungeons.length];
        boolean[] select = new boolean[dungeons.length];
        
        permutation(k, dungeons, tgt, select, 0);
        
        return max;
    }
}