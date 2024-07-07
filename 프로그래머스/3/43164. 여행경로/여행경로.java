import java.util.*;

class Solution {
    public ArrayList<String> list = new ArrayList<>();
    public boolean[] visit;
    
    public String[] solution(String[][] tickets) {
        visit = new boolean[tickets.length];
        dfs(tickets, "ICN", "ICN", 0);
        Collections.sort(list);
        
        String[] answer = new String[tickets.length + 1];
        answer = list.get(0).split(" ");
        return answer;
    }
    
    public void dfs(String[][] tickets, String from, String path, int cnt) {
        if (cnt == tickets.length) {
            list.add(path);
            return;
        }
        for (int i = 0; i < tickets.length; i++) {
            if (!visit[i] && tickets[i][0].equals(from)) {
                visit[i] = true;
                dfs(tickets, tickets[i][1], path + " " + tickets[i][1], cnt + 1);
                visit[i] = false;
            }
        }
    }
}