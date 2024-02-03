import java.util.*;

class Solution {
    public void addElement(List<String> list, String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if ('a' <= s.charAt(i) && s.charAt(i) <= 'z' 
                && 'a' <= s.charAt(i + 1) && s.charAt(i + 1) <= 'z') {
                list.add(s.substring(i, i + 2));
            }
        }
    }
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        
        addElement(list1, str1);
        addElement(list2, str2);
        
        List<String> tmp = new ArrayList<>();
        
        int intersection = 0;
        for (int i = 0; i < list2.size(); i++) {
            if (list1.contains(list2.get(i))) {
                intersection++;
                list1.remove(list2.get(i));
                tmp.add(list2.get(i));
            }
        }
        for (int i = 0; i < tmp.size(); i++) list1.add(tmp.get(i));
        
        int union = list2.size();
        for (int i = 0; i < list2.size(); i++) {
            if (list1.contains(list2.get(i))) {
                list1.remove(list2.get(i));
            }
        }
        union += list1.size();
        
        if (intersection == 0 && union == 0) return 65536;
        return (int)((float)intersection / (float)union * 65536);
    }
}