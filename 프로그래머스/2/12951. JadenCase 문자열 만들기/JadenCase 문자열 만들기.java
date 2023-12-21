class Solution {
    public String solution(String s) {
        String result = "";
        boolean flag = true;
        
        for (String w : s.toLowerCase().split("")) {
            result += flag ? w.toUpperCase() : w;
            flag = w.equals(" ");
        }
        
        return result;
    }
}