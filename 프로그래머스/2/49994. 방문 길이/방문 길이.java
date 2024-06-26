import java.util.*;
import java.awt.*;

class Solution {
    public int solution(String dirs) {
        HashSet<String> set = new HashSet<>();
        
        int x = 0;
        int y = 0;
        
        Map<Character, Point> delta = new HashMap<>() {{
            put('U', new Point(0, 1));
            put('D', new Point(0, -1));
            put('R', new Point(1, 0));
            put('L', new Point(-1, 0));
        }};
        
        for (char dir : dirs.toCharArray()) {
            int nx = x + delta.get(dir).x;
            int ny = y + delta.get(dir).y;
            
            if (-5 <= nx && nx <= 5 && -5 <= ny && ny <= 5) {
                set.add(x + "" + y + "" + nx + "" + ny);
                set.add(nx + "" + ny + "" + x + "" + y);
                x = nx;
                y = ny;
            }
        }
        
        return set.size() / 2;
    }
}