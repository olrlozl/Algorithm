import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return cities.length * 5;
        
        int time = 0;
        List<String> list = new LinkedList<>();
        
        for (String city : cities) {
            city = city.toLowerCase();
            
            if (!list.contains(city)) {
                if (list.size() == cacheSize) list.remove(0);
                time += 5;
            } else {
                list.remove(list.indexOf(city));
                time += 1;
            }
            list.add(city);
            
        }
        return time;
    }
}