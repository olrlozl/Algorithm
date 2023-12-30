import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        List<String> list = new ArrayList<>();
        for (String word : words) {
            if (list.isEmpty()) {
                list.add(word);
                continue;
            }
            if (word.charAt(0) == list.get(list.size() - 1).charAt(list.get(list.size() - 1).length() - 1) && !list.contains(word)) {
                list.add(word);
            } else {
                return new int[] {list.size() % n + 1, list.size() / n + 1};
            }

        }
        return new int[] {0, 0};
    }
}