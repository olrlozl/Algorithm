import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr;
    public static boolean[][] visit;
    public static PriorityQueue<Virus> pq;
    public static ArrayList<Virus> newVirus;
    public static int[][] delta = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        visit = new boolean[N][N];
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] != 0) {
                    pq.add(new Virus(i, j, arr[i][j]));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;
        int Y = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < S; i++) {
            int len = pq.size();
            newVirus = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                Virus virus = pq.poll();
                bfs(virus);
            }
            for (int j = 0; j < newVirus.size(); j++) {
                pq.add(newVirus.get(j));
            }
        }

        bw.write(arr[X][Y]  + "");
        bw.close();
    }

    public static void bfs(Virus start) {
        visit[start.r][start.c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = start.r + delta[i][0];
            int nc = start.c + delta[i][1];

            if (0 <= nr && nr < N && 0 <= nc && nc < N && !visit[nr][nc] && arr[nr][nc] == 0) {
                arr[nr][nc] = start.num;
                newVirus.add(new Virus(nr, nc, start.num));
                visit[nr][nc] = true;
            }
        }
    }

    public static class Virus implements Comparable<Virus> {
        int r;
        int c;
        int num;

        public Virus(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }

        @Override
        public int compareTo(Virus v) {
            return this.num - v.num;
        }
    }
}