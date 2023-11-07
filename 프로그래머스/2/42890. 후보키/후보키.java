import java.util.*;

class Solution {
    public ArrayList<boolean[]> candidateKeys = new ArrayList<>();
    public int result = 0;

    public void combination(boolean[] tgt, int idx, int cnt, int r, String[][] relation) {
        if (cnt == r) {
            // 최소성 (cKey가 tgt의 부분집합이면 안됨)
            loop: for (boolean[] cKey : candidateKeys) {
                for (int i = 0; i < cKey.length; i++) {
                    if (!cKey[i]) continue;
                    if (cKey[i] && !tgt[i]) continue loop;
                }
                return;
            }

            // 유일성
            ArrayList<String[]> unique = new ArrayList<>();

            for (int row = 0; row < relation.length; row++) {
                String[] cur = new String[r]; // 속성 쌍
                int i = 0;

                for (int j = 0; j < relation[0].length; j++) {
                    if (tgt[j]) cur[i++] = relation[row][j];
                }

                for (int u = 0; u < unique.size(); u++) {
                    if (Arrays.equals(cur, unique.get(u))) return;
                }
                unique.add(cur);
            }

            // 최소성과 유일성 모두 만족하는 경우
            boolean[] tmp = new boolean[tgt.length];
            for(int i = 0; i < tmp.length; i++) tmp[i] = tgt[i];
            candidateKeys.add(tmp);

            result++;
            return;
        }
        if (idx == tgt.length) return;

        tgt[idx] = true;
        combination(tgt, idx + 1, cnt + 1, r, relation);
        tgt[idx] = false;
        combination(tgt, idx + 1, cnt, r, relation);
    }
    
    public int solution(String[][] relation) {
        int col = relation[0].length;
        for (int r = 1; r <= col; r++) {
            combination(new boolean[col],0, 0, r, relation);
        }
        return result;
    }
}