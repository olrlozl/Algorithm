class Solution {
    public int gcd(int a, int b) {
        while (b > 0) {
            int tmp = a;
            a = b;
            b = tmp % b;
        }
        return a;
    }

    public int solution(int[] arr) {
        // a와 b의 최소공배수 = a * b / a와 b의 최대공약수
        int lcm = arr[0];
        for (int i = 1; i < arr.length; i++) {
            lcm = lcm * arr[i] / gcd(lcm, arr[i]);
        }
        return lcm;
    }

}