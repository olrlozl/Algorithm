class Solution {
    public String solution(int n, int t, int m, int p) {
        
        StringBuilder answer = new StringBuilder();
        StringBuilder tube = new StringBuilder();
        int num = 0;
        
        while (answer.length() < t * m) {
            answer.append(Integer.toString(num++, n).toUpperCase());
        }

        for (int i = 0; i < t; i++) {
            tube.append(answer.charAt(i * m + p - 1));
        }
        
        return tube.toString();
    }
}