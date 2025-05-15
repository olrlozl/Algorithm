import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        Queue<String> queue = new LinkedList<>();
        boolean[] visit = new boolean[words.length];
        
        int cnt = -1;
        queue.add(begin);
        
        while (!queue.isEmpty()) {
            cnt++;
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                String now = queue.poll();
                
                if (now.equals(target)) {
                    return cnt;
                }

                for (int j = 0; j < words.length; j++) {
                    if (visit[j]) continue;
                    
                    int diff = 0;
                    
                    for (int k = 0; k < begin.length(); k++) {
                        if (now.charAt(k) != words[j].charAt(k)) {
                            diff++;
                        }
                        if (diff > 1) {
                            break;
                        }
                    }

                    if (diff == 1) {
                        queue.add(words[j]);
                        visit[j] = true;
                    }
                }
            }
        }
        return 0;
    }
}