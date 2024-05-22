class Solution {
    public int solution(int storey) {
        int cnt = 0;

        while (storey > 0) {
            int s = storey % 10;
            
            if (s < 5) {
                cnt += s;
                storey -= s;
            } else if (s > 5) {
                cnt += 10 - s;
                storey += s;
            } else {
                if (storey / 10 % 10 < 5) {
                    cnt += s;
                    storey -= s;
                } else {
                    cnt += 10 - s;
                    storey += s;
                }
            }

            storey /= 10;
        }
         
        return cnt;
    }
}