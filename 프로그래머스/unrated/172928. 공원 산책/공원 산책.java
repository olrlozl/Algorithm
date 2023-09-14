class Solution {
    public int[] solution(String[] park, String[] routes) {
        
        int r = 0;
        int c = 0;
        
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length(); j++) {
                if (park[i].charAt(j) == 'S') {
                    r = i;
                    c = j;
                    break;
                }
            }
        }
        
        loop: for (String route : routes) {
            char op = route.split(" ")[0].charAt(0);
            int n = Integer.parseInt(route.split(" ")[1]);
            switch (op) {
                case 'N':
                    if (0 <= r - n) {
                        for (int i = 1; i <= n; i++){
                            if (park[r-i].charAt(c) == 'X') {
                                continue loop;
                            }
                        }
                        r -= n;
                    }
                    break;
                case 'S':
                    if (r + n < park.length) {
                        for (int i = 1; i <= n; i++){
                            if (park[r+i].charAt(c) == 'X') {
                                continue loop;
                            }
                        }
                        r += n;
                    }
                    break;
                case 'W':
                    if (0 <= c - n) {
                        for (int i = 1; i <= n; i++){
                            if (park[r].charAt(c-i) == 'X') {
                                continue loop;
                            }
                        }
                        c -= n;
                    }
                    break;
                case 'E':
                    if (c + n < park[0].length()) {
                        for (int i = 1; i <= n; i++){
                            if (park[r].charAt(c+i) == 'X') {
                                continue loop;
                            }
                        }
                        c += n;
                    }
                    break;
            }
        }

        int[] result = {r, c};
        return result;
    }
}