class Solution {
    public int min = Integer.MAX_VALUE;

    public void elevator(int n, int m, int cnt, int v) {
        if (n == 0) {
                    min = Math.min(min, cnt);
                    return;
                }

                if (m <= v) elevator((int)(n + Math.pow(10, m + 1) - n % (Math.pow(10, m + 1))), m + 1, cnt + (int)((Math.pow(10, m + 1) - n % (Math.pow(10, m + 1))) / Math.pow(10, m)), v);
                elevator((int)(n - n % (Math.pow(10, m + 1))), m + 1, cnt + (int)(n % (Math.pow(10, m + 1)) / Math.pow(10, m)), v);


    }
    public int solution(int storey) {
        elevator(storey, 0, 0, (int)(Math.log10(storey) + 1));
        return min;
    }
}