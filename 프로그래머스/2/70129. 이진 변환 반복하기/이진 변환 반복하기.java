class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2]; // { 제거할 0의 개수, 이진 변환 횟수 }

        while (!s.equals("1")) {
            answer[1] += s.replace("1", "").length(); // 제거할 0의 개수
            s = Integer.toBinaryString(s.replace("0", "").length()); // 0 제거 후 길이를 2진 변환
            answer[0]++; // 이진 변환 횟수 증가
        }

        return answer;
    }
}