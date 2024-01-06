class Solution {
    public int[] solution(int n, long left, long right) {
        int[] result = new int[(int)(right - left + 1)];
        for (long i = left, idx = 0; i <= right; i++) {
            result[(int) idx++] = (int) Math.max(i/n+1, i%n+1);
        }
        return result;
    }
}