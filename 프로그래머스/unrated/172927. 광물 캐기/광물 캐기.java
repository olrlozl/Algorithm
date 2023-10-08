import java.io.*;
import java.util.*;

class Solution {
    public static int[][] tired = {{1,1,1}, {5,1,1}, {25,5,1}};
    public static int minSum = Integer.MAX_VALUE;
    
    public static Map<String, Integer> map = new HashMap<String, Integer>(){{
        put("diamond", 0); put("iron", 1); put("stone", 2);
    }};
    
    public static void Mining (String[] minerals, int[] tgt) {
        int sum = 0;
        for (int i = 0; i < minerals.length; i++) {
            if (i >= tgt.length * 5) break;
            sum += tired[tgt[i / 5]][map.get(minerals[i])];
        }
        minSum = Math.min(minSum, sum);
    }
    
    public static void Permutation(String[] minerals, int[] src, int[] tgt, boolean[] select, int tgtIdx) {
        if (tgtIdx == tgt.length) {
            Mining(minerals, tgt);
            return;
        }
        boolean[] visit = new boolean[3];
        for (int i = 0; i < src.length; i++) {
            if (!select[i] && ! visit[src[i]]) {
                tgt[tgtIdx] = src[i];
                visit[src[i]] = true;
                select[i] = true;
                Permutation(minerals, src, tgt, select, tgtIdx + 1);
                select[i] = false;
            }
        }
   }
    
    public int solution(int[] picks, String[] minerals) {
        int[] src = new int[picks[0] + picks[1] + picks[2]];
        for (int i = 0, idx = 0; i < 3; i++) {
            for (int j = 0; j < picks[i]; j++) {
                src[idx++] = i;
            }
        }
        int[] tgt = new int[Math.min(minerals.length / 5 + 1, src.length)];
        boolean[] select = new boolean[src.length];
        Permutation(minerals, src, tgt, select, 0);

        return minSum;
    }
}