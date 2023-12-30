import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
         List<String> list = new ArrayList<>();
        char last = ' ';
        for (String word : words) {
            if (list.isEmpty()) {
                list.add(word);
                last = word.charAt(word.length() - 1);
                continue;
            }
            if (word.charAt(0) == last && !list.contains(word)) {
                list.add(word);
                last = word.charAt(word.length() - 1);
            } else {
                return new int[] {list.size() % n + 1, list.size() / n + 1};
            }

        }
        return new int[] {0, 0};
    }
}