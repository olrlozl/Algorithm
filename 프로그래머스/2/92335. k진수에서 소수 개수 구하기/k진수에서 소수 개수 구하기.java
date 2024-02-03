import java.util.*;

class Solution {
    public boolean isPrime(Long n) {
        if (n == 1) return false;
        for (int i = 2; i <= (int)Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    public int solution(int n, int k) {
        int cnt = 0;
        for (String num : Integer.toString(n, k).split("0")) {
            if (num.isEmpty()) continue;
            if (isPrime(Long.parseLong(num))) cnt++;
        }
        return cnt;
    }
}