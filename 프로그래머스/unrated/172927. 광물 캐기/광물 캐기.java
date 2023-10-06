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
//            System.out.println(Arrays.toString(tgt));
            Mining(minerals, tgt);
            return;
        }
        // 현재 tgtIdx에 같은 종류의 곡괭이가 나오지 않도록 하기 위해,  방문여부 배열을 Permutation함수가 호출 될 때마다 생성
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
        // 가지고 있는 모든 곡괭이를 담은 배열
        int[] src = new int[picks[0] + picks[1] + picks[2]];
        for (int i = 0, idx = 0; i < 3; i++) {
            for (int j = 0; j < picks[i]; j++) {
                src[idx++] = i;
            }
        }
        // 사용할 곡괭이를 담을 배열
        int[] tgt = new int[Math.min(minerals.length / 5 + 1, src.length)];
        // 곡괭이 선택 여부를 담을 배열
        boolean[] select = new boolean[src.length];
        // 순열 함수 호출
        Permutation(minerals, src, tgt, select, 0);

        return minSum;
    }
}