class Solution {
    public long solution(int n) {
        if (n <= 2) return n;
        int a = 1;
        int b = 2;
        for (int i = 0; i < n - 2; i++) {
            int tmp = a;
            a = b;
            b = (tmp + a) % 1234567;
        }
        return b;
    }
}