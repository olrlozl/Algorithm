import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(String s) {
        int[] arr = Stream.of(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);
        return arr[0] + " " + arr[arr.length - 1];
    }
}