import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);

        int boat = 0;
        int l = 0;
        int r = people.length - 1;

        while (l <= r) {
            if (people[l] + people[r] <= limit) l++;
            boat++;
            r--;
        }

        return boat;
    }
}