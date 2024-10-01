import java.io.*;
import java.util.*;
import java.awt.*;

public class Main {
    public static int N;
    public static int M;
    public static ArrayList<Point> home;
    public static ArrayList<Point> chicken;
    public static boolean[] open;
    public static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][N];
        home = new ArrayList<>();
        chicken = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) home.add(new Point(i, j));
                else if (arr[i][j] == 2) chicken.add(new Point(i, j));
            }
        }

        open = new boolean[chicken.size()];
        combination(0, 0);

        bw.write(result + "");
        bw.close();
    }

    public static void combination(int idx, int openCnt) {
        if (openCnt == M) {
            calculate();
            return;
        }
        if (idx == chicken.size()) {
            return;
        }
        open[idx] = true;
        combination(idx + 1, openCnt + 1);
        open[idx] = false;
        combination(idx + 1, openCnt);
    }

    public static void calculate() {
        int city = 0; // 도시의 치킨거리
        for (int i = 0; i < home.size(); i++) {
            int temp = 999; // i번째 집의 치킨거리
            for (int j = 0; j < chicken.size(); j++) {
                if (!open[j]) continue;
                temp = Math.min(temp, Math.abs(home.get(i).x - chicken.get(j).x) + Math.abs(home.get(i).y - chicken.get(j).y));
            }
            city += temp;
        }
        result = Math.min(result, city); // 도시의 치킨거리 최솟값
    }
}