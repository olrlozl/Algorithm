import java.util.*;

class Solution {
    public int[][] give_take;
    public int[] point;
    public int[] next;
    
    public void comb(int srcLen, int[] tgt, int srcIdx, int tgtIdx) {
        if (tgt.length == tgtIdx) {
            if (give_take[tgt[0]][tgt[1]] > give_take[tgt[1]][tgt[0]]) next[tgt[0]]++;
            else if (give_take[tgt[0]][tgt[1]] < give_take[tgt[1]][tgt[0]]) next[tgt[1]]++;
            else {
                if (point[tgt[0]] > point[tgt[1]]) next[tgt[0]]++;
                else if (point[tgt[0]] < point[tgt[1]]) next[tgt[1]]++;
            }
            return;
        }
        
        if (srcIdx == srcLen) return;
        
        tgt[tgtIdx] = srcIdx;
        comb(srcLen, tgt, srcIdx + 1, tgtIdx + 1);
        comb(srcLen, tgt, srcIdx + 1, tgtIdx);
    }
    
    public int solution(String[] friends, String[] gifts) {

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < friends.length; i++) map.put(friends[i], i);
        
        give_take = new int[friends.length][friends.length];
        point = new int[friends.length];
        
        for (String gift : gifts) {
            give_take[map.get(gift.split(" ")[0])][map.get(gift.split(" ")[1])]++;
            point[map.get(gift.split(" ")[0])]++;
            point[map.get(gift.split(" ")[1])]--;
        }

        next = new int[friends.length];
        comb(friends.length, new int[2], 0, 0);
   
        int max = 0;
        for (int i = 0; i < friends.length; i++) max = Math.max(max, next[i]);
        
        return max;
    }
}