import java.util.*;

public class Solution {
    public int solution(int n) {
        return Integer.toBinaryString(n).replace("0", "").length(); // 이진수의 1의 개수
    }
}