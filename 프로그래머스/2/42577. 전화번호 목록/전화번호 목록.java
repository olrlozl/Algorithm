import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashSet<String> set = new HashSet<>();
        for (String s : phone_book) set.add(s);
        
        for (int i = 0; i < phone_book.length; i++) {
            for (int j = 1; j < phone_book[i].length(); j++) {
                if (set.contains(phone_book[i].substring(0, j))) {
                    return false;
                }
            }
        }
        return true;
    }
}