import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] days = new int[progresses.length];
        
        for (int i = 0; i < progresses.length; i++) {
            days[i] = (100 - progresses[i]) / speeds[i];
            if ((100 - progresses[i]) % speeds[i] > 0) days[i]++; 
        }
        
        List<Integer> list = new ArrayList<>();
        
        int flag = days[0];
        int num = 1;
        for (int i = 1; i < days.length; i++) {
            if (flag >= days[i]) num++;
            else {
                list.add(num);
                flag = days[i];
                num = 1;
            }
        }
        list.add(num);
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);
        
        return answer;
    }
}