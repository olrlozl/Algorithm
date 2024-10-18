import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if (number == N) return 1; //
        
        // N을 i번 사용해서 만들 수 있는 수 (i: 1~8)
        ArrayList<HashSet<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= 8; i++) list.add(new HashSet<>());
        
        list.get(1).add(N); // N을 1번 사용해서 만들 수 있는 수는 N뿐
        
        
        for (int i = 2; i <= 8; i++) {
            HashSet<Integer> set = list.get(i);
            
            for (int j = 1; j < i; j++) {
                HashSet<Integer> preSet = list.get(j);
                HashSet<Integer> postSet = list.get(i - j);
                
                for (int preNum : preSet) {
                    for (int postNum : postSet) {
                        set.add(preNum + postNum);
                        set.add(preNum - postNum);
                        set.add(preNum * postNum);
                        if (postNum != 0) set.add(preNum / postNum);
                    }
                }
            }
            set.add(Integer.parseInt(String.valueOf(N).repeat(i)));
            if (set.contains(number)) return i;
        }
        return -1;
    }
}