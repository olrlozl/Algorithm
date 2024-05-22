class Solution {
    public int solution(int storey) {
        int cnt = 0;

        while (storey > 0) {
            int s = storey % 10;

            if (s < 5) {
                cnt += s;
            } else if (s > 5) {
                cnt += 10 - s;
                storey += 10;
            } else {
                if (storey / 10 % 10 > 4) {
                    storey += 10;
                }
                cnt += s;
            }
            
            storey /= 10;
        }
         
        return cnt;
    }
}