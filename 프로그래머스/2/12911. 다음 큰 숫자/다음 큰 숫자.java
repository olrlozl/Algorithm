class Solution {
    public int solution(int n) {
        int one = Integer.toBinaryString(n).replace("0", "").length();

        for (int i = n + 1; ; i++) {
            if (one == Integer.toBinaryString(i).replace("0", "").length()) {
                return i;
            }
        }
    }
}