class Solution {
    public int solution(int n) {
        int a = 0;
        int b = 1;
        for (int i = 1; i < n; i++) {
            int tmp = a;
            a = b;
            b = (tmp + a) % 1234567;
        }
        return b;
    }
}