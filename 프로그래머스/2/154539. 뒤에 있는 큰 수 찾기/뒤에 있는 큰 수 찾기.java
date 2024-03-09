import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] result = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = numbers.length - 1; i >= 0; i--) {
            while(!stack.isEmpty()) {
                if (numbers[i] < stack.peek()) {
                    result[i] = stack.peek();
                    break;
                } else {
                    stack.pop();
                }
            }
            if (stack.isEmpty()) {
                result[i] = -1;
            }
            stack.add(numbers[i]);
        }
        
        return result;
    }
}