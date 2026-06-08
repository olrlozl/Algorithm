import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap<>(); // 장르: 누적횟수
        
        for (int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        } 
        // System.out.println(map);
        
        ArrayList<String> gList = new ArrayList<>(map.keySet()); // 장르 리스트 
        gList.sort((a, b) -> map.get(b) - map.get(a)); // 누적횟수 기준 내림차순
        // System.out.println(gList);
        
        ArrayList<Song>[] songs = new ArrayList[gList.size()]; // 각 장르별 {고유번호, 재생횟수} 리스트
        for (int i = 0; i < songs.length; i++) songs[i] = new ArrayList<>();
        
        for (int i = 0; i < genres.length; i++) {
            int gIdx = gList.indexOf(genres[i]); // 장르별 누적횟수로 내림차순했을 때 장르 인덱스
            songs[gIdx].add(new Song(i, plays[i]));
        }
        
        int albumSize = 0;
        
        for (int i = 0; i < songs.length; i++) {
            Collections.sort(songs[i]); // 재생횟수 기준 내림차순 정렬
            albumSize += Math.min(2, songs[i].size()); // 앨범 수록곡 카운팅
            // System.out.println(gList.get(i) + " : " + songs[i]);
        }
        
        int[] answer = new int[albumSize];
        int aIdx = 0;
        
        for (int i = 0; i < songs.length; i++) {
            int len = Math.min(2, songs[i].size()); // 현재 장르의 수록곡 개수
            for (int j = 0; j < len; j++) {
                answer[aIdx++] = songs[i].get(j).idx;
            }
        }
        return answer;
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
            
        // @Override
        // public String toString() {
        //     return "{idx=" + idx + ", play=" + play + "}";
        // }
    }
}