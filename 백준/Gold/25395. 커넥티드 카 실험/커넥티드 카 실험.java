import java.io.*;
import java.util.*;

public class Main {
    public static int lCar; // 사물 인터넷 연결 가능성이 있는 가장 좌측 차 번호
    public static int rCar; // 사물 인터넷 연결 가능성이 있는 가장 우측 차 번호
    public static int[] pos;
    public static int[] fuel;
    public static int N;
    public static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        pos = new int[N + 1];
        fuel = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            fuel[i] = Integer.parseInt(st.nextToken());
        }

        bfs();

        for (int i = lCar; i <= rCar; i++) {
            bw.write(i + " ");
        }
        bw.close();
    }

    public static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[N + 1];

        queue.add(S);
        visit[S] = true;

        lCar = S;
        rCar = S;

        int lPos = pos[S]; // 이동가능한 가장 좌측 좌표
        int rPos = pos[S]; // 이동가능한 가장 우측 좌표

        while (!queue.isEmpty()) {
            int nowCar = queue.poll();
            lPos = Math.min(lPos, pos[nowCar] - fuel[nowCar]);
            rPos = Math.max(rPos, pos[nowCar] + fuel[nowCar]);

            // 왼쪽으로 확장
            for (int i = lCar - 1; i >= 1; i--) {
                if (pos[i] < lPos) break;
                if (visit[i]) continue;
                lCar = Math.min(lCar, i);
                queue.add(i);
                visit[i] = true;
            }

            // 오른쪽으로 확장
            for (int i = rCar + 1; i <= N; i++) {
                if (rPos < pos[i]) break;
                if (visit[i]) continue;
                rCar = Math.max(rCar, i);
                queue.add(i);
                visit[i] = true;
            }
        }
    }
}