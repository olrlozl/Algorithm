import java.util.*;

class Solution {
    
    public Map<Character, Integer> map = new HashMap<Character, Integer>(){{
        put('A', 0); put('C', 1); put('F', 2); put('J', 3);
        put('M', 4); put('N', 5); put('R', 6); put('T', 7);
    }};

    public int cnt = 0;

    public void permutation(int[] tgt, boolean[] visit, int idx, String[] data) {
        if (idx == 8) {
            boolean flag = true;
            for (String d : data) {
                int a = -1;
                int b = -1;
                for (int i = 0; i < 8; i++) {
                    if (tgt[i] == map.get(d.charAt(0))) a = i;
                    else if (tgt[i] == map.get(d.charAt(2))) b = i;
                }
                if ((d.charAt(3) == '=' && Math.abs(a - b) - 1 != Integer.parseInt(String.valueOf(d.charAt(4))))
                 || (d.charAt(3) == '>' && Math.abs(a - b) - 1 <= Integer.parseInt(String.valueOf(d.charAt(4))))
                 || (d.charAt(3) == '<' && Math.abs(a - b) - 1 >= Integer.parseInt(String.valueOf(d.charAt(4))))) {
                    flag = false;
                    break;
                }
            }
            if (flag) cnt++;
            return;
        }
        for (int i = 0; i < 8; i++) {
            if (!visit[i]) {
                visit[i] = true;
                tgt[idx] = i;
                permutation(tgt, visit, idx + 1, data);
                visit[i] = false;
            }
        }
    }
    
    public int solution(int n, String[] data) {
        permutation(new int[8], new boolean[8], 0, data);
        return cnt;
    }
}