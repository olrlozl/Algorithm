class Solution {
    public int[] solution(int brown, int yellow) {
        int[] result = new int[2];
        for (int i = 1; i <= yellow; i++) {
            if (yellow % i == 0 && brown == (i+2) * (yellow/i+2) - yellow) {
                result[0] = yellow/i + 2;
                result[1] = i + 2;
                break;
            }
        }
        return result;
    }
}

// yellow = x * y
// brown = (x+2) (y+2) - yellow