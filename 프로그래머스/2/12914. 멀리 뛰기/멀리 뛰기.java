class Solution {
    public long solution(int n) {
        if (n <= 2) return n;
        long[] fibo = new long[n];
        fibo[0] = 1;
        fibo[1] = 2;
        for (int i = 2; i < n; i++) fibo[i] = (fibo[i-2] + fibo[i-1]) % 1234567;
        return fibo[n-1];
    }
}