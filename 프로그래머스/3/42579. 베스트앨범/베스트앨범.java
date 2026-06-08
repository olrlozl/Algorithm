import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> g_p = new HashMap<>(); // 장르: 누적횟수
        HashMap<String, List<Song>> g_sl = new HashMap<>(); // 장르: {고유번호, 재생횟수}리스트
        
        for (int i = 0; i < genres.length; i++) {
            g_p.put(genres[i], g_p.getOrDefault(genres[i], 0) + plays[i]);
            g_sl.computeIfAbsent(genres[i], k -> new ArrayList<>()).add(new Song(i, plays[i]));
        } 
        
        ArrayList<String> gList = new ArrayList<>(g_p.keySet()); // 장르 리스트 
        gList.sort((a, b) -> g_p.get(b) - g_p.get(a)); // 누적횟수 기준 내림차순
        
        ArrayList<Integer> answer = new ArrayList<>();
        
        for (String g : gList) {
            List<Song> sList = g_sl.get(g);
            Collections.sort(sList);
            for (int i = 0; i < Math.min(2, sList.size()); i++) {
                answer.add(sList.get(i).idx);
            }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    public class Song implements Comparable<Song>{
        int idx;
        int play;
        
        public Song(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }
        
        @Override
        public int compareTo(Song o) {
            return o.play - this.play;
        }
    }
}