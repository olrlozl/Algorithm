import java.util.*;
import java.awt.*;

class Solution {
    
    public int solution(String dirs) {
        
        HashSet<String> set = new HashSet<>();
        
        HashMap<Character, Point> map = new HashMap<>() {{ 
            put('U', new Point(0, 1));
            put('D', new Point(0, -1));
            put('R', new Point(1, 0));
            put('L', new Point(-1, 0));
        }};
        
        Point now = new Point(0, 0);
        
        for (Character dir : dirs.toCharArray()) {
            int nextX = now.x + map.get(dir).x;
            int nextY = now.y + map.get(dir).y;
            
            if (-5 <= nextX && nextX <= 5 && -5 <= nextY && nextY <= 5) {
                set.add(now.x + " " + now.y + " " + nextX + " " + nextY);
                set.add(nextX + " " + nextY + " " + now.x + " " + now.y);
                now = new Point(nextX, nextY);
            }
        }
        
        return set.size() / 2;
    }
}