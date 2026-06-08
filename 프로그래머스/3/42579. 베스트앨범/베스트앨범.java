import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap<>(); // 장르: 누적횟수
        
        for (int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        } 
        
        ArrayList<String> gList = new ArrayList<>(map.keySet()); // 장르 리스트 
        gList.sort((a, b) -> map.get(b) - map.get(a)); // 누적횟수 기준 내림차순
        
        HashMap<String, HashMap<Integer, Integer>> map2 = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            if (!map2.containsKey(genres[i])) {
                HashMap<Integer, Integer> song = new HashMap<>();
                song.put(i, plays[i]);
                map2.put(genres[i], song);
            } else {
                map2.get(genres[i]).put(i, plays[i]);
            }
        }
        
        ArrayList<Integer> answer = new ArrayList<>();
        
        for (String g : gList) {
            HashMap<Integer, Integer> song = map2.get(g);
            ArrayList<Integer> songIdxList = new ArrayList<>(song.keySet());
            songIdxList.sort((a, b) -> song.get(b) - song.get(a));
            for (int i = 0; i < Math.min(2, song.size()); i++) {
                answer.add(songIdxList.get(i));
            }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}