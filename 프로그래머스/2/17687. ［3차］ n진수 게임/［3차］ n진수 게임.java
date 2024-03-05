class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        int num = 0;
        
        while (answer.length() < t * m) {
            answer += Integer.toString(num++, n).toUpperCase();    
        }
        
        String tube = "";
        for (int i = 0; i < t; i++) {
            tube += answer.charAt(i * m + p - 1);
        }
        
        return tube;
    }
}
