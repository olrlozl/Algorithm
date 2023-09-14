import java.awt.*;
import java.util.*;

class Solution {
    public int[] solution(String[] park, String[] routes) {
        
        Map<Character, Point> delta = new HashMap<Character, Point>(){{
            put('N', new Point(-1,0));
            put('S', new Point(1,0));
            put('W', new Point(0,-1));
            put('E', new Point(0,1));
        }};
        
        // 시작 좌표 [r,c]
        int r = 0;
        int c = 0;

        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length(); j++) {
                if (park[i].charAt(j) == 'S') {
                    r = i;
                    c = j;
                    break;
                }
            }
        }

        // 힌 칸씩 이동해보면서, 조건을 만족하면, 해당 명령 수행
        int nr = 0;
        int nc = 0;

        loop: for (String route : routes) {
            char op = route.split(" ")[0].charAt(0);
            int n = Integer.parseInt(route.split(" ")[1]);

            for (int i = 1; i <= n; i++) {
                nr = r + i * delta.get(op).x;
                nc = c + i * delta.get(op).y;
                // 범위를 벗어나거나, 장애물을 만나면, 해당 명령 무시
                if (0 > nr || nr >= park.length || 0 > nc || nc >= park[0].length() || park[nr].charAt(nc) == 'X') {
                    continue loop;
                }
            }
            r = nr;
            c = nc;
        }

        int[] result = {r, c};
        return result;
    }
}