import java.util.*;

class Solution {
    public int[] solution(String msg) {
        
        List<String> dic = new ArrayList<>();
        dic.add("");
        for (int i = 0; i < 26; i++) dic.add(String.valueOf((char)('A' + i))); 
        
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder(msg);
        int start = 0;
        int end = 1;

        while (end <= msg.length()) {
            if (dic.contains(sb.substring(start, end))) {
                end++;
            } else {
                list.add(dic.indexOf(sb.substring(start, end - 1))); // 출력
                dic.add(sb.substring(start,end)); // 사전 추가
                start = end - 1;
            }
        }
        list.add(dic.indexOf(sb.substring(start, end - 1))); // 출력
        
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) result[i] = list.get(i);
        
        return result;
    }
}