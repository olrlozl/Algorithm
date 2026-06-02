class Solution {
    public int solution(String name) {
        int answer = name.length() - 1;
        int cnt = 0;
        
        for (int i = 0; i < name.length(); i++) {
            cnt += Math.min(name.charAt(i) - 'A', 26 - name.charAt(i) + 'A');
            int idx = i + 1;
            while (idx < name.length() && name.charAt(idx) == 'A') idx++;
            answer = Math.min(answer, i * 2 + name.length() - idx);
            answer = Math.min(answer, (name.length() - idx) * 2 + i);
        }
        return answer + cnt;
    }
}