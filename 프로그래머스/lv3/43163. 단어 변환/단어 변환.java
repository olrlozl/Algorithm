import java.util.*;

class Solution {
    
    public static class Word {
        int cnt;
        String word;

        public Word (int idx, String word) {
            this.cnt = idx;
            this.word = word;
        }
    }

    public static int bfs (String begin, String target, String[] words) {
        Queue<Word> queue = new LinkedList<>();
        boolean[] visit = new boolean[words.length];

        queue.add(new Word(0, begin));

        while (!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                Word now = queue.poll();
                if (now.word.equals(target)) {
                    return now.cnt;
                }

                for (int j = 0; j < words.length; j++) {
                    if (visit[j]) continue;

                    int dif = 0;
                    for (int k = 0; k < now.word.length(); k++) {
                        if (now.word.charAt(k) != words[j].charAt(k)) {
                            dif++;
                        }
                    }
                    if (dif == 1) {
                        queue.add(new Word(now.cnt + 1, words[j]));
                        visit[j] = true;
                    }
                }
            }
        }
        return 0;
    }
    
    public int solution(String begin, String target, String[] words) {
        return bfs(begin, target, words);
    }
}