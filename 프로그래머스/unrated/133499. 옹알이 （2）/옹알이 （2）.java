class Solution {
    public int solution(String[] babbling) {
        String[] words =  {"aya", "ye", "woo", "ma"};

        int result = 0;

        for (String b : babbling) {
            for (String w : words) {
                if (!b.contains(w + w)) b = b.replace(w," ");
            }
            b = b.trim();
            if (b.isEmpty()) {
                System.out.println(b);
                result++;
            }
        }
        
        return result;
    }
}