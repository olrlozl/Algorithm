import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[][] arr;
    public static Queue<Virus> q = new LinkedList<>();
    public static int[][] delta = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        ArrayList<Virus> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] != 0) {
                    list.add(new Virus(i, j, arr[i][j]));
                }
            }
        }

        Collections.sort(list, (a, b) -> a.num - b.num);

        for (int i = 0; i < list.size(); i++) {
            q.add(list.get(i));
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;
        int Y = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < S; i++) {
            int len = q.size();
            for (int j = 0; j < len; j++) {
                bfs(q.poll());
            }
        }

        bw.write(arr[X][Y]  + "");
        bw.close();
    }

    public static void bfs(Virus start) {
        for (int i = 0; i < 4; i++) {
            int nr = start.r + delta[i][0];
            int nc = start.c + delta[i][1];

            if (0 <= nr && nr < N && 0 <= nc && nc < N && arr[nr][nc] == 0) {
                arr[nr][nc] = start.num;
                q.add(new Virus(nr, nc, start.num));
            }
        }
    }

    public static class Virus {
        int r;
        int c;
        int num;

        public Virus(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }
}