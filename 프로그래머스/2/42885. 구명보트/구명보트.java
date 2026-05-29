import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        // 가장 무거운 사람은 무조건 보드를 한 번 타야함.
        // 만약 가장 가벼운 사람과 같이 탈 수 있으면 같이 태우고, 못타면 무거운 사람 혼자 태움.
        Arrays.sort(people);
        int answer = 0;
        int l = 0;
        int r = people.length - 1;
        
        while (l <= r) {
            if (people[l] + people[r] <= limit) {
                l++;
            } 
            answer++;
            r--;
        }
        return answer;
    }
}