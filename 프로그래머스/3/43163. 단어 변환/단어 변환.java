import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        Queue<String> queue = new LinkedList<>();
        boolean[] visit = new boolean[words.length];
        queue.add(begin);
        int cnt = 0;
        
        loop: while(!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                String word = queue.poll();
                if (word.equals(target)) {
                    return cnt;
                }
                for (int j = 0; j < words.length; j++) {
                    if (visit[j]) continue;
                    int dif = 0;
                    for (int k = 0; k < begin.length(); k++) {
                        if (word.charAt(k) != words[j].charAt(k)) {
                            dif++;
                            if (dif > 1) break;
                        }
                    }
                    if (dif == 1) {
                        queue.add(words[j]);
                        visit[j] = true;
                    }
                }
            }
            cnt++;
        }
        return 0;
    }
}