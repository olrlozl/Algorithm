import java.util.*;
import java.io.*;

public class Main {
    public static class Room {
        List<Player> players = new ArrayList<>();
    }
    public static class Player implements Comparable<Player> {
        int level;
        String name;

        public Player (int level, String name){
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return name.compareTo(o.name);
        }

        @Override
        public String toString() {
            return level + " " + name;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken()); // 플레이어 수
        int m = Integer.parseInt(st.nextToken()); // 방의 정원
        List<Room> rooms = new ArrayList<>();

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()); // level
            String n = st.nextToken(); // name
            boolean enter = false;

            for (Room room : rooms) {
                if (room.players.size() < m
                        && room.players.get(0).level - 10 <= l
                        && l <= room.players.get(0).level + 10) {
                    room.players.add(new Player(l, n));
                    enter = true;
                    break;
                }
            }

            if (!enter) {
                Room room = new Room();
                room.players.add(new Player(l, n));
                rooms.add(room);
            }
        }

        for (Room room : rooms) {
            Collections.sort(room.players);
            if (room.players.size() == m) {
                bw.write("Started!\n");
            } else {
                bw.write("Waiting!\n");
            }
            for (Player player : room.players) {
                bw.write(player + "\n");
            }
        }
        bw.close();
    }
}