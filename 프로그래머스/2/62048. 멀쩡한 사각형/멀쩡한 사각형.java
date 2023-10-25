class Solution {
    public long gcd (int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    
    public long solution(int w, int h) {
        return (long)w * h - gcd(w, h)  * (w / gcd(w, h) + h / gcd(w, h) - 1);
    }
}