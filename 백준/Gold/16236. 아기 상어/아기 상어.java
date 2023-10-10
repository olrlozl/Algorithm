import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int[][] arr;
    public static int[][] delta = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}}; // 상 좌 우 하
    public static int sharkSize = 2; // 현재 아기상어의 크기
    public static int eatCnt = 0; // 아기상어가 현재 크기일 때 먹은 물고기의 수
    public static int time = 0; // 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간

    public static void bfs(int r, int c) {
        Queue<Point> queue = new LinkedList<>();
        int[][] visit = new int[n][n];

        queue.add(new Point(r, c));
        ArrayList<Point> canEat = new ArrayList<>(); // 먹을 수 있는 물고기 후보

        while (!queue.isEmpty() && canEat.isEmpty()) { // 큐가 비어있지 않고 먹을 수 있는 물고기 후보가 없다면 계속 반복
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Point now = queue.poll();
                if (sharkSize > arr[now.x][now.y] && arr[now.x][now.y] != 0 && arr[now.x][now.y] != 9) { // 먹을 수 있다면 (아기상어보다 작고, 빈칸이 아니고, 기존 아기상어 위치가 아니라면)
                    canEat.add(new Point(now.x, now.y));
                }
                if (!canEat.isEmpty()) continue; // 먹을 수 있는 물고기가 하나라도 있다면, 더이상 큐에 넣지 않음
                for (int d = 0; d < 4; d++) {
                    int nr = now.x + delta[d][0];
                    int nc = now.y + delta[d][1];
                    if (0 > nr || nr >= n || 0 > nc || nc >= n || visit[nr][nc] != 0 || sharkSize < arr[nr][nc] || (nr == r && nc == c)) continue;
                    // 이웃한 위치 중에서, 범위를 벗어나지 않았고, 아직 방문하지 않았고, 지나갈 수 없고, 기존 아기상어의 위치가 아니라면
                    queue.add(new Point(nr, nc));
                    visit[nr][nc] = visit[now.x][now.y] + 1;
                }
            }
        }

        // 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움요청
        if (canEat.isEmpty()) return;

        // 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기 순으로 정렬
        Collections.sort(canEat, (o1, o2) -> {
            if (o1.x == o2.x)
                return o1.y - o2.y;
            return o1.x - o2.x;
        });

        arr[r][c] = 0; // 기존에 아기상어가 있던 위치를 빈 칸으로 변경
        arr[canEat.get(0).x][canEat.get(0).y] = 9; // 물고기를 먹은 위치로 아기상어 이동

        time += visit[canEat.get(0).x][canEat.get(0).y];

        if (sharkSize == ++eatCnt) { // 아기 상어는 자신의 크기와 같은 수의 물고기를 먹으면
            sharkSize++; // 크기가 1 증가
            eatCnt = 0; // 현재 크기일 때 먹은 물고기의 수 0으로 초기화
        }

        bfs(canEat.get(0).x, canEat.get(0).y); // 아기상어가 물고기를 먹은 위치에서 다시 bfs 재귀 호출

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        int r = 0; // 아기상어의 행 위치
        int c = 0; // 아기상어의 열 위치

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    r = i;
                    c = j;
                }
            }
        }

        bfs(r, c);

        bw.write(time + "");
        bw.close();
    }
}